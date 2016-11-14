package com.bridgelabz.appystore.controller;

import android.os.AsyncTask;

import com.bridgelabz.appystore.interfaces.ContentListAsynTask;
import com.bridgelabz.appystore.interfaces.ContentListDataDownloadCompleted;
import com.bridgelabz.appystore.model.ContentListmodel;
import com.bridgelabz.appystore.utility.DialogBox;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 29/8/16.
 */

public class ContentListController {

CategoryController obj = new CategoryController();

    public void loadContentListFromServer(String url, final ContentListAsynTask asyntask){


        ContentListRestService object= new ContentListRestService(){
            @Override
            protected void onPostExecute(ArrayList arrayList) {
                asyntask.loadedContentListDataFromServer(arrayList);
            }
        };
        object.execute(url);

    }

    class ContentListRestService extends AsyncTask<String,String,ArrayList> {

        @Override
        protected ArrayList doInBackground(String... strings) {
            String contetjsondata = HttpManager.readDataFromServer(strings[0]);
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
//                    Bitmap image= HttpManager.imageDownload(imageurl);
                    String duration = object.getString("content_duration");
                    mContentlist.add(new ContentListmodel(title,imageurl,videourl,duration));

                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return mContentlist;
        }


    }

    public  void populateContentListViewModel(String pid, final String cid, int offset, final ContentListDataDownloadCompleted dataready) {


            String url = "http://beta.appystore.in/appy_app/appyApi_handler.php?method=getContentList&" +
                    "content_type=videos&limit=5&offset=" + offset + "&" + cid + "=176&pcatid=" + pid + "&age=1.5&incl_age=5";

            loadContentListFromServer(url, new ContentListAsynTask() {
                @Override
                public void loadedContentListDataFromServer(ArrayList<ContentListmodel> data) {

                    ArrayList<ContentListViewmodel> contentviewmodellist = new ArrayList<ContentListViewmodel>();
                    for (int i = 0; i < data.size(); i++) {
                        ContentListmodel contentListmodel = data.get(i);
                        String title = contentListmodel.getTitle();
                        String url = contentListmodel.getImageUrl();
                        //   Bitmap image = contentListmodel.getImage();
                        String videourl = contentListmodel.getVideoUrl();
                        contentviewmodellist.add(new ContentListViewmodel(title, url, videourl));
                    }
                    dataready.receivedContentListViewModelData(contentviewmodellist);
                }
            });
        }




}
