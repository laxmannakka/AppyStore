package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.model.Historymodel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by bridgeit007 on 17/9/16.
 */

public class HistoryRecyclerAdapter extends  RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>{
    Context mContext;
    List<Historymodel> mData;
    LayoutInflater inflater;

    public HistoryRecyclerAdapter(Context mcontext, List<Historymodel> mData) {
        this.mContext= mcontext;
        this.mData = mData;
        inflater=LayoutInflater.from(mcontext);
    }

    @Override
    public HistoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item1, parent, false);
        MyViewHolder myviewHolder = new MyViewHolder(view);

        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryRecyclerAdapter.MyViewHolder holder, int position) {
        Historymodel model = mData.get(position);
        holder.textview.setText(model.getTitle());
        Glide.with(mContext).load(model.getImageurl()).diskCacheStrategy(DiskCacheStrategy.SOURCE ).into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            textview =(TextView)itemView.findViewById(R.id.titleview);
        }
    }
}
