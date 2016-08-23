package com.bridgelabz.appystore.model;

import android.graphics.Bitmap;

/**
 * Created by bridgeit007 on 19/8/16.
 *
 * <Purpose>
 * 1.This model class shows the Catogorylist
 *
 *
 */

public class Categorymodel {

    String title;
    String group_id;
    String parent_category_id;
    String sub_category_id;
    String sub_category_title;
    String image_path;
    Bitmap image;

    public Categorymodel(String title, String imageurl, String groupid, String sub_category_id, Bitmap contentimage) {
        this.title=title;
        this.image_path=imageurl;
        this.parent_category_id=groupid;
        this.sub_category_id=sub_category_id;
        this.image=contentimage;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(String sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getSub_category_title() {
        return sub_category_title;
    }

    public void setSub_category_title(String sub_category_title) {
        this.sub_category_title = sub_category_title;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
