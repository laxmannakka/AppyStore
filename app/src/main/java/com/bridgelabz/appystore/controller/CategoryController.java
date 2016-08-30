package com.bridgelabz.appystore.controller;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bridgelabz.appystore.interfaces.Asyntask;
import com.bridgelabz.appystore.interfaces.Dataready;
import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.model.ContentListmodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by bridgeit007 on 19/8/16.
 * <Purpose>
 * 1. In this class I have done  rest call operations
 * 2.it holds the model data and populate the view model
 */

public class CategoryController {


    // Initilizing the arraylist this list holds the category model data
   public  final ArrayList<Categorymodel> mListofCategory = new ArrayList<>();
    ArrayList<ContentListmodel> mContentlist = new ArrayList<>();


    public void loaddataFromserver(final Asyntask asyntask) {
        //rest url getting the data from url
        String url = "http://beta.appystore.in/appy_app/appyApi_handler.php?method=getCategoryList&content_type=videos&limit_start=0&age=1.5&incl_age=5";

        // creating the object of Restservice class
        RestService obj = new RestService() {

            @Override
            protected void onPostExecute(ArrayList arrayList) {
                asyntask.getdatafromserver(arrayList);
            }
        };


        // calling the function
        obj.execute(url);
    }


    // function for Populate viewmodel

    public ArrayList<CategoryViewmodel> populateViewmodel(final Dataready dataready) {

        // calling the rest call
        loaddataFromserver(new Asyntask() {
            @Override
            public void getdatafromserver(ArrayList<Categorymodel> categorylist) {


                ArrayList<CategoryViewmodel> viewmodellist = new ArrayList<>();
                for (int i = 0; i < categorylist.size(); i++) {
                    Categorymodel model = categorylist.get(i);
                    String title = model.getCategory_name();
                    Bitmap image = model.getImage();
                    String pid =model.getParent_category_id();
                    String cid = model.getCategory_id();
                    String url =model.getImage_path();
                    viewmodellist.add(new CategoryViewmodel(title, image,pid,cid,url));
                }
                dataready.getviewmodeldata(viewmodellist);
            }

        });
        return null;
    }


    // This class used for do the backround process
    class RestService extends AsyncTask<String, String, ArrayList> {
        @Override
        protected ArrayList doInBackground(String... strings) {

            String catogory_name;
            String category_id;
            String parent_category_id;
            String parent_category_name;
            String image_path;
            Bitmap image;

            // caliing the function it return the datafetched from url as String format
            String data = Utility.readDataFromServer(strings[0]);
            try {

                // crearing the json object of url data
                JSONObject jsonobject = new JSONObject(data);
                // creating the subjson array in jsonobject
                JSONObject subjsonobject = jsonobject.getJSONObject("Responsedetails");

                // reading json array which is in responce details
                JSONArray jsonArray = subjsonobject.getJSONArray("category_id_array");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject arrayobject = jsonArray.getJSONObject(i);
                    catogory_name = arrayobject.getString("category_name");
                    category_id = arrayobject.getString("category_id");
                    parent_category_id = arrayobject.getString("parent_category_id");
                    parent_category_name = arrayobject.getString("parent_category_name");
                    //JSONArray contentJson = arrayobject.getJSONArray("image_path");
                    JSONObject urlobject = arrayobject.getJSONObject("image_path");
                    String url = urlobject.getString("50x50");
                    image = Utility.imageDownload(url);
                    mListofCategory.add(new Categorymodel(catogory_name,category_id,parent_category_id,parent_category_name,image));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mListofCategory;

        }
    }





}


