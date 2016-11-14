package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.utility.RoundedCornersTransformation;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by bridgeit007 on 14/9/16.
 */

public class VideoviewRecyclerAdapter extends RecyclerView.Adapter<VideoviewRecyclerAdapter.MyViewHolder> {
    Context context;
    List<ContentListViewmodel> model;
    LayoutInflater layoutinflater;

    public VideoviewRecyclerAdapter(Context context, List<ContentListViewmodel> model) {
        this.context = context;
        this.model = model;
        layoutinflater = layoutinflater.from(context);
    }

    @Override
    public VideoviewRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutinflater.inflate(R.layout.video_layout_image, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoviewRecyclerAdapter.MyViewHolder holder, int position) {
        ContentListViewmodel data = model.get(position);
        int sCorner = 25;
        int sMargin = 4;

        Glide.with(context)
                .load(data.getImageurl())
                .bitmapTransform(new RoundedCornersTransformation( context,sCorner, sMargin))
                .into(holder.video_images);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView video_images;
        public MyViewHolder(View itemView) {
            super(itemView);
            video_images = (ImageView) itemView.findViewById(R.id.imagevideo);
        }
    }
}
