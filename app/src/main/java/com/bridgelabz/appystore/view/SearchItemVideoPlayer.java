package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bridgelabz.appystore.R;

public class SearchItemVideoPlayer extends AppCompatActivity {

    String url;
    VideoView mVideoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item_video_player);
        Intent mIntent = getIntent();
        url=mIntent.getExtras().getString("videourl");
        mVideoview = (VideoView) findViewById(R.id.videoview);
        playvideo(url);
    }
    public void playvideo(String url) {
        Uri uri = Uri.parse(url);
        // Initilizing the videoview
        // Settring the url
        mVideoview.setVideoURI(uri);

        //Mediacontrooler for the controolong the ideo
        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(mVideoview);
        mVideoview.setMediaController(mediaController);
        //Starting the video
        mVideoview.start();
    }

}
