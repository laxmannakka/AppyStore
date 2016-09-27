package com.bridgelabz.appystore.viewmodel;

import com.bridgelabz.appystore.controller.SearchController;
import com.bridgelabz.appystore.interfaces.FetchSearchview;
import com.bridgelabz.appystore.interfaces.SearchDataDownLoadCompleted;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 15/9/16.
 */

public class SearchViewmodel {
    int total_cout;
    String urlimage;
    String videourl;
    String title;

    public SearchViewmodel() {
    }

    public SearchViewmodel(String title, String imageurl, String dnldUrl){
        this.title =title;
        this.urlimage=imageurl;
        this.videourl=dnldUrl;

    }
    SearchController searchControllerObject =  new SearchController();



    public int getTotal_cout() {
        return total_cout;
    }

    public void setTotal_cout(int total_cout) {
        this.total_cout = total_cout;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



  public void  getSerachResultFromViewmodel(String text, final FetchSearchview fetchSearchview){
      searchControllerObject.populateSearcViewModelData(text, new SearchDataDownLoadCompleted() {
          @Override
          public void receivedSerachViewModelData(ArrayList<SearchViewmodel> model) {
              fetchSearchview.receivedSearchviewdata(model);

          }
      });

  }
}
