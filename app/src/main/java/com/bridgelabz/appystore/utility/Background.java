package com.bridgelabz.appystore.utility;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bridgelabz.appystore.R;

/**
 * Created by bridgeit007 on 8/10/16.
 */

public class Background extends Service {
    MediaPlayer mediaPlayer;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Toast.makeText(getApplicationContext(),"Created Sucessfully",Toast.LENGTH_LONG).show();

        mediaPlayer = MediaPlayer.create(this, R.raw.ll);
        mediaPlayer.seekTo(20);

        mediaPlayer.setLooping(false); // Set looping
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        mediaPlayer.start();

    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();

    }
}
