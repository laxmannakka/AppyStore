package com.bridgelabz.appystore.adapters;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RoundedCornersTransformation;
import com.bridgelabz.appystore.view.VideoPlayerFragment;

import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by bridgeit007 on 28/8/16.
 */

public class ContentListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> data;
    List<ContentListViewmodel> model;
    LayoutInflater layoutinflater;
    DataBaseHandler mLocalDb ;
    FragmentManager fragmentManager;
    String mPid;
    String mCid;


    public ContentListRecyclerAdapter(Context context, List<ContentListViewmodel> model, FragmentManager fm, String pid, String cid) {
        this.context = context;
        this.model = model;
        layoutinflater = layoutinflater.from(context);
        this.fragmentManager = fm;
        this.mPid = pid;
        this.mCid = cid;
        mLocalDb = new DataBaseHandler(context);
    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 * 2;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case 0:
                View view = layoutinflater.inflate(R.layout.item1, parent, false);
                return new ViewHolder0(view);
            case 2:
                View vieww = layoutinflater.inflate(R.layout.item2, parent, false);
                return new ViewHolder1(vieww);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContentListViewmodel viewmodel = model.get(position);
        switch (holder.getItemViewType()) {
            case 0:
                evenItems(holder, position, viewmodel);
                break;
            case 2:
                oddItems(holder, position, viewmodel);
               // evenItems(holder, position, viewmodel);

                break;
            default:
                break;
        }

    }

    public void evenItems(final RecyclerView.ViewHolder holder, final int position, final ContentListViewmodel model) {
      final  ViewHolder0 holder0 = (ViewHolder0) holder;
        holder0.textview.setText(model.getTitle());

        int sCorner = 25;
        int sMargin = 4;
        Glide.with(context)
                .load(model.getImageurl())
                .bitmapTransform(new RoundedCornersTransformation(context, sCorner, sMargin))
                .into(holder0.imageview);


        holder0.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(context, holder0.dot);
                menu.getMenuInflater().inflate(R.menu.menu, menu.getMenu());
                menu.show();//showing popup menu
            }
        });

        holder0.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VideoPlayerFragment fragment = new VideoPlayerFragment();
                // adding the data to loacal data base
                mLocalDb.addStoreDataToDataBase(new Historymodel(model.getTitle(), model.getImageurl(), model.getVideourl()));
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                //putting the url to bundle
                bundle.putString("url", model.getVideourl());
                // putting th cid to bundle
                bundle.putString("cid", mCid);
                // putting th pid to bundle
                bundle.putString("pid", mPid);
                fragment.setArguments(bundle);
                // repalcing the fragment
                transaction.replace(R.id.layout1, fragment);
                // commit the fragment
                transaction.commit();
            }
        });
    }

    public void oddItems(final RecyclerView.ViewHolder holder, final int position, final ContentListViewmodel model) {
        int sCorner = 25;
        int sMargin = 4;
        final ViewHolder1 holder2 = (ViewHolder1) holder;
        holder2.textview.setText(model.getTitle());
        holder2.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(context, holder2.dot);
                menu.setGravity(Gravity.BOTTOM);
                menu.getMenuInflater().inflate(R.menu.menu, menu.getMenu());
                menu.show();//showing popup menu
            }
        });

        holder2.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VideoPlayerFragment fragment = new VideoPlayerFragment();
                // adding the data to loacal data base
                mLocalDb.addStoreDataToDataBase(new Historymodel(model.getTitle(), model.getImageurl(), model.getVideourl()));
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                //putting the url to bundle
                bundle.putString("url", model.getVideourl());
                // putting th cid to bundle
                bundle.putString("cid", mCid);
                // putting th pid to bundle
                bundle.putString("pid", mPid);
                fragment.setArguments(bundle);
                // repalcing the fragment
                transaction.replace(R.id.layout1, fragment);
                // commit the fragment
                transaction.commit();
            }
        });

        Glide.with(context)
                .load(model.getImageurl())
                .bitmapTransform(new RoundedCornersTransformation(context, sCorner, sMargin))
                .into(holder2.imageview);
    }


    class ViewHolder0 extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;
        ImageView dot;
        ImageView video;

        public ViewHolder0(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            textview = (TextView) itemView.findViewById(R.id.titleview);
            dot = (ImageView) itemView.findViewById(R.id.dot1);
            video = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;
        ImageView dot;
        ImageView video;

        public ViewHolder1(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            textview = (TextView) itemView.findViewById(R.id.titleview);
            dot = (ImageView) itemView.findViewById(R.id.dot1);
            video = (ImageView) itemView.findViewById(R.id.imageView);
        } // ending of viewholder constructor
    } // end of viewholder class
}



