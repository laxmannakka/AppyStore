package com.bridgelabz.appystore.model;

import android.graphics.Bitmap;

/**
 * Created by bridgeit007 on 19/8/16.
 */

public class ContentListmodel {

    String title;
    String imageUrl;
    String videoUrl;
    Bitmap image;
    String durationofvideo;

    public ContentListmodel(String title, String imageUrl, String videoUrl, Bitmap image, String durationofvideo) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.image = image;
        this.durationofvideo = durationofvideo;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDurationofvideo() {
        return durationofvideo;
    }

    public void setDurationofvideo(String durationofvideo) {
        this.durationofvideo = durationofvideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDurationofvideos() {
        return durationofvideo;
    }

    public void setDurationofvideos(String durationofvideos) {
        this.durationofvideo = durationofvideos;
    }
}

