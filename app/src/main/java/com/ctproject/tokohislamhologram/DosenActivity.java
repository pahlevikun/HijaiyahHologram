package com.ctproject.tokohislamhologram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ctproject.tokohislamhologram.Models.Listdosen;
import com.ctproject.tokohislamhologram.Models.Model;
import com.ctproject.tokohislamhologram.Models.RestAPI;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by farhan on 2/29/16.
 */
//public class DosenActivity extends AppCompatActivity implements ListView.OnItemClickListener, SearchView.OnQueryTextListener {
    public class DosenActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    //root url dari webservice
    public static final String ROOT_URL = "http://fyp-pi.esy.es/";
    //deklarasi varibel untuk mengirim data ke activity lain
    public static final String ID = "id";
    public static final String NAMA = "nama";
    public static final String FOTO = "foto";
    public static final String INFO = "info";
    public static final String CARABACA = "carabaca";
    public static final String VIDEO = "video";
    //listview untuk menampilkan data
    private ListView listview;
    //varibel books bertipe List dan List tersebut berdasarkan objek Listdosen
    private List<Listdosen> dosens;
    private android.widget.SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inisialisasi listview
        listview = (ListView) findViewById(R.id.listViewDosen);

        //memanggil method untuk mengambil data dosen
        getDosen();

        //setting onItemClickListener untuk listview
        listview.setOnItemClickListener(this);

    }


    private void getDosen() {
        //Ketika Aplikasi mengambil data kita akan melihat progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Mengambil Data","Mohon tunggu..",false,false);
        //Logging Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //set Level Log
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())//GsonConverter untuk parsing json
                .client(httpClient.build())
                .build();

        RestAPI service = retrofit.create(RestAPI.class);

        Call<Model> call = service.loadListDosen();
        call.enqueue(new Callback<Model>() {  //Asyncronous Request
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                loading.dismiss();
                List<Listdosen> dosen = response.body().getListdosen();

                //memasukkan data dari varibel dosen ke dosens
                dosens = dosen;
                //memanggil method untuk menampilkan list
                showList();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }


    private void showList() {
        //String array untuk menyimpan nama semua nama dosen
        String[] items = new String[dosens.size()];

        for (int i = 0; i < dosens.size(); i++) {
            items[i] = dosens.get(i).getNama();
        }
        //Membuat Array Adapter for listview
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_data_listview, items);

        //setting adapter untuk listview
        listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);
    }


    //method ini akan dieksekusi ketikan listitem diklik
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //membuat intent
        Intent intent = new Intent(this, DosenDetailActivity.class);
        //mengambil dosen dari list
        Listdosen listdosen = dosens.get(position);
        //menambahkan detail dosen untuk intent
        intent.putExtra(ID, listdosen.getId());
        intent.putExtra(NAMA, listdosen.getNama());
        intent.putExtra(INFO, listdosen.getInfo());
        intent.putExtra(CARABACA, listdosen.getCarabaca());
        intent.putExtra(FOTO, listdosen.getFoto());
        intent.putExtra(VIDEO, listdosen.getVideo());

        //memulai activity lain untuk menampilkan detail dosen
        startActivity(intent);
    }
}