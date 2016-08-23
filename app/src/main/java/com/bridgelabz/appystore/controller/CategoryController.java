package com.bridgelabz.appystore.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by bridgeit007 on 19/8/16.
 * <Purpose>
 *
 *
 */

public class CategoryController {
    String X_APPY_USERID = "290903782";
    String X_APPY_IMEI = "353368070301951";
    String X_APPY_PCP_ID = "999";
    String X_APPY_CAMPAIGN_ID = "8700441600";
    String X_APPY_APP_TYPE = " lite";
    String X_APPY_TTR = "10800000";
    String X_APPY_UTYPE = "O";
    String X_APPY_MSISDN = "0";
    String X_APPY_IS_NEW_USER = "N";
    String X_APPY_API_KEY = "gh610rt23eqwpll";
    public CategoryController() {
    }



    //--Method to convert the stream data into the string

    public static Bitmap imageDownload(String uri) {
        URL url;
        InputStream image;
        try {
            url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            image = new BufferedInputStream(httpURLConnection.getInputStream());
            return BitmapFactory.decodeStream(image);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String readDataFromServer(String uri) {

        InputStream in;
        try {
            URL url = new URL(uri);


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            // urlConnection.setRequestMethod("GET");
            urlConnection.addRequestProperty("X_APPY_USERID", X_APPY_USERID);
            urlConnection.addRequestProperty("X_APPY_IMEI", X_APPY_IMEI);
            urlConnection.addRequestProperty("X_APPY_PCP_ID", X_APPY_PCP_ID);
            urlConnection.addRequestProperty("X_APPY_CAMPAIGN_ID", X_APPY_CAMPAIGN_ID);
            urlConnection.addRequestProperty("X_APPY_APP_TYPE", X_APPY_APP_TYPE);
            urlConnection.addRequestProperty("X_APPY_TTR", X_APPY_TTR);
            urlConnection.addRequestProperty("X_APPY_UTYPE", X_APPY_UTYPE);
            urlConnection.addRequestProperty("X_APPY_MSISDN", X_APPY_MSISDN);
            urlConnection.addRequestProperty("X_APPY_IS_NEW_USER", X_APPY_IS_NEW_USER);
            urlConnection.addRequestProperty("X_APPY_API_KEY", X_APPY_API_KEY);
            urlConnection.setRequestMethod("GET");
            in = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";

    }

    public static ArrayList CatogoryViewtoCatogoryviewmodel(ArrayList<Categorymodel> viewmodel){

        ArrayList<CategoryViewmodel> viewmodellist = new ArrayList<>();
        for(int i=0;i<viewmodel.size();i++){
            Categorymodel model = viewmodel.get(i);
            String title = model.getTitle();
            Bitmap image = model.getImage();
            viewmodellist.add(new CategoryViewmodel(title,image));
        }
        return viewmodellist;
    }


}


