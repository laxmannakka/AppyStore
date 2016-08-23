package com.bridgelabz.appystore.view;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {
    ArrayList<Categorymodel> mListofContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);
        mListofContent = new ArrayList<>();

        String url = "http://beta.appystore.in/appy_app/appyApi_handler.php?method=getCatContentList&limit_start=0&age=3&incl_age=5&content_type=appsgames&limit=1&offset=0&limit_end=15";
        Myactivity obj = new Myactivity();
        obj.execute(url);
    }


    class Myactivity extends AsyncTask<String, String, ArrayList> {
        @Override
        protected ArrayList doInBackground(String... strings) {
          final  CategoryController obj = new CategoryController();
            String data = obj.readDataFromServer(strings[0]);
            String title;
            JSONObject Root = null;
            try {
                Root = new JSONObject(data);
                JSONArray jsonArray = Root.optJSONArray("data_array");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    title = jsonObject.getString("Title");
                    String contentarray = jsonObject.getString("content_array");
                    JSONArray subjsonarray = jsonObject.getJSONArray("content_array");
                    for(int j=0;j<subjsonarray.length();j++) {
                        JSONObject object = subjsonarray.getJSONObject(j);
                        String imageurl = object.getString("image_path");
                        String groupid = object.getString("group_id");
                        String sub_category_id = object.getString("sub_category_id");
                        Bitmap contentimage = CategoryController.imageDownload(imageurl);
                        mListofContent.add(new Categorymodel(title, imageurl, groupid, sub_category_id, contentimage));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mListofContent;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {

        ArrayList<CategoryViewmodel> viewmodelist = CategoryController.CatogoryViewtoCatogoryviewmodel(arrayList);
            String lll="";
        }
    }
}





