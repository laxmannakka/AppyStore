package com.bridgelabz.appystore.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.HistoryRecyclerAdapter;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment{

    // this Variable stores the data to localdb
    DataBaseHandler mLocalDb;
    // for displaying the data
    RecyclerView mRecyclerview;
    // List of History model initilization
    List<Historymodel> mListofStoredata= new ArrayList<Historymodel>();
    // for view
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflating the view
        mView = inflater.inflate(R.layout.recyclerview,container,false);
        // Creating the object of local DatabaseHandlerclass
        mLocalDb = new DataBaseHandler(mView.getContext());
        //initilization of Recyclerview
        mRecyclerview = (RecyclerView) mView.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        // getting the data from data base
        mListofStoredata=mLocalDb.getAllStoredData();
        // Creating the object of HistoryRecyclerAdapter
        HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(mView.getContext(),mListofStoredata);
        mRecyclerview.setAdapter(adapter);
        // recycler view on touch item
        mRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(mView.getContext(), mRecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Historymodel model = mListofStoredata.get(position);

            } // end of onCliclk function
            @Override
            public void onLongClick(View view, int position) {

            } // end of onLong click
        }));
        return  mView;
    } // end of Oncreateiew

} // end of class
