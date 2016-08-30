package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.RecyclerAdapter;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.FetchContentLIst;
import com.bridgelabz.appystore.interfaces.FetchView;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;
import java.util.List;

public class ContentListView extends AppCompatActivity {


    ArrayList<ContentListView> mListofContent;
    int value;
    RecyclerView recyclerView;
    List<String> mList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    private GridLayoutManager lLayout;
    String pid;
    String cid;
    public ArrayList<ContentListViewmodel> viewmodeldata = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list_view);
        Intent mIntent = getIntent();
        pid = mIntent.getExtras().getString("pid");
        cid = mIntent.getExtras().getString("cid");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        /*lLayout = new GridLayoutManager(ContentListView.this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lLayout);*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

       /* for (int i = 0; i < 50; i++) {
            mList.add(String.valueOf(R.mipmap.ic_launcher));
        }
        recyclerAdapter = new RecyclerAdapter(getBaseContext(),mList);
        recyclerView.setAdapter(recyclerAdapter);*/

        final ContentListViewmodel viewmodel = new ContentListViewmodel();


        viewmodel.getContentListViewmodeldata(pid, cid, new FetchContentLIst() {
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

                viewmodel.getContentListViewmodeldata(pid, cid, new FetchContentLIst() {
                    @Override
                    public void getcontentviewdata(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                        ArrayList<ContentListViewmodel> model = new ArrayList<>();
                        model = viewmodelArrayList;

                        for(int i=0;i<model.size();i++){
                            viewmodeldata.add(model.get(i));
                        }

                        recyclerAdapter.notifyDataSetChanged();

                    }
                });


            }
        });
    }
}

