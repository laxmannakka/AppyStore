package com.bridgelabz.appystore.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.interfaces.CategoryAsyntask;
import com.bridgelabz.appystore.interfaces.CategoryDataDownloadCompleted;
import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.utility.CategoryDataBaseHandler;
import com.bridgelabz.appystore.utility.DataBaseHandler;
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

    Context mContext;
    SharedPreferences sharedPreferences;

    // Initilizing the arraylist this list holds the category model data
    ArrayList<Categorymodel> mListofCategory = new ArrayList<>();
    ArrayList<CategoryViewmodel> viewmodellist = new ArrayList<>();

    CategoryDataBaseHandler db;



    public CategoryController(Context context){
        this.mContext = context.getApplicationContext();
        db= new CategoryDataBaseHandler(mContext);
        String ll="";
    }

    public CategoryController() {
    }


    public void loadDataFromServer(final CategoryAsyntask categoryAsyntask) {
        //rest url getting the data from url
        String url = mContext.getResources().getString(R.string.categoryvideoslink);
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
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor= sharedPreferences.edit();
        editor.putInt("key",0);
        editor.commit();

        int value = sharedPreferences.getInt("key",-1);
        Toast.makeText(mContext.getApplicationContext(),"value is "+value,Toast.LENGTH_LONG).show();
        if(value==0) {
            Log.i("laxman","INside the if db handler ---------");
            sharedPreferences.edit().putInt("key",5).commit();
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
                        db.addStoreDataToDataBase(new CategoryViewmodel(title, pid, cid, url));
                    }
                    dataready.receivedCategoryViewModelData(viewmodellist);
                }
            });
        }
        else
        {
            CategoryDataBaseHandler db = new CategoryDataBaseHandler(mContext);
            ArrayList<CategoryViewmodel> categorymodellist=db.getAllStoredData();
            dataready.receivedCategoryViewModelData(categorymodellist);
        }
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
