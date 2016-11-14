package com.bridgelabz.appystore.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgeit007 on 19/9/16.
 */


/*
*    String catogory_name;
            String category_id;
            String parent_category_id;
            String parent_category_name;*/

public class CategoryDataBaseHandler extends SQLiteOpenHelper {

    //  contact names and columns
    private static final String CATEGORY_NAME = "categoryname";
    private static final String CATEGORY_ID = "cid";
    private static final String PARENT_CATEGORY_NAME = "parentcategoryname";
    private static final String PARENT_CATEGORY_ID = "pid";
    private static final String URL = "imageurl";
    public static String DATABASE_NAME = "Categorydata";
    // Table Name
    public static String TABLE_NAME = "categorylist";
    public static int DATABASE_NUMBER = 1;


    public CategoryDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_NUMBER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String categoryListTable = "CREATE TABLE " + TABLE_NAME + "(" + CATEGORY_NAME + " PRIMARY KEY," + CATEGORY_ID + "  TEXT not null," + PARENT_CATEGORY_ID + " TEXT not null,"
                + URL + " TEXT not null)";
        sqLiteDatabase.execSQL(categoryListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addStoreDataToDataBase(CategoryViewmodel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY_NAME, model.getTitle()); // Contact Name
        values.put(CATEGORY_ID, model.getCid()); // Contact Phone Number
        values.put(PARENT_CATEGORY_ID, model.getPid());
        values.put(URL, model.getUrl());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<CategoryViewmodel> getAllStoredData() {

        ArrayList<CategoryViewmodel> viewModelList = new ArrayList<CategoryViewmodel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryViewmodel model = new CategoryViewmodel();

                model.setTitle(cursor.getString(0));
                model.setCid(cursor.getString(1));
                model.setPid(cursor.getString(2));
                model.setUrl(cursor.getString(3));

                // Adding contact to list
                viewModelList.add(model);
            } while (cursor.moveToNext());
        }

        // return list
        return viewModelList;
    }
}
