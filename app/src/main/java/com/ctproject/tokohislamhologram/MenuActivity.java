package com.ctproject.tokohislamhologram;

/**
 * Created by farhan on 2/29/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private Button TombolCariDosen,TombolBagikan,TombolInformasi,TombolAplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TombolCariDosen = (Button) findViewById(R.id.tombolDosen);
        TombolBagikan = (Button) findViewById(R.id.tombolBagikan);
        TombolInformasi = (Button) findViewById(R.id.tombolInformasi);
        TombolAplikasi = (Button) findViewById(R.id.tombolAplikasi);

        TombolCariDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MenuActivity.this, DosenActivity.class);
                startActivity(intent);
            }
        });

        TombolBagikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent Bagikan = new Intent(Intent.ACTION_SEND);
                Bagikan.setType("text/plain");
                Bagikan.putExtra(Intent.EXTRA_TEXT, "Ayo gunakan aplikasi Pocket Yasin dilengkapi dengan Tahlil dan Ayat Kursi beserta terjemahannya, dapatkan gratis di https://play.google.com/");
                startActivity(Intent.createChooser(Bagikan, "Bagikan Aplikasi ini melalui:"));
            }
        });

        TombolInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MenuActivity.this, InformasiActivity.class);
                startActivity(intent);
            }
        });

        TombolAplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan lagi untuk keluar!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}

