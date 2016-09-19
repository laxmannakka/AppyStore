package com.bridgelabz.appystore.model;

/**
 * Created by bridgeit007 on 17/9/16.
 */

public class Historymodel {
    String title;
    String imageurl;
    String videourl;





    public Historymodel() {
    }

    public Historymodel(String title, String imageurl, String videourl) {
        this.title = title;
        this.imageurl = imageurl;
        this.videourl = videourl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
