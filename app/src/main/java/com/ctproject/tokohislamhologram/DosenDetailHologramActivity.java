package com.ctproject.tokohislamhologram;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import android.content.Intent;

/**
 * Created by farhan on 3/31/16.
 */
public class DosenDetailHologramActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail_hologram);

        //mengambil intent
        Intent intent = getIntent();
        String url = intent.getStringExtra(DosenDetailActivity.VIDEOHOLOGRAM);

        Uri uri=Uri.parse(url);

        VideoView video=(VideoView)findViewById(R.id.videoView);
        video.setVideoURI(uri);
        video.start();
    }
}
