package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.librarycarosal.CoverFlowAdapter;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by bridgeit007 on 30/8/16.
 */


public class MyCoverFlowAdapter extends CoverFlowAdapter {
    LayoutInflater inflater;
    View view;
    Context mcontext;
    ArrayList<CategoryViewmodel> viewmodellist = new ArrayList<>();
    private boolean dataChanged;


    /**
     * Constructor of MyCoverfloew adapter
     * <param>
     */
    public MyCoverFlowAdapter(Context context, ArrayList<CategoryViewmodel> model) {
        this.mcontext = context;
        this.viewmodellist = model;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.custom_layout, null);
    }


    @Override
    public int getCount() {
        return viewmodellist.size();
    }

    @Override
    public Bitmap getImage(final int position) {

       Bitmap image = viewmodellist.get(position).getImage();

            Bitmap bgimage = getScreenViewBitmap(view, image);
            return image;
    }


    public Bitmap getScreenViewBitmap(final View view, Bitmap bitmap) {
        view.setDrawingCacheEnabled(true);

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        view.buildDrawingCache(true);
        Bitmap l=view.getDrawingCache();
        Bitmap b = Bitmap.createBitmap(l);
        view.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }


}