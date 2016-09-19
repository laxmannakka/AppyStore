package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.CategoryRecyclerAdapter;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchCategoryList;
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
    // for displyaing the data
    RecyclerView mCatogoryRecyclerview;
    ImageView mSearchimageview;
    String mPid;
    String mCid;
    ImageView mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catogory_listview);

        mCatogoryRecyclerview = (RecyclerView) findViewById(R.id.categoryrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mCatogoryRecyclerview.setLayoutManager(linearLayoutManager);
        mListofContent = new ArrayList<>();
        mSearchimageview = (ImageView) findViewById(R.id.categorysearch);
        mHistory = (ImageView) findViewById(R.id.history);
        // Creating tha object of Category viewmodel
        CategoryViewmodel categoryViewmodel = new CategoryViewmodel();
        // getting the viewmodel data
        categoryViewmodel.getCategoryViewModelData(new FetchCategoryList() {

            @Override
            public void receivedCategoryViewData(ArrayList<CategoryViewmodel> viewmodelArrayList) {
                mListofContent = viewmodelArrayList;
                CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(CategoryActivity.this, mListofContent);
                mCatogoryRecyclerview.setAdapter(adapter);

            }
        });

        // Recyclerview Touch Lisner
        mCatogoryRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mCatogoryRecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CategoryViewmodel categoryviewmodel = mListofContent.get(position);
                mPid = categoryviewmodel.getPid();
                mCid = categoryviewmodel.getCid();
                Intent contentlist = new Intent(CategoryActivity.this, ContentListActivity.class);
                contentlist.putExtra("mPid", mPid);
                contentlist.putExtra("mCid", mCid);
                startActivity(contentlist);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }) {
        });


        mSearchimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serchview = new Intent(CategoryActivity.this,SearchActivity.class);
                serchview.putExtra("mPid", mPid);
                serchview.putExtra("mCid", mCid);
                startActivity(serchview);
            }
        });

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Historyview = new Intent(CategoryActivity.this,HistoryActivity.class);
                startActivity(Historyview);

            }
        });
    }
}






