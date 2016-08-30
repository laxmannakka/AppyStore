package com.bridgelabz.appystore.adapters;

import android.content.Context;
import android.graphics.Bitmap;

import com.bridgelabz.appystore.librarycarosal.CoverFlowAdapter;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 30/8/16.
 */


    public class MyCoverFlowAdapter extends CoverFlowAdapter {
        private boolean dataChanged;

        Context mcontext;
        ArrayList<CategoryViewmodel> viewmodellist = new ArrayList<>();


        /**
         * Constructor of MyCoverfloew adapter
         *<param>
         * */
        public MyCoverFlowAdapter(Context context, ArrayList<CategoryViewmodel> model){
            this.mcontext=context;
            this.viewmodellist=model;
        }


        @Override
        public int getCount() {
            return viewmodellist.size();
        }

        @Override
        public Bitmap getImage(final int position)  {
            return viewmodellist.get(position).getImage();

        }
    }


