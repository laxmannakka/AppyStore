package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.RecyclerAdapter;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchContentLIst;
import com.bridgelabz.appystore.interfaces.FetchView;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;
import java.util.List;

public class ContentListView extends AppCompatActivity {


    ArrayList<ContentListView> mListofContent;
    int value;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private GridLayoutManager lLayout;
    String pid;
    String cid;
    int moffset=0;

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


        viewmodel.getContentListViewmodeldata(pid, cid,moffset, new FetchContentLIst() {
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
                moffset=moffset+5;
                viewmodel.getContentListViewmodeldata(pid, cid,moffset ,new FetchContentLIst() {
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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
            ContentListViewmodel dataposition = viewmodeldata.get(position);
                Toast.makeText(getApplicationContext(),"messageusrl"+dataposition.getVideourl(),Toast.LENGTH_LONG).show();
                Intent videoview = new Intent(ContentListView.this,VideoPlayer.class);
                videoview.putExtra("videourl",dataposition.getVideourl());
                startActivity(videoview);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}

