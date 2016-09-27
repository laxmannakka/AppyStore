package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.utility.RoundedCornersTransformation;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bridgeit007 on 28/8/16.
 */

public class ContentListRecyclerAdapter extends RecyclerView.Adapter<ContentListRecyclerAdapter.ViewHolder> {
    Context context;
    List<String> data;
    List<ContentListViewmodel> model;
    LayoutInflater layoutinflater;

    public ContentListRecyclerAdapter(Context context, List<ContentListViewmodel> model) {
        this.context = context;
        this.model = model;
        layoutinflater=layoutinflater.from(context);
    }

    @Override
    public ContentListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutinflater.inflate(R.layout.rowimage, parent, false);
               ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ContentListRecyclerAdapter.ViewHolder holder, int position) {
        ContentListViewmodel data = model.get(position);
          int sCorner = 25;
         int sMargin = 4;
//        Glide.with(context).load(data.getImageurl()).diskCacheStrategy( DiskCacheStrategy.SOURCE ).into(holder.imageview);

       // Glide.with(context).load(data.getImageurl()).diskCacheStrategy( DiskCacheStrategy.SOURCE ).into(holder.imageview);

       /* Glide.with(context).load(data.getImageurl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imageview) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.imageview.setImageDrawable(circularBitmapDrawable);
            }
        });*/
        Glide.with(context)
                .load(data.getImageurl())
                .bitmapTransform(new RoundedCornersTransformation( context,sCorner, sMargin))
                .into(holder.imageview);

        holder.textview.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;


        public ViewHolder(View itemView) {
            super(itemView);

            imageview = (ImageView) itemView.findViewById(R.id.myImageView);
            textview =(TextView)itemView.findViewById(R.id.myImageViewText);
        }
    }



}
