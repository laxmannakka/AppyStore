package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.CategoryRecyclerAdapter;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchView;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

/**
 * This class shows the Categories List
 *
 * */



public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "CategoryActivity";
    // Arraylist storing the of Categoryviewmodel list this data getting from viewmodel
    ArrayList<CategoryViewmodel> mListofContent;
    //for log purpose
    String VIEW_LOG_TAG = "error";
    // Showing the progress bar
    ProgressBar mSpinner;
    //for display
    View mView;
    //for display the text
    TextView mTextView;
    //for display the title
    TextView mDisplaytitle;
    RecyclerView mCatogoryRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catogory_listview);
        mCatogoryRecyclerview = (RecyclerView) findViewById(R.id.categoryrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mCatogoryRecyclerview.setLayoutManager(linearLayoutManager);

        //  final ListView listview = (ListView) findViewById(R.id.listview);
        mListofContent = new ArrayList<CategoryViewmodel>();
        // Creating tha object of Category viewmodel
        CategoryViewmodel categoryViewmodel = new CategoryViewmodel();



        // getting the viewmodel data
        categoryViewmodel.getViewmodeldata(new FetchView() {

            @Override
            public void getviewdata(ArrayList<CategoryViewmodel> viewmodelArrayList) {
                Log.i(TAG, "getviewdata: " + "INSIDE GETVIEWDATA");
                mListofContent = viewmodelArrayList;
                Log.i(TAG, "getviewdata: SIZE" + "\n" + viewmodelArrayList.size() + "\n" + mListofContent.size());

                CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(CategoryActivity.this, mListofContent);

                mCatogoryRecyclerview.setAdapter(adapter);

            }
        });

        // Recyclerview Touch Lisner
        mCatogoryRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mCatogoryRecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CategoryViewmodel model2 = mListofContent.get(position);
                String pid = model2.getPid();
                String cid = model2.getCid();
                Intent contentlist = new Intent(CategoryActivity.this, ContentListViewActivity.class);
                contentlist.putExtra("pid", pid);
                contentlist.putExtra("cid", cid);
                startActivity(contentlist);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }) {
        });


    }
}






