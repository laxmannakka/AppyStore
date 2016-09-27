package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.databinding.ItemViewBinding;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 13/9/16.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {
    Context mContext;
    ArrayList<CategoryViewmodel> mData;
    LayoutInflater layoutinflater;

    public CategoryRecyclerAdapter(Context mContext, ArrayList<CategoryViewmodel> mData) {
        this.mContext = mContext;
        this.mData = mData;
        layoutinflater=layoutinflater.from(mContext);

    }


    @Override
    public CategoryRecyclerAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = layoutinflater.inflate(R.layout.custom_layout_bg, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);


        return viewHolder;*/
        ItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_view,parent,false);

        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CategoryRecyclerAdapter.CategoryViewHolder holder, int position) {

        CategoryViewmodel modeldata = mData.get(position);
        holder.mItemViewBinding.titletextview.setText(modeldata.getTitle());
        Glide.with(mContext).load(modeldata.getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.mItemViewBinding.carosalbg);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {



        ItemViewBinding mItemViewBinding;
        ImageView imageView;
        TextView title;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.carosalbg);
            title =(TextView)itemView.findViewById(R.id.titletextview);
        }
        public CategoryViewHolder(final ItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());

            mItemViewBinding = itemViewBinding;
        }
    }
}


