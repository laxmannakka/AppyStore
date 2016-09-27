package com.bridgelabz.appystore.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.CategoryRecyclerAdapter;
import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.databinding.ActivityCarouselPreviewBinding;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchCategoryList;
import com.bridgelabz.appystore.utility.CategoryDataBaseHandler;
import com.bridgelabz.appystore.utility.DialogBox;
import com.bridgelabz.appystore.utility.MyFragment;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * This class shows the Categories List
 */


public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "CategoryActivity";
    // Arraylist storing the of Categoryviewmodel list this data getting from viewmodel
    ArrayList<CategoryViewmodel> mListofContent;
    // for displyaing the data
    RecyclerView mCatogoryRecyclerview;
    ImageView mSearchimageview;
    String mPid;
    String mCid;
    ImageView mHistory;
    ImageView mSongImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.catogory_listview);
        final ActivityCarouselPreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_carousel_preview);


        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false);

        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        binding.listHorizontal.setLayoutManager(layoutManager);
        binding.listHorizontal.addOnScrollListener(new CenterScrollListener());



      /*  mCatogoryRecyclerview = (RecyclerView) findViewById(R.id.categoryrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mCatogoryRecyclerview.setLayoutManager(linearLayoutManager);
        mListofContent = new ArrayList<>();
        mSearchimageview = (ImageView) findViewById(R.id.categorysearch);
        mHistory = (ImageView) findViewById(R.id.history);
        mSongImage = (ImageView) findViewById(R.id.songs);*/


        // Creating tha object of Category viewmodel
        CategoryViewmodel categoryViewmodel = new CategoryViewmodel(CategoryActivity.this);

        // getting the viewmodel data
        categoryViewmodel.getCategoryViewModelData(new FetchCategoryList() {

            @Override
            public void receivedCategoryViewData(ArrayList<CategoryViewmodel> viewmodelArrayList) {
                mListofContent = viewmodelArrayList;
                CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(CategoryActivity.this, mListofContent);
                // mCatogoryRecyclerview.setAdapter(adapter);
                binding.listHorizontal.setAdapter(adapter);

            }
        });

        // Recyclerview Touch Lisner
        binding.listHorizontal.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mCatogoryRecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CategoryViewmodel categoryviewmodel = mListofContent.get(position);
                mPid = categoryviewmodel.getPid();
                mCid = categoryviewmodel.getCid();
                Intent contentlist = new Intent(CategoryActivity.this, ContentListActivity.class);
                contentlist.putExtra("mPid", mPid);
                contentlist.putExtra("mCid", mCid);
                startActivity(contentlist);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }) {
        });


        binding.categorysearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serchview = new Intent(CategoryActivity.this, SearchActivity.class);
                serchview.putExtra("mPid", mPid);
                serchview.putExtra("mCid", mCid);
                startActivity(serchview);
            }
        });

        binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Historyview = new Intent(CategoryActivity.this, HistoryActivity.class);
                startActivity(Historyview);

            }
        });
        binding.song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              DialogBox.showDialouge(CategoryActivity.this);
            }
        });
        binding.shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox.showDialouge(CategoryActivity.this);
            }
        });
        binding.videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox.showDialouge(CategoryActivity.this);

            }
        });
        binding.parentingpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MyFragment fragment = new MyFragment();
                fragmentTransaction.add(R.id.coordinatelayout, fragment);
                fragmentTransaction.commit();

            }
        });
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox.showDialouge(CategoryActivity.this);
            }
        });
    }



}






