package com.bridgelabz.appystore.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.adapters.SearchRecyclerAdapter;
import com.bridgelabz.appystore.interfaces.ClickListener;
import com.bridgelabz.appystore.interfaces.FetchSearchview;
import com.bridgelabz.appystore.model.Historymodel;
import com.bridgelabz.appystore.utility.DataBaseHandler;
import com.bridgelabz.appystore.utility.RecyclerTouchListener;
import com.bridgelabz.appystore.viewmodel.SearchViewmodel;

import java.util.ArrayList;
/**
 * this Activity shows the search results from server
 * **/



public class SearchFragment extends Fragment implements View.OnClickListener {

   // Declaring the variables
    EditText mSearchText;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    Button mButton5;
    Button mButton6;
    ImageView mSearchimageview;
    ImageView mBack;
    SearchViewmodel searchViewmodelobject;
    TextView mTextview;
    LinearLayout mLinearlayout;
    RecyclerView mRecyclerview;
    ImageView mBackButtonImage;
    String pid;
    String cid;
    ArrayList<SearchViewmodel> mLIstofSearchhResult;
    DataBaseHandler mLocalDb;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflating the view
        mView = inflater.inflate(R.layout.activity_search, container, false);
        // Initilizing the all variables
        mSearchText = (EditText) mView.findViewById(R.id.serchedittext);
        mSearchimageview = (ImageView) mView.findViewById(R.id.serchicon);
        mButton1 = (Button) mView.findViewById(R.id.rhymes);
        mButton2 = (Button) mView.findViewById(R.id.letters);
        mButton3 = (Button) mView.findViewById(R.id.counting);
        mButton4 = (Button) mView.findViewById(R.id.drawing);
        mButton5 = (Button) mView.findViewById(R.id.numbers);
        mButton6 = (Button) mView.findViewById(R.id.counting2);
        mTextview = (TextView) mView.findViewById(R.id.textshow);
        mBack = (ImageView) mView.findViewById(R.id.back);
        mLinearlayout = (LinearLayout) mView.findViewById(R.id.linearlayout);
        mRecyclerview = (RecyclerView) mView.findViewById(R.id.recyclerview);

        // grid layout manager for showing the data in recycler view as grid
        GridLayoutManager lLayout = new GridLayoutManager(mView.getContext(), 3);
        // setting the layout
        mRecyclerview.setLayoutManager(lLayout);

        // setting the onclick lisner
        mButton1.setOnClickListener(SearchFragment.this);
        mButton2.setOnClickListener(SearchFragment.this);
        mButton3.setOnClickListener(SearchFragment.this);
        mButton4.setOnClickListener(SearchFragment.this);
        mButton5.setOnClickListener(SearchFragment.this);
        mButton6.setOnClickListener(SearchFragment.this);
        mBack.setOnClickListener(SearchFragment.this);
        // Initilazing tha array list
        mLIstofSearchhResult = new ArrayList<>();
        // initilizing tha Searchviewmodle object
        searchViewmodelobject = new SearchViewmodel();

        // 
        mSearchimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_data = mSearchText.getText().toString();
                mLinearlayout.setVisibility(view.GONE);
                mTextview.setVisibility(View.GONE);
                searchViewmodelobject.getSerachResultFromViewmodel(search_data, new FetchSearchview() {
                    @Override
                    public void receivedSearchviewdata(ArrayList<SearchViewmodel> model) {
                        mLIstofSearchhResult = model;
                        SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(model, mView.getContext());
                        mRecyclerview.setAdapter(adapter);

                    }
                });
            }
        });


  /*      mRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(mView.getContext(), mRecyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchViewmodel model = mLIstofSearchhResult.get(position);
              //  mLocalDb.addStoreDataToDataBase(new Historymodel(model.getTitle(),model.getUrlimage(),model.getVideourl()));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VideoPlayerFragment fragment = new VideoPlayerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url",model.getVideourl());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.layout1,fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/


        return mView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rhymes:
                mSearchText.setText(mButton1.getText());
                break;
            case R.id.letters:
                mSearchText.setText(mButton2.getText());
                break;
            case R.id.counting:
                mSearchText.setText(mButton3.getText());
                break;
            case R.id.drawing:
                mSearchText.setText(mButton4.getText());
                break;
            case R.id.numbers:
                mSearchText.setText(mButton5.getText());
                break;
            case R.id.counting2:
                mSearchText.setText(mButton6.getText());
                break;
            case R.id.back:
                Toast.makeText(mView.getContext(),"message",Toast.LENGTH_LONG).show();
                getActivity().getFragmentManager().beginTransaction().remove(this).commit();
                break;
        }
    }
}





