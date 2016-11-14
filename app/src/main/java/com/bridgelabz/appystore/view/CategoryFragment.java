package com.bridgelabz.appystore.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.CategoryRecyclerAdapter;
import com.bridgelabz.appystore.databinding.ActivityCarouselPreviewBinding;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchCategoryList;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

/**
 * this class shows the Categorylist
 * int this fragment taking recyclerview displaying data
 */

public class CategoryFragment extends Fragment {
    // recyclerview for display data
    RecyclerView mRecyclerView;
    // Array list of category view model
    ArrayList<CategoryViewmodel> mListofdata = new ArrayList<>();
    // View for shows the layout
    View mView;
    // parent category id
    String mPid;
    // category id
    String mCid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflating the view
        mView = inflater.inflate(R.layout.recyclerview, container, false);
        // initializing the recycler view
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler);
        // Creating the object of Carousal layout manager
        CarouselLayoutManager layoutManager1 = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false);
       //  will be called for each visible view item after general LayoutManager layout finishes
        layoutManager1.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        // Set the layout that this RecyclerView will use.
        mRecyclerView.setLayoutManager(layoutManager1);
        //Add a listener that will be notified of any changes in scroll state or position.
        mRecyclerView.addOnScrollListener(new CenterScrollListener());
        // Creating the object of Category viewmodel
        final CategoryViewmodel categoryViewmodel = new CategoryViewmodel(mView.getContext());

        //  intercept touch events  of items
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(mView.getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CategoryViewmodel categoryViewmodel1 = mListofdata.get(position);
                Toast.makeText(view.getContext(), "mesage" + position, Toast.LENGTH_LONG).show();
                mPid = categoryViewmodel1.getPid();
                mCid = categoryViewmodel1.getCid();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ContentListFragment obj = new ContentListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("pid", mPid);
                bundle.putString("cid", mCid);
                obj.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment, obj);
                fragmentTransaction.commit();
            } // close onclick

            @Override
            public void onLongClick(View view, int position) {

            } // closing on long click
        })); // function calling end

        // this function get the data of view   model
        categoryViewmodel.getCategoryViewModelData(new FetchCategoryList() {
            @Override
            public void receivedCategoryViewData(ArrayList<CategoryViewmodel> viewmodelArrayList) {
                mListofdata = viewmodelArrayList;
                // Creating the object of Category adapter
                CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(mView.getContext(), mListofdata);
                mRecyclerView.setAdapter(adapter);
            } //closing of receivedCategoryViewData function
        }); // end of function call



        return mView; // retuning the view

    } // close brace of Oncreate view
} //closing of class

