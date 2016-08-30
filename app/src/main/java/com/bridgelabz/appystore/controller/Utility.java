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
 * Created by bridgeit007 on 23/8/16.
 */

public class Utility {


    // Declaring the Headers Which we  need
  public static   String X_APPY_USERID = "290903782";
    public static  String X_APPY_IMEI = "353368070301951";
    public static String X_APPY_PCP_ID = "999";
    public static String X_APPY_CAMPAIGN_ID = "8700441600";
    public static String X_APPY_APP_TYPE = " lite";
    public  static String X_APPY_TTR = "10800000";
    public static String X_APPY_UTYPE = "O";
    public static String X_APPY_MSISDN = "0";
    public static String X_APPY_IS_NEW_USER = "N";
    public static String X_APPY_API_KEY = "gh610rt23eqwpll";
    public static String X_APPY_UserAgent="Mozilla/5.0 (Linux; Android 5.0.2; Panasonic ELUGA Switch Build/LRX22G; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.81 Mobile Safari/537.36";




    //Method to convert the stream data into the string

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


    // This function takes the url as the input and reads the data from server
    public static String readDataFromServer(String uri) {

        // Declaring the varible urldata
        InputStream urldata;
        try {
            // Initilizing the url
            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
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
            urlConnection.addRequestProperty("X_APPY_UserAgent",X_APPY_UserAgent);
            urlConnection.setRequestMethod("GET");
            // reading the data from url
            urldata = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(urldata); // calling the function to convertstream to string
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // This function convert the Input stream to string
    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";

    }



}
