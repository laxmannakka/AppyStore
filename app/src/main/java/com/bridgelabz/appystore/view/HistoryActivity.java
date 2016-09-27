package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.HistoryRecyclerAdapter;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    DataBaseHandler mLocalDb;
    RecyclerView mRecyclerview;
    ImageView mBackbutton;
    List<Historymodel> listofStoredata= new ArrayList<Historymodel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        mLocalDb= new DataBaseHandler(HistoryActivity.this);
        mRecyclerview = (RecyclerView) findViewById(R.id.historyrecyclerview);
        mBackbutton= (ImageView) findViewById(R.id.imagebackck);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerview.setLayoutManager(linearLayoutManager);

        listofStoredata=mLocalDb.getAllStoredData();
        HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(HistoryActivity.this,listofStoredata);
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Historymodel model = listofStoredata.get(position);
                Intent videoview = new Intent(HistoryActivity.this,SearchItemVideoPlayer.class);
                videoview.putExtra("videourl",model.getVideourl());
                startActivity(videoview);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mBackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
