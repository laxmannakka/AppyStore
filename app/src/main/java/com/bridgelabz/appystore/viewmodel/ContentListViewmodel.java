package com.bridgelabz.appystore.viewmodel;

import android.graphics.Bitmap;

import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.controller.ContentListController;
import com.bridgelabz.appystore.interfaces.Dataready;
import com.bridgelabz.appystore.interfaces.DownloadCompleted;
import com.bridgelabz.appystore.interfaces.FetchContentLIst;
import com.bridgelabz.appystore.interfaces.FetchView;
import com.bridgelabz.appystore.model.ContentListmodel;
import com.bridgelabz.appystore.view.ContentListView;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 27/8/16.
 */

public class ContentListViewmodel {

    String title;
    Bitmap contentImage;
    String url;

    // Constructor
    public ContentListViewmodel(String title,Bitmap image,String url){
        this.title=title;
        this.contentImage=image;
        this.url =url;
    }

    public Bitmap getContentImage() {
        return contentImage;
    }

    public String getTitle() {
        return title;
    }

    ContentListController controller = new ContentListController();
    ArrayList<ContentListViewmodel> mcontentviewmodelist =  new ArrayList<>();

    public ContentListViewmodel(){}


    CategoryController categoryController = new CategoryController();

    public  void  getContentListViewmodeldata(String pid, String cid, final FetchContentLIst fetchView){

        controller.populateContentlistViewmodel(pid, cid, new DownloadCompleted() {
            @Override
            public void getcontentlistviewmodeldata(ArrayList<ContentListViewmodel> data) {

                mcontentviewmodelist=data;
                fetchView.getcontentviewdata(mcontentviewmodelist);


            }
        });

    }

}





