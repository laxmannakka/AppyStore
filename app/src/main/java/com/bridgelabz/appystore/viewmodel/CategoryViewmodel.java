package com.bridgelabz.appystore.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.interfaces.Dataready;
import com.bridgelabz.appystore.interfaces.FetchView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bridgeit007 on 19/8/16.
 * <p>
 * <Purpose>:
 *
 * This class shows the Category model
 */

public class CategoryViewmodel implements Serializable {


    // this variable  holds the title
    String title;
    // this varible holds the image
    Bitmap image;
    // this varible holds the parent category id
    String pid;
    // this variable holds ths catogory id
    String cid;
    // this is for url
    String url;
    //Arraylist of Catogory model it holds the viewmodle list
    ArrayList<CategoryViewmodel> viewmodlelist = new ArrayList<>();
    // Creating the object of Categorycontroller
    CategoryController categoryController = new CategoryController();

    //creating the constructor taking arguments title,bitmap
    public CategoryViewmodel(String title, Bitmap image) {
        this.title = title;
        this.image = image;
    }

    /**
     * this category maodl constructer
     * it takes the arguments title,image,pid ,cid url
     **/
    public CategoryViewmodel(String title, String pid, String cid, Bitmap image) {
        this.title = title;
        this.image = image;
        this.pid = pid;
        this.cid = cid;
    }

    // Empty constructor of this class
    public CategoryViewmodel() {

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



    public ArrayList<CategoryViewmodel> populateDummydata(Context mcontext) {

        ArrayList<CategoryViewmodel> dummlist = new ArrayList<>();
        int iconC[] = {R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.
                ic_launcher};
        String titleC[] = {"ABCD", "XYZ", "PQRS"};
        for (int i = 0; i <10; i++) {
            Bitmap icon = BitmapFactory.decodeResource(mcontext.getResources(), iconC[2]);
            CategoryViewmodel currentC = new CategoryViewmodel();
            currentC.image = icon;
            currentC.title= titleC[2];
            dummlist.add(currentC);
        }

        return dummlist;
    }





    /**
     * this function get the viewmodel data
     * here performing the call back mechanism get the data from controller
     * and return ths list which we are getting
     **/

    public ArrayList<CategoryViewmodel> getViewmodeldata(final FetchView fetchView) {

        final ArrayList<CategoryViewmodel> viewmodel = categoryController.populateViewmodel(new Dataready() {
            // overide method will execute the when the responce come from the populatemodel function
            @Override
            public void getviewmodeldata(ArrayList<CategoryViewmodel> data) {

                viewmodlelist = data;
                fetchView.getviewdata(viewmodlelist);
            }
        });

        return viewmodlelist; // returning the arraylist of data

    }


}
