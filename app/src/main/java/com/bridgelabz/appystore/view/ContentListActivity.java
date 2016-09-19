package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.ContentListRecyclerAdapter;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchContentList;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;

/**
 * <Purpose>
 * 1.this class shows the Content list of data
 * 2.list of data getting from view model  and showing in Recycle view
 *
 *
 * */


public class ContentListActivity extends AppCompatActivity {



    // arrayList of content list model
    public ArrayList<ContentListViewmodel> viewmodeldata = new ArrayList<>();

    //Declaring the Recyclerview
    RecyclerView mRecyclerView;
    // Declaring the recycler Adapter
    ContentListRecyclerAdapter contentListRecyclerAdapter;
    // for parent category id
    String mPid;
    // for category id
    String mCid;
    // starting data of json which is in server
    int mOffset = 0;
    // for the mSeach image
    ImageView mSerchimage;
    //Declaring the Database Handler class
    DataBaseHandler mLocalDb;
    // for the history
    ImageView mHistory;
    ImageView mHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list_view);
        // getting the Intent
        Intent mIntent = getIntent();
        // getting the mCid and mPid values through the intent
        mPid = mIntent.getExtras().getString("mPid");
        mCid = mIntent.getExtras().getString("mCid");

        // Initializing the all fields
        mSerchimage = (ImageView) findViewById(R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mHistory= (ImageView) findViewById(R.id.history);
        mHome = (ImageView) findViewById(R.id.home);
        // Creation of object of DataBaseHandler class passing argument context of this class
        mLocalDb = new DataBaseHandler(ContentListActivity.this);

        // Creating the object of LinearLayoutManager class
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        // Setting layout for recyclerview
        mRecyclerView.setLayoutManager(linearLayoutManager);


        // Creating the object of Contentlist
        final ContentListViewmodel viewmodel = new ContentListViewmodel();

        viewmodel.getContentListViewmodeldata(mPid, mCid, mOffset, new FetchContentList() {
            @Override
            public void receivedContentViewData(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                viewmodeldata = viewmodelArrayList;
                contentListRecyclerAdapter = new ContentListRecyclerAdapter(getBaseContext(), viewmodeldata);
                mRecyclerView.setAdapter(contentListRecyclerAdapter);

            }
        });
        mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                mOffset = mOffset + 5;
                viewmodel.getContentListViewmodeldata(mPid, mCid, mOffset, new FetchContentList() {
                    @Override
                    public void receivedContentViewData(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                        ArrayList<ContentListViewmodel> model = new ArrayList<>();
                        model = viewmodelArrayList;

                        for (int i = 0; i < model.size(); i++) {
                            viewmodeldata.add(model.get(i));
                        }
                        contentListRecyclerAdapter.notifyDataSetChanged();
                    }
                });


            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ContentListViewmodel contentListdata = viewmodeldata.get(position);
                Toast.makeText(getApplicationContext(), "messageusrl" + contentListdata.getVideourl(), Toast.LENGTH_LONG).show();
                Intent videoview = new Intent(ContentListActivity.this, VideoPlayerActivity.class);
                mLocalDb.addStoreDataToDataBase(new Historymodel(contentListdata.getTitle(),contentListdata.getImageurl(),contentListdata.getVideourl()));
                videoview.putExtra("videourl", contentListdata.getVideourl());
                videoview.putExtra("mPid", mPid);
                videoview.putExtra("mCid", mCid);
                startActivity(videoview);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mSerchimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serchview = new Intent(ContentListActivity.this,SearchActivity.class);
                serchview.putExtra("mPid", mPid);
                serchview.putExtra("mCid", mCid);
                startActivity(serchview);
            }
        });

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Historyview = new Intent(ContentListActivity.this,HistoryActivity.class);
                startActivity(Historyview);

            }
        });
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent categorylistview = new Intent(ContentListActivity.this,CategoryActivity.class);
                startActivity(categorylistview);
            }
        });

    }
}

