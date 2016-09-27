package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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



public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent mIntent = getIntent();
        pid = mIntent.getExtras().getString("mPid");
        cid = mIntent.getExtras().getString("mCid");

        mSearchText = (EditText) findViewById(R.id.serchedittext);
        mSearchimageview = (ImageView) findViewById(R.id.serchicon);

        mButton1 = (Button) findViewById(R.id.rhymes);
        mButton2 = (Button) findViewById(R.id.letters);
        mButton3 = (Button) findViewById(R.id.counting);
        mButton4 = (Button) findViewById(R.id.drawing);
        mButton5 = (Button) findViewById(R.id.numbers);
        mButton6 = (Button) findViewById(R.id.counting2);
        mTextview = (TextView) findViewById(R.id.textshow);
        mLinearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mBackButtonImage = (ImageView) findViewById(R.id.backbutton);

        GridLayoutManager lLayout = new GridLayoutManager(SearchActivity.this, 3);
        mRecyclerview.setLayoutManager(lLayout);

        mButton1.setOnClickListener(SearchActivity.this);
        mButton2.setOnClickListener(SearchActivity.this);
        mButton3.setOnClickListener(SearchActivity.this);
        mButton4.setOnClickListener(SearchActivity.this);
        mButton5.setOnClickListener(SearchActivity.this);
        mButton6.setOnClickListener(SearchActivity.this);

        mLIstofSearchhResult = new ArrayList<>();
        searchViewmodelobject = new SearchViewmodel();
        mLocalDb = new DataBaseHandler(SearchActivity.this);

        mBackButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contentlist = new Intent(SearchActivity.this, ContentListActivity.class);
                contentlist.putExtra("mPid", pid);
                contentlist.putExtra("mCid", cid);
                startActivity(contentlist);
            }
        });
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
        }


        mSearchimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String search_data = mSearchText.getText().toString();
                Toast.makeText(SearchActivity.this, "Toast MEssage" + search_data, Toast.LENGTH_LONG).show();
                mLinearlayout.setVisibility(view.GONE);
                mTextview.setVisibility(View.GONE);

                searchViewmodelobject.getSerachResultFromViewmodel(search_data, new FetchSearchview() {
                    @Override
                    public void receivedSearchviewdata(ArrayList<SearchViewmodel> model) {

                        mLIstofSearchhResult=model;
                        SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(model, SearchActivity.this);
                        mRecyclerview.setAdapter(adapter);

                    }
                });


            }
        });
   mRecyclerview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerview, new ClickListener() {
       @Override
       public void onClick(View view, int position) {
           SearchViewmodel model = mLIstofSearchhResult.get(position);
           mLocalDb.addStoreDataToDataBase(new Historymodel(model.getTitle(),model.getUrlimage(),model.getVideourl()));

           Intent videoview = new Intent(SearchActivity.this, VideoPlayerActivity.class);
           videoview.putExtra("videourl", model.getVideourl());
           startActivity(videoview);

       }

       @Override
       public void onLongClick(View view, int position) {

       }
   }));

        mBackButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent home = new Intent(SearchActivity.this,CategoryActivity.class);
                startActivity(home);
            }
        });

    }
}
