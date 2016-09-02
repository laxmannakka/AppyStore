package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bridgeit007 on 28/8/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<String> data;
    List<ContentListViewmodel> model;
    LayoutInflater layoutinflater;

    public RecyclerAdapter(Context context, List<ContentListViewmodel> model) {
        this.context = context;
        this.model = model;
        layoutinflater=layoutinflater.from(context);
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutinflater.inflate(R.layout.rowimage, parent, false);
               ViewHolder viewHolder = new ViewHolder(view);


      //  RowimageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rowimage, parent, false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        ContentListViewmodel data = model.get(position);
        Bitmap theBitmap;
        Glide.with(context).load(data.getImageurl()).into(holder.imageView);
        holder.textview.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textview;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.myImageView);
            textview =(TextView)itemView.findViewById(R.id.myImageViewText);
        }


    }

}
