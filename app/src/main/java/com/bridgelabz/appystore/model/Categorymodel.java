package com.bridgelabz.appystore.model;

import android.graphics.Bitmap;

/**
 * Created by bridgeit007 on 19/8/16.
 *
 * <Purpose>
 * 1.This model class shows the Catogorylist
 *
 *
 *
 *
 */

public class Categorymodel {

    String category_name;
    String category_id;
    String parent_category_id;
    String parent_category_name;
    String image_path;
    String sub_category_id;
    Bitmap image;



    public Categorymodel(String category_name, String category_id, String parent_category_id, String parent_category_name, Bitmap image) {
        this.category_name = category_name;
        this.category_id = category_id;
        this.parent_category_id = parent_category_id;
        this.parent_category_name = parent_category_name;
        this.image = image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(String parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

    public String getParent_category_name() {
        return parent_category_name;
    }

    public void setParent_category_name(String parent_category_name) {
        this.parent_category_name = parent_category_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}