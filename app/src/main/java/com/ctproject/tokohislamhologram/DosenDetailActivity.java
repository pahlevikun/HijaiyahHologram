package com.ctproject.tokohislamhologram;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by farhan on 2/29/16.
 */
public class DosenDetailActivity extends AppCompatActivity {
    //mendefinisikan
    private TextView textViewJabatan;
    private TextView textViewNama;
    private TextView textViewTelepon;
    private ImageView imageViewFoto;

    private Button hologram;

    private LinearLayout linLayJabatan;
    private LinearLayout linLayTelepon;

    private ClipboardManager clipboardManager;
    private ClipData clipData;

    private String namaDosen;
    private String bantu;

    public static final String VIDEOHOLOGRAM = "videohologram";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        //inisialisasi
        textViewNama = (TextView) findViewById(R.id.tvNamaDetail);
        textViewJabatan = (TextView) findViewById(R.id.tvInformasiHijaiyah);
        textViewTelepon = (TextView) findViewById(R.id.tvCaraMembacaHijaiyah);
        linLayJabatan = (LinearLayout) findViewById(R.id.linearLayoutInformasiHijaiyah);
        linLayTelepon = (LinearLayout) findViewById(R.id.linearLayoutCaraMembacaHijaiyah);
        hologram = (Button) findViewById(R.id.tombolHologramHijaiyah);
        imageViewFoto = (ImageView) findViewById(R.id.ivDetailHijaiyah);
        //mengambil intent
        Intent intent = getIntent();

                //Menampilkan nilai hasil parsing dari intent
                textViewNama.setText(intent.getStringExtra(DosenActivity.NAMA));
        if(intent.getStringExtra(DosenActivity.INFO)==null){
            textViewJabatan.setText("-");
        }
        else{
            textViewJabatan.setText(intent.getStringExtra(DosenActivity.INFO));
        }
        if(intent.getStringExtra(DosenActivity.CARABACA)==null) {
            textViewTelepon.setText("-");
        }
        else{
            textViewTelepon.setText(intent.getStringExtra(DosenActivity.CARABACA));
        }
        if(intent.getStringExtra(DosenActivity.FOTO)==null){
            imageViewFoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_noimage));
        }
        else {
            Picasso.with(this).load(intent.getStringExtra(DosenActivity.FOTO)).into(imageViewFoto);
        }

        //Menekan Liniear Layout
        linLayJabatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = getIntent();
                if(intent.getStringExtra(DosenActivity.INFO)==null){
                    Toast.makeText(DosenDetailActivity.this,"Tidak ada data!", Toast.LENGTH_SHORT).show();
                }
                else {
                    namaDosen = textViewNama.getText().toString();
                    bantu = intent.getStringExtra(DosenActivity.INFO);
                    clipData = ClipData.newPlainText("text", "Jabatan Bpk/Ibu " + namaDosen + ", adalah " + bantu);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(DosenDetailActivity.this,"Jabatan Dosen berhasil disalin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        linLayTelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = getIntent();
                if(intent.getStringExtra(DosenActivity.CARABACA)==null){
                    Toast.makeText(DosenDetailActivity.this,"Tidak ada data!", Toast.LENGTH_SHORT).show();
                }
                else {
                    namaDosen = textViewNama.getText().toString();
                    bantu = intent.getStringExtra(DosenActivity.CARABACA);
                    clipData = ClipData.newPlainText("text", "No. Telp Bpk/Ibu " + namaDosen + ", adalah " + bantu);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(DosenDetailActivity.this,"Telepon Dosen berhasil disalin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hologram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent ambil = getIntent();
                String bantuin = ambil.getStringExtra(DosenActivity.VIDEO);
                Intent intent = new Intent(DosenDetailActivity.this, DosenDetailHologramActivity.class);
                intent.putExtra(VIDEOHOLOGRAM, bantuin);
                startActivity(intent);
            }
        });
    }

    //
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bantuan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bantuan:
                Intent intentBantuan = new Intent(DosenDetailActivity.this, BantuanActivity.class);
                startActivity(intentBantuan);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}