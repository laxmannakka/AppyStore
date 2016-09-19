package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.VideoviewRecyclerAdapter;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchContentLIst;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;

public class VideoPlayerActivity extends AppCompatActivity {
    final ContentListViewmodel viewmodel = new ContentListViewmodel();
    // Video url
    String url;
    String pid;
    String cid;
    CategoryViewmodel categoryViewmodel = new CategoryViewmodel();
    ArrayList<ContentListViewmodel> mListofContent = new ArrayList<>();
    RecyclerView mVidoesdisplayrecyclerview;
    VideoviewRecyclerAdapter recyclerAdapter;
    int moffset = 5;
    VideoView video;
    ImageView mbackActivityimage;
    DataBaseHandler mLocalDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        // Getting the intent
        Intent mintent = getIntent();
        // getting the String from intent
        url = mintent.getExtras().getString("videourl");
        cid = mintent.getExtras().getString("cid");
        pid = mintent.getExtras().getString("pid");


        mVidoesdisplayrecyclerview = (RecyclerView) findViewById(R.id.recyclerviewofvideos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mVidoesdisplayrecyclerview.setLayoutManager(linearLayoutManager);
        video = (VideoView) findViewById(R.id.videoview);
        mbackActivityimage = (ImageView) findViewById(R.id.backimage);
        mLocalDb = new DataBaseHandler(VideoPlayerActivity.this);

        playvideo(url);

     /*   // Parsing the uri
            Uri uri = Uri.parse(url);
            // Initilizing the videoview
            // Settring the url
            video.setVideoURI(uri);

            //Mediacontrooler for the controolong the ideo
            MediaController mediaController = new MediaController(this);

            mediaController.setAnchorView(video);
            video.setMediaController(mediaController);
            //Starting the video
            video.start();
*/


        viewmodel.getContentListViewmodeldata(pid, cid, moffset, new FetchContentLIst() {
            @Override
            public void getcontentviewdata(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                mListofContent = viewmodelArrayList;
                recyclerAdapter = new VideoviewRecyclerAdapter(getBaseContext(), mListofContent);
                mVidoesdisplayrecyclerview.setAdapter(recyclerAdapter);

            }
        });


        mVidoesdisplayrecyclerview.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                moffset = moffset + 5;
                viewmodel.getContentListViewmodeldata(pid, cid, moffset, new FetchContentLIst() {
                    @Override
                    public void getcontentviewdata(ArrayList<ContentListViewmodel> viewmodelArrayList) {

                        ArrayList<ContentListViewmodel> model = new ArrayList<>();
                        model = viewmodelArrayList;

                        for (int i = 0; i < model.size(); i++) {
                            mListofContent.add(model.get(i));
                        }

                        recyclerAdapter.notifyDataSetChanged();

                    }
                });

            }
        });


        mVidoesdisplayrecyclerview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mVidoesdisplayrecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                video.stopPlayback();
                ContentListViewmodel dataposition = mListofContent.get(position);

                mLocalDb.addStoreDataToDataBase(new Historymodel(dataposition.getTitle(), dataposition.getImageurl(), dataposition.getVideourl()));
                Toast.makeText(getApplicationContext(), "messageusrl" + dataposition.getVideourl(), Toast.LENGTH_LONG).show();
                playvideo(dataposition.getVideourl());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mbackActivityimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contentListActivity = new Intent(VideoPlayerActivity.this, ContentListActivity.class);
                contentListActivity.putExtra("pid", pid);
                contentListActivity.putExtra("cid", cid);
                startActivity(contentListActivity);
            }
        });

    }

    public void playvideo(String url) {
        Uri uri = Uri.parse(url);
        // Initilizing the videoview
        // Settring the url
        video.setVideoURI(uri);

        //Mediacontrooler for the controolong the ideo
        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        //Starting the video
        video.start();

    }


}



