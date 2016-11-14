package com.bridgelabz.appystore.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.VideoviewRecyclerAdapter;
import com.bridgelabz.appystore.controller.EndlessRecyclerOnScrollListener;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchContentList;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;

import static com.bridgelabz.appystore.R.id.relativelayout;
import static com.bridgelabz.appystore.R.id.videoview;

public class VideoPlayerFragment extends Fragment implements View.OnClickListener {

    final ContentListViewmodel viewmodel = new ContentListViewmodel();
    // Video url
    String url;
    String mPid;
    String mCid;
    CategoryViewmodel categoryViewmodel = new CategoryViewmodel();
    ArrayList<ContentListViewmodel> mListofContent = new ArrayList<>();
    RecyclerView mVidoesdisplayrecyclerview;
    VideoviewRecyclerAdapter recyclerAdapter;
    int moffset = 5;
    VideoView video;
    View mView;
    ImageView mBack;
    RelativeLayout layout;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_video_player,container,false);
        mBack =(ImageView)mView.findViewById(R.id.backimage);
        layout = (RelativeLayout)mView.findViewById(R.id.activity_video_player);
        mBack.setOnClickListener(VideoPlayerFragment.this);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        mPid=bundle.getString("pid");
        mCid=bundle.getString("cid");
        video = (VideoView)mView. findViewById(videoview);
        playvideo(url);
        mVidoesdisplayrecyclerview = (RecyclerView) mView.findViewById(R.id.recyclerviewofvideos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mVidoesdisplayrecyclerview.setLayoutManager(linearLayoutManager);

        viewmodel.getContentListViewmodeldata(mPid, mCid, moffset, new FetchContentList() {
            @Override
            public void receivedContentViewData(ArrayList<ContentListViewmodel> viewmodelArrayList) {
                mListofContent = viewmodelArrayList;
                recyclerAdapter = new VideoviewRecyclerAdapter(mView.getContext(), mListofContent);
                mVidoesdisplayrecyclerview.setAdapter(recyclerAdapter);

            }
        });
        mVidoesdisplayrecyclerview.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                moffset = moffset + 5;
                viewmodel.getContentListViewmodeldata(mPid, mCid, moffset, new FetchContentList() {
                    @Override
                    public void receivedContentViewData(ArrayList<ContentListViewmodel> viewmodelArrayList) {

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

        mVidoesdisplayrecyclerview.addOnItemTouchListener(new RecyclerTouchListener(mView.getContext(), mVidoesdisplayrecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                video.stopPlayback();
                ContentListViewmodel dataposition = mListofContent.get(position);

        //        mLocalDb.addStoreDataToDataBase(new Historymodel(dataposition.getTitle(), dataposition.getImageurl(), dataposition.getVideourl()));
                playvideo(dataposition.getVideourl());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return mView;
    }


    public void playvideo(String url) {
        Uri uri = Uri.parse(url);
        // Initilizing the videoview
        // Settring the url
        video.setVideoURI(uri);

        //Mediacontrooler for the controolong the ideo
        MediaController mediaController = new MediaController(mView.getContext());

        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        //Starting the video
        video.start();
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(mView.getContext(),"message in back",Toast.LENGTH_LONG).show();
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}



