package com.bridgelabz.appystore.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bridgelabz.appystore.controller.ContentListController;
import com.bridgelabz.appystore.interfaces.DownloadCompleted;
import com.bridgelabz.appystore.interfaces.FetchContentLIst;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 27/8/16.
 * <Purpose>:
 * <p>
 * This class shows the Contentlist  model
 * it performs  the  operation of all the variables getter and setters
 * and content list model will come over here
 */

public class ContentListViewmodel extends BaseObservable{

    String title; // for title of contentlist
    Bitmap contentImage; // for image
    String videourl; // video url
    String imageurl; // image url

  // Creating the object of controller
    ContentListController controller = new ContentListController();
  //initilizing the Content list array list
    ArrayList<ContentListViewmodel> mcontentviewmodelist = new ArrayList<>();

    // Constructor of this class
    public ContentListViewmodel(String title,/* Bitmap image*/String imageurl, String url) {
        this.title = title;
       /* this.contentImage = image;*/
        this.imageurl=imageurl;
        this.videourl = url;
    }


    //Empty constructor
    public ContentListViewmodel() {
    }

    //Getter of bitmap
    public Bitmap getContentImage() {
        return contentImage;
    }

    //Gettet  of title
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

    /**
     * This function get the Content listviewmodel
     *
     * **/

    public void getContentListViewmodeldata(String pid, String cid, int offset, final FetchContentLIst fetchView) {

        controller.populateContentlistViewmodel(pid, cid, offset, new DownloadCompleted() {
            @Override
            public void getcontentlistviewmodeldata(ArrayList<ContentListViewmodel> data) {

                mcontentviewmodelist = data;
                fetchView.getcontentviewdata(mcontentviewmodelist);

            }
        });

    }

    /*@BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {

        Glide.with(view.getContext()).load(imageUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }*/

}





