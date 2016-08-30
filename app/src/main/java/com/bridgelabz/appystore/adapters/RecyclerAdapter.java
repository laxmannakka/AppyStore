package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgeit007 on 28/8/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<String> data;
    LayoutInflater layoutInflater;
    List<ContentListViewmodel> model;

    public RecyclerAdapter(Context context, List<ContentListViewmodel> model) {
        this.context = context;
      this.model=model;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.rowimage, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
       /* holder.imageView.setImageResource(Integer.parseInt(data.get(position)));*/
        ContentListViewmodel data = model.get(position);
        holder.imageView.setImageBitmap(data.getContentImage());
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
