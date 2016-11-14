package com.bridgelabz.appystore.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.interfaces.CategoryDataDownloadCompleted;
import com.bridgelabz.appystore.interfaces.FetchCategoryList;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 19/8/16.
 * <p>
 * <Purpose>:
 *
 * This class shows the Category model
 */

public class CategoryViewmodel  {



    // this variable  holds the title
    String title;
    // this varible holds the homeicon
    Bitmap image;
    // this varible holds the parent category id
    String pid;
    // this variable holds ths catogory id
    String cid;
    // this is for url
    String url;
    Context mContext;
    CategoryController categoryController;

    public CategoryViewmodel() {
    }

    //Arraylist of Catogory model it holds the viewmodle list
    ArrayList<CategoryViewmodel> viewmodlelist = new ArrayList<>();
    // Creating the object of Categorycontroller

    //creating the constructor taking arguments title,bitmap
    public CategoryViewmodel(String title, Bitmap image) {
        this.title = title;
        this.image = image;
    }
    public CategoryViewmodel(String url){
        this.url=url;
    }



    /**
     * this category maodl constructer
     * it takes the arguments title,homeicon,pid ,cid url
     **/


    public CategoryViewmodel(String title,String pid,String cid,String url){
        this.title=title;
        this.pid=pid;
        this.cid=cid;
        this.url=url;
    }
    // Empty constructor of this class
    public CategoryViewmodel( Context context) {
        this.mContext= context;
         categoryController   = new CategoryController(mContext);

    }


    /**
     * getter and setter for all varibles
     **/
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


    /**
     * this function get the viewmodel data
     * here performing the call back mechanism get the data from controller
     * and return ths list which we are getting
     *
     * @param fetchView*/

    public void getCategoryViewModelData(final FetchCategoryList fetchView) {

         categoryController.populateViewmodel(new CategoryDataDownloadCompleted() {
            // overide method will execute the when the responce come from the populatemodel function
            @Override
            public void receivedCategoryViewModelData(ArrayList<CategoryViewmodel> data) {

                fetchView.receivedCategoryViewData(data);
            }
        });


    }


}
