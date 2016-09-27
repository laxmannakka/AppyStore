package com.bridgelabz.appystore.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bridgelabz.appystore.model.Historymodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgeit007 on 17/9/16.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "History";
    // Table Name
    public static String TABLE_NAME = "data";
    //  contact names and columns
    private static final String TITLE ="title";
    private static final String KEY_IMAGE_URL = "imageurl";
    private static final String KEY_VIDEO_URL = "videourl";
    public static int DATABASE_NUMBER = 2;


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String historytable= "CREATE TABLE " + TABLE_NAME + "(" + TITLE + " TEXT not null," + KEY_IMAGE_URL + "  PRIMARY KEY,"
                + KEY_VIDEO_URL + " TEXT not null)";
        sqLiteDatabase.execSQL(historytable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
  public  void addStoreDataToDataBase(Historymodel model)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE, model.getTitle());
        values.put(KEY_IMAGE_URL, model.getImageurl());
        values.put(KEY_VIDEO_URL,model.getVideourl());
        // Inserting Row
        db.insert(TABLE_NAME,null, values);
        db.close(); // Closing database connection
    }
    public List<Historymodel > getAllStoredData() {

        List<Historymodel> historyList = new ArrayList<Historymodel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Historymodel model = new Historymodel();

                model.setTitle(cursor.getString(0));
                model.setImageurl(cursor.getString(1));
                model.setVideourl(cursor.getString(2));

                // Adding contact to list
            historyList.add(model);
            } while (cursor.moveToNext());
        }

        // return contact list
        return historyList;
    }
}
