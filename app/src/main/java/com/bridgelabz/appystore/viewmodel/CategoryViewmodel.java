package com.bridgelabz.appystore.viewmodel;

import android.graphics.Bitmap;

import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.interfaces.Asyntask;
import com.bridgelabz.appystore.interfaces.Dataready;
import com.bridgelabz.appystore.interfaces.FetchView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bridgeit007 on 19/8/16.
 *
 * <Purpose>
 *
 * This class shows the Category model
 *
 */

public class CategoryViewmodel implements Serializable {
    String title;
    Bitmap image;
    String pid;
    String cid;
    String url;
    //creating the constructor taking arguments title,bitmap
    public CategoryViewmodel(String title, Bitmap image) {
        this.title = title;
        this.image = image;
    }

    public CategoryViewmodel(String title, Bitmap image, String pid, String cid,String url) {
        this.title = title;
        this.image = image;
        this.pid = pid;
        this.cid = cid;
        this.url =url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    ArrayList<CategoryViewmodel> viewmodlelist = new ArrayList<>();

 // Creating the object of Categorycontroller
    CategoryController categoryController = new CategoryController();

    public CategoryViewmodel() {

    }



    //Function for get the viewmodel data
    public ArrayList<CategoryViewmodel> getViewmodeldata(final FetchView fetchView){

        final ArrayList<CategoryViewmodel> viewmodel= categoryController.populateViewmodel(new Dataready() {
            @Override
            public void getviewmodeldata(ArrayList<CategoryViewmodel> data) {

                viewmodlelist=data;
                fetchView.getviewdata(viewmodlelist);
            }
        });

        return viewmodlelist;

   }



}
