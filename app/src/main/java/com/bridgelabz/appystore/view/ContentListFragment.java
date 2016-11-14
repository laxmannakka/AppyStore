package com.bridgelabz.appystore.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.ContentListRecyclerAdapter;
import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchContentList;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.DialogBox;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;

/**
 * This class shows the Content list of data in a fragment
 */

public class ContentListFragment  extends Fragment{

    // for display the view
    View mView;
    // for displaying the data
    RecyclerView mRecyclerview;
   // parent category id
    String mPid;
    // category id
    String mCid;
    // offset from where the start the list
    int mOffset=0;
    // DataBaseHandler class diclaration
    DataBaseHandler mLocalDb;
    // Array list of content list view model
    public ArrayList<ContentListViewmodel> viewmodeldata = new ArrayList<>();
    // Initilization of ContentListRecycler adapter
    ContentListRecyclerAdapter contentListRecyclerAdapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Bundle to get the data passed through the fragment
        Bundle bundle = getArguments();
        mPid = bundle.getString("pid"); // getting the pid
        mCid=bundle.getString("cid");  // getting the cid
        // inflating the layout
        mView = inflater.inflate(R.layout.recyclerview, container, false);
        //Creation of Database Handler class
        mLocalDb = new DataBaseHandler(mView.getContext());
        // Initilization of recycler view
        mRecyclerview = (RecyclerView) mView.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        // Setting layout for recyclerview
        mRecyclerview.setLayoutManager(linearLayoutManager);
        // creation of object of content list view model
        final ContentListViewmodel viewmodel = new ContentListViewmodel();

        if(new CategoryController().isOnline(mView.getContext())) {

            // calling the function here
            viewmodel.getContentListViewmodeldata(mPid, mCid, mOffset, new FetchContentList() {
                @Override
                public void receivedContentViewData(ArrayList<ContentListViewmodel> viewmodelArrayList) {
                    viewmodeldata = viewmodelArrayList;
                    contentListRecyclerAdapter = new ContentListRecyclerAdapter(mView.getContext(), viewmodeldata,getFragmentManager(),mPid,mCid);
                    mRecyclerview.setAdapter(contentListRecyclerAdapter);
                } // closing the receivedContentViewData
            }); // end of calling function

            //recycler view setonscroll lisner
            mRecyclerview.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
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
                            } // end of for
                            contentListRecyclerAdapter.notifyDataSetChanged();
                        }
                    }); // end of getContentListViewmodeldata
                } // end of on load more
            });// end of function

        }
        else {
            DialogBox.showAlertDioalouge(mView.getContext());
        }


        return mView; // returnig  the view
    }
}
