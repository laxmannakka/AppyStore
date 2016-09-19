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
import com.bridgelabz.appystore.adapters.RecyclerAdapter;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchContentLIst;
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
    RecyclerView recyclerView;
    // Declaring the recycler Adapter
    RecyclerAdapter recyclerAdapter;
    // for parent category id
    String pid;
    // for category id
    String cid;
    // starting data of json which is in server
    int moffset = 0;
    // for the mSeach image
    ImageView mSerchimage;
    //Declaring the Database Handler class
    DataBaseHandler mLocalDb;

    // for the history
    ImageView mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list_view);
        // getting the Intent
        Intent mIntent = getIntent();
        // getting the cid and pid values through the intent
        pid = mIntent.getExtras().getString("pid");
        cid = mIntent.getExtras().getString("cid");

        // Initializing the all fields
        mSerchimage = (ImageView) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mHistory= (ImageView) findViewById(R.id.history);

        // Creation of object of DataBaseHandler class passing argument context of this class
        mLocalDb = new DataBaseHandler(ContentListActivity.this);

        // Creating the object of LinearLayoutManager class
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        // Setting layout for recyclerview
        recyclerView.setLayoutManager(linearLayoutManager);


        // Creating the object of Contentlist
        final ContentListViewmodel viewmodel = new ContentListViewmodel();

        /**
         * Function getContentListViewmodeldata
         * @Param pid parent catogory id
         * @param cid Category id
         * @Param Interface FetchContentList
         * */
        viewmodel.getContentListViewmodeldata(pid, cid, moffset, new FetchContentLIst() {
            @Override
            public void getcontentviewdata(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                viewmodeldata = viewmodelArrayList;
                recyclerAdapter = new RecyclerAdapter(getBaseContext(), viewmodeldata);
                recyclerView.setAdapter(recyclerAdapter);

            }
        });
        recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                moffset = moffset + 5;
                viewmodel.getContentListViewmodeldata(pid, cid, moffset, new FetchContentLIst() {
                    @Override
                    public void getcontentviewdata(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                        ArrayList<ContentListViewmodel> model = new ArrayList<>();
                        model = viewmodelArrayList;

                        for (int i = 0; i < model.size(); i++) {
                            viewmodeldata.add(model.get(i));
                        }

                        recyclerAdapter.notifyDataSetChanged();

                    }
                });


            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ContentListViewmodel contentListdata = viewmodeldata.get(position);
                Toast.makeText(getApplicationContext(), "messageusrl" + contentListdata.getVideourl(), Toast.LENGTH_LONG).show();
                Intent videoview = new Intent(ContentListActivity.this, VideoPlayer.class);


                mLocalDb.addStoreDataToDataBase(new Historymodel(contentListdata.getTitle(),contentListdata.getImageurl(),contentListdata.getVideourl()));
                videoview.putExtra("videourl", contentListdata.getVideourl());
                videoview.putExtra("pid", pid);
                videoview.putExtra("cid", cid);
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
                serchview.putExtra("pid", pid);
                serchview.putExtra("cid", cid);
                startActivity(serchview);
            }
        });

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Historyview = new Intent(ContentListActivity.this,HistoryView.class);
                startActivity(Historyview);

            }
        });

    }
}

