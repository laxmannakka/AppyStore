package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.viewmodel.SearchViewmodel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 16/9/16.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.MyviewHolder> {

    ArrayList<SearchViewmodel> mData;
    Context mContext;
    LayoutInflater inflater;


    public SearchRecyclerAdapter(ArrayList<SearchViewmodel> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
    }



    @Override
    public SearchRecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.search_item, parent, false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(SearchRecyclerAdapter.MyviewHolder holder, int position) {

        SearchViewmodel singlemodel = mData.get(position);
        holder.textview.setText(singlemodel.getTitle());
        Glide.with(mContext).load(singlemodel.getUrlimage()).diskCacheStrategy( DiskCacheStrategy.SOURCE ).into(holder.imageview);


    }

    @Override
    public int getItemCount() {
        Log.i("laxman","sizeis"+mData.size());
        return mData.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;
        public MyviewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            textview =(TextView)itemView.findViewById(R.id.titlenamedisplay);
        }
    }
}
