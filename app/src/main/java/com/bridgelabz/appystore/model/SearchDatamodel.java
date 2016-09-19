package com.bridgelabz.appystore.model;

/**
 * Created by bridgeit007 on 15/9/16.
 */

public class SearchDatamodel {

    int total_count;
    String title;
    String imageurl;
    String videourl;
    String canonical_name;



    public SearchDatamodel(String title, String imageurl, String videourl, String canonicalname) {
    this.title=title;
        this.imageurl=imageurl;
        this.videourl=videourl;
        this.canonical_name=canonicalname;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
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

    public String getCanonical_name() {
        return canonical_name;
    }

    public void setCanonical_name(String canonical_name) {
        this.canonical_name = canonical_name;
    }
}
