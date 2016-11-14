package com.bridgelabz.appystore.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import com.bridgelabz.appystore.R;

/**
 * Created by bridgeit007 on 20/10/16.
 */

public class LayerListFactory {
    Context mContext;

    public LayerListFactory(Context mContext) {
        this.mContext = mContext;
    }

    public   LayerDrawable getDrawble(){
        LayerDrawable layerimage = (LayerDrawable) mContext.getResources().getDrawable(R.drawable.demolayerlist);
        return  layerimage;
    }
}
