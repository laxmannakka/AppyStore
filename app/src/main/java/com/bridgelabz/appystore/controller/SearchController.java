package com.bridgelabz.appystore.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.bridgelabz.appystore.interfaces.SearchDataAsyntask;
import com.bridgelabz.appystore.interfaces.SearchDataDownLoadCompleted;
import com.bridgelabz.appystore.model.SearchDatamodel;
import com.bridgelabz.appystore.viewmodel.SearchViewmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 15/9/16.
 */

public class SearchController {
    ArrayList<SearchDatamodel> mListofSearchData = new ArrayList<>();
    ArrayList<SearchViewmodel> mListofSearchviewMoeldata = new ArrayList<>();


    public void loadDataFromServer(String searchItem, final SearchDataAsyntask searchAsyntask) {
        String searchUrl = "http://beta.appystore.in/appy_app/appyApi_handler.php?method=search&keyword=" + searchItem + "&content_type=appsgames&limit=20&offset=0&age=1&incl_age=6";
        SearchAsyntask obj = new SearchAsyntask() {
            @Override
            protected void onPostExecute(ArrayList<SearchDatamodel> searchDatamodels) {
                super.onPostExecute(searchDatamodels);
                searchAsyntask.loadedSearchDataFromServer(searchDatamodels);
            }
        };
        obj.execute(searchUrl);

    }

    public void populateSearcViewModelData(String searchItem, final SearchDataDownLoadCompleted searchDataReady) {
        loadDataFromServer(searchItem, new SearchDataAsyntask() {
            @Override
            public void loadedSearchDataFromServer(ArrayList<SearchDatamodel> searchmodellist) {

                for (int i = 0; i < searchmodellist.size(); i++) {
                    SearchDatamodel viewmodeldata = searchmodellist.get(i);
                    String title = viewmodeldata.getTitle();
                    String imageurl = viewmodeldata.getImageurl();
                    String dnldUrl = viewmodeldata.getVideourl();
                    mListofSearchviewMoeldata.add(new SearchViewmodel(title, imageurl, dnldUrl));
                }

                searchDataReady.receivedSerachViewModelData(mListofSearchviewMoeldata);

            }
        });

    }


    class SearchAsyntask extends AsyncTask<String, String, ArrayList<SearchDatamodel>> {

        @Override
        protected ArrayList<SearchDatamodel> doInBackground(String... strings) {
            String responceSearchData = HttpManager.readDataFromServer(strings[0]);

            try {
                JSONObject jsonrootsearchobject = new JSONObject(responceSearchData);

                JSONArray jsonarray = jsonrootsearchobject.getJSONArray("Responsedetails");


                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject object = jsonarray.getJSONObject(i);
                    JSONArray array = object.getJSONArray("data_array");
                    for (int j = 0; j < array.length(); j++) {
                        JSONObject arrayobject = array.getJSONObject(j);
                        String title = arrayobject.getString("title");
                        String imageurl = arrayobject.getString("image_path");
                        String videourl = arrayobject.getString("dnld_url");
                        String canonicalname = arrayobject.getString("canonical_name");
                        mListofSearchData.add(new SearchDatamodel(title, imageurl, videourl, canonicalname));
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("laxman", "listsize" + mListofSearchData.size());
            return mListofSearchData;
        }
    }
}
