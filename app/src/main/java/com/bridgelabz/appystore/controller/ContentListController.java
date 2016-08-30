package com.bridgelabz.appystore.controller;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bridgelabz.appystore.interfaces.Asyntask;
import com.bridgelabz.appystore.interfaces.Backgroundpross;
import com.bridgelabz.appystore.interfaces.Dataready;
import com.bridgelabz.appystore.interfaces.DownloadCompleted;
import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.model.ContentListmodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 29/8/16.
 */

public class ContentListController {

    int moffset =0;


    public void loadContentlistfromserver(String url, final Backgroundpross asyntask){


        ContentListRestService object= new ContentListRestService(){
            @Override
            protected void onPostExecute(ArrayList arrayList) {
                asyntask.getContentlsitdatafromserver(arrayList);
            }
        };
        object.execute(url);

    }

    class ContentListRestService extends AsyncTask<String,String,ArrayList> {

        @Override
        protected ArrayList doInBackground(String... strings) {
            String contetjsondata = Utility.readDataFromServer(strings[0]);
            ArrayList<ContentListmodel> mContentlist = new ArrayList<>();


            try {
                JSONObject jsonobject = new JSONObject(contetjsondata);
                JSONObject subjsonobject = jsonobject.getJSONObject("Responsedetails");
                JSONArray jsonArray = subjsonobject.getJSONArray("data_array");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("title");
                    String imageurl = object.getString("image_path");
                    String videourl=object.getString("dnld_url");
                    Bitmap image= Utility.imageDownload(imageurl);
                    String duration = object.getString("content_duration");
                    mContentlist.add(new ContentListmodel(title,imageurl,videourl,image,duration));

                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return mContentlist;
        }


    }

    public  void populateContentlistViewmodel(String pid, final String cid, final DownloadCompleted dataready){
            moffset = moffset+4;


        String url = "http://beta.appystore.in/appy_app/appyApi_handler.php?method=getContentList&" +
                "content_type=videos&limit=5&offset="+moffset+"&"+cid+"=176&pcatid="+pid+"&age=1.5&incl_age=5";

      /*  String url ="http://beta.appystore.in/appy_app/appyApi_handler.php?method=getContentList&content_type=videos&limit=5&offset=0&catid=175&pcatid=174&age=1.5&incl_age=5";*/

        loadContentlistfromserver(url, new Backgroundpross() {
            @Override
            public void getContentlsitdatafromserver(ArrayList<ContentListmodel> data) {

                ArrayList<ContentListViewmodel> contentviewmodellist =new ArrayList<ContentListViewmodel>();
                for(int i=0;i<data.size();i++){
                    ContentListmodel contentListmodel = data.get(i);
                    String title = contentListmodel.getTitle();
                    Bitmap image = contentListmodel.getImage();
                    String videourl = contentListmodel.getVideoUrl();
                    contentviewmodellist.add(new ContentListViewmodel(title,image,videourl));
                }
                dataready.getcontentlistviewmodeldata(contentviewmodellist);
            }
        });
    }
}
