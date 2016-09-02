package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bridgelabz.appystore.R;

public class VideoPlayer extends AppCompatActivity {
    // Video url
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        // Getting the intent
        Intent mintent = getIntent();
        // getting the String from intent
        url= mintent.getExtras().getString("videourl");
        // Parsing the uri
        Uri uri= Uri.parse(url);
        // Initilizing the videoview
        VideoView video=(VideoView)findViewById(R.id.videoview);
        // Settring the url
        video.setVideoURI(uri);
        //Mediacontrooler for the controolong the ideo
        MediaController mediaController = new
                MediaController(this);

        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        //Starting the video
        video.start();
    }
}
