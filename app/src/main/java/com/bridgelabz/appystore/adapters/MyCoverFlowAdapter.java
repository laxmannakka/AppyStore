package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.librarycarosal.CoverFlowAdapter;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

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

        for(CategoryViewmodel categoryViewmodel:model){
            categoryViewmodel.setImage(getScreenViewBitmap(view,categoryViewmodel.getImage()));
        }
    }


    @Override
    public int getCount() {
        return viewmodellist.size();
    }

    @Override
    public Bitmap getImage(int position) {
        return  viewmodellist.get(position).getImage();
    }


    public Bitmap getScreenViewBitmap(final View view, Bitmap bitmap) {
        view.setDrawingCacheEnabled(true);

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        ImageView imageView = (ImageView) view.findViewById(R.id.carosalbg);
        imageView.setImageBitmap(bitmap);
        view.buildDrawingCache(true);
        Bitmap l=view.getDrawingCache();
        Bitmap b = Bitmap.createBitmap(l);
        view.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }


}