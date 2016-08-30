package com.bridgelabz.appystore.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.MyCoverFlowAdapter;
import com.bridgelabz.appystore.interfaces.FetchView;
import com.bridgelabz.appystore.librarycarosal.CoverFlowView;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {

   // Arraylist storing the of Categoryviewmodel list this data getting from viewmodel
    ArrayList<CategoryViewmodel> mListofContent;

    //for log purpose
    String VIEW_LOG_TAG = "error";
    ProgressBar spinner;
    View view;
    TextView textView;
    ArrayList<CategoryViewmodel> model;
    TextView mdisplaytitle;
    public transient Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflating the layout


         view = LayoutInflater.from(this).inflate(R.layout.activity_content_view, null, false);
        spinner= (ProgressBar) view.findViewById(R.id.progressBar1);
        textView = (TextView) view.findViewById(R.id.spinnertext);
        mdisplaytitle=(TextView)view.findViewById(R.id.displayname);
        // set the view
        setContentView(view);



        // Initilizing the Coverflow view
        final CoverFlowView<MyCoverFlowAdapter> mCoverFlowView = (CoverFlowView<MyCoverFlowAdapter>) findViewById(R.id.coverflow);

        // Creating tha object of Category viewmodel
        CategoryViewmodel categoryViewmodel = new CategoryViewmodel();

        // INitilizing the arraylist
        mListofContent = new ArrayList<>();

        // getting the viewmodel data
        mListofContent= categoryViewmodel.getViewmodeldata(new FetchView() {

            @Override
            public void getviewdata(ArrayList<CategoryViewmodel> viewmodelArrayList) {
              model = viewmodelArrayList;

                spinner.setVisibility(view.INVISIBLE);
                textView.setVisibility(view.INVISIBLE);
                final MyCoverFlowAdapter adapter = new MyCoverFlowAdapter(CategoryView.this,model);
                mCoverFlowView.setAdapter(adapter);


            }
        });


        mCoverFlowView
                .setCoverFlowListener(new CoverFlowView.CoverFlowListener<MyCoverFlowAdapter>() {

                    @Override
                    public void imageOnTop(
                            CoverFlowView<MyCoverFlowAdapter> view,
                            int position, float left, float top, float right,
                            float bottom) {
                        Log.e(VIEW_LOG_TAG, position + " on top!");
                        mdisplaytitle.setText(model.get(position).getTitle());
                    }

                    @Override
                    public void topImageClicked(
                            CoverFlowView<MyCoverFlowAdapter> view, int position) {
                        Log.e(VIEW_LOG_TAG, position + " clicked!");
                        Toast.makeText(getBaseContext(),"Position"+position,Toast.LENGTH_SHORT).show();
                        CategoryViewmodel model2 = model.get(position);
                        String pid = model2.getPid();
                        String cid =model2.getCid();
                        Intent contentlist = new Intent(CategoryView.this,ContentListView.class);
                        contentlist.putExtra("pid",pid);
                        contentlist.putExtra("cid",cid);
                        startActivity(contentlist);
                    }

                    @Override
                    public void invalidationCompleted() {

                    }
                });

        mCoverFlowView
                .setTopImageLongClickListener(new CoverFlowView.TopImageLongClickListener() {

                    @Override
                    public void onLongClick(int position) {
                        Log.e(VIEW_LOG_TAG, "top image long clicked == >"
                                + position);

                    }
                });

    }



}





