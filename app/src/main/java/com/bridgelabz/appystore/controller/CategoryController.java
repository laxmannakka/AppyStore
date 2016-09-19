package com.bridgelabz.appystore.controller;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bridgelabz.appystore.interfaces.CategoryAsyntask;
import com.bridgelabz.appystore.interfaces.CategoryDataDownloadCompleted;
import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 19/8/16.
 * <Purpose>
 * 1. In this class I have done  rest call operations
 * 2.it holds the model data and populate the view model
 */

public class CategoryController {


    // Initilizing the arraylist this list holds the category model data
    ArrayList<Categorymodel> mListofCategory = new ArrayList<>();
    ArrayList<CategoryViewmodel> viewmodellist = new ArrayList<>();

    public void loadDataFromServer(final CategoryAsyntask categoryAsyntask) {
        //rest url getting the data from url
        String url = "http://beta.appystore.in/appy_app/appyApi_handler.php?method=getCategoryList&content_type=videos&limit_start=0&age=1.5&incl_age=5";

        // creating the object of Restservice class
        RestService obj = new RestService() {

            @Override
            protected void onPostExecute(ArrayList<Categorymodel> arrayList) {
                categoryAsyntask.loadedCategoryDataFromServer(arrayList);
            }
        };


        // calling the function
        obj.execute(url);
    }


    // function for Populate viewmodel
    public void populateViewmodel(final CategoryDataDownloadCompleted dataready) {

        // calling the rest call
        loadDataFromServer(new CategoryAsyntask() {
            @Override
            public void loadedCategoryDataFromServer(ArrayList<Categorymodel> categorylist) {


                for (int i = 0; i < categorylist.size(); i++) {
                    Categorymodel model = categorylist.get(i);
                    String title = model.getCategory_name();
                    String pid = model.getParent_category_id();
                    String cid = model.getCategory_id();
                    String url = model.getImage_path();
                    viewmodellist.add(new CategoryViewmodel(title, pid, cid, url));
                }
                dataready.receivedCategoryViewModelData(viewmodellist);
            }

        });
    }


    // This class used for do the backround process
    class RestService extends AsyncTask<String, String, ArrayList<Categorymodel>> {
        @Override
        protected ArrayList doInBackground(String... strings) {

            String catogory_name;
            String category_id;
            String parent_category_id;
            String parent_category_name;
            String image_path;
            Bitmap image;

            // caliing the function it return the datafetched from url as String format
            String data = HttpManager.readDataFromServer(strings[0]);
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
                    //  image = HttpManager.imageDownload(url);
                    mListofCategory.add(new Categorymodel(catogory_name, category_id, parent_category_id, parent_category_name, url));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mListofCategory;
        }
    }

}
