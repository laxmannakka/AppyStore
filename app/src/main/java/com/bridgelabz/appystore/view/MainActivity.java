package com.bridgelabz.appystore.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import com.bridgelabz.appystore.utility.LayerListFactory;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.utility.ChildLockDialogBox;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * This class shows the Categories List
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // Declaring the variables
    ImageView mHome;
    ImageView mSearch;
    ImageView mHistory;
    ImageView mShop,mSong,mVideos;
    ImageView mCorner;
    ImageView fabIconNew;
    View mView;
    public  static  int i=0;
    LayerListFactory mLIstfactory;
    LayerDrawable mLayerDrawable;
    ImageView mImageArray[] = new ImageView[6];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_xml);

        mView = findViewById(R.id.semiTransparent);
        fabIconNew = new ImageView(MainActivity.this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.homeicon));
        // initilizing the all the variables
        mHome = (ImageView) findViewById(R.id.home);
        mSearch = (ImageView) findViewById(R.id.categorysearch);
        mHistory = (ImageView) findViewById(R.id.history);
        mShop = (ImageView) findViewById(R.id.shop);
        mSong=(ImageView)findViewById(R.id.songs);
        mVideos=(ImageView)findViewById(R.id.videos);
        mLIstfactory = new LayerListFactory(MainActivity.this);

        mImageArray[0]=mHome;
        mImageArray[1]=mSong;
        mImageArray[2]=mVideos;
        mImageArray[3]=mSearch;
        mImageArray[4]=mHistory;
        mImageArray[5]=mShop;



        mLayerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.demolayerlist);
        final Drawable history = (Drawable) getResources().getDrawable(R.drawable.ic_history_white_24dp);
       Drawable history1=resize(history);
        mLayerDrawable.setDrawableByLayerId(R.id.layerimage, history1);
        mHistory.setBackground(mLayerDrawable);

        mLayerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.demolayerlist);
        final Drawable homee = (Drawable) getResources().getDrawable(R.drawable.ic_home_white_24dp);
        mLayerDrawable.setDrawableByLayerId(R.id.layerimage, homee);
        mHome.setBackground(mLayerDrawable);


        mLayerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.demolayerlist);
        Drawable songicon = getResources().getDrawable(R.drawable.ic_music_note_white_24dp);
        mLayerDrawable.setDrawableByLayerId(R.id.layerimage, songicon);
        mSong.setBackground(mLayerDrawable);

        mLayerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.demolayerlist);
        Drawable videoicon = getResources().getDrawable(R.drawable.ic_ondemand_video_white_24dp);
        mLayerDrawable.setDrawableByLayerId(R.id.layerimage, videoicon);
        mVideos.setBackground(mLayerDrawable);

        mLayerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.demolayerlist);
        Drawable searchicon = getResources().getDrawable(R.drawable.ic_search_white_24dp);
        mLayerDrawable.setDrawableByLayerId(R.id.layerimage,searchicon);
        mSearch.setBackground(mLayerDrawable);

        mLayerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.demolayerlist);
        Drawable shopicon = getResources().getDrawable(R.drawable.ic_shopping_cart_white_24dp);
        mLayerDrawable.setDrawableByLayerId(R.id.layerimage,shopicon);
        mShop.setBackground(mLayerDrawable);



     /* final  LayerDrawable ld = (LayerDrawable) getResources().getDrawable(R.drawable.layerlist);
        mHome.setImageDrawable(ld.getDrawable(0));
        mSearch.setImageDrawable(ld.getDrawable(5));
        mHistory.setImageDrawable(ld.getDrawable(4));
        mShop.setImageDrawable(ld.getDrawable(6));
        mSong.setImageDrawable(ld.getDrawable(1));
        mVideos.setImageDrawable(ld.getDrawable(3));*/





        mVideos.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                   // mVideos.setImageDrawable(ld.getDrawable(3));
                    Log.d("onTouch", "MotionEvent.ACTION_UP" );
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                 //   mVideos.setImageDrawable(ld.getDrawable(3));
                    Log.d("onTouch", "MotionEvent.ACTION_DOWN" );
                    return true;
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    //mVideos.setImageDrawable(ld.getDrawable(2));
                    Log.d("onTouch", "MotionEvent.ACTION_CANCEL" );
                }
                return false;
            }
        });



        //    mCorner = (ImageView) findViewById(R.id.mhome);
        // Fragment Transaction operations done here
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CategoryFragment fragment = new CategoryFragment();
        fragmentTransaction.add(R.id.fragment, fragment);
        fragmentTransaction.commit();
        // when mHome homeicon clicks replacing fragment with the Catogory fragment
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor();
                setColor(mHome);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CategoryFragment fragment = new CategoryFragment();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }
        });
        // when mHome homeicon clicks replacing fragment with the Search fragment
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor();
                setColor(mSearch);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SearchFragment fragment = new SearchFragment();
                fragmentTransaction.replace(R.id.layout1, fragment);
                fragmentTransaction.commit();

            }
        });
        // when mHome homeicon clicks replacing fragment with the History fragment
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor();
                setColor(mHistory);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HistoryFragment fragment = new HistoryFragment();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();

            }
        });
        // onclick showing the pop up
        mShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor();
                setColor(mShop);
              //  DialogBox.showDialouge(MainActivity.this);
                ChildLockDialogBox dialogBox = new ChildLockDialogBox(MainActivity.this);
                dialogBox.show();
            }
        });
        // onclick showing the pop up
      /*  mCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox.showDialouge(MainActivity.this);

            }
        });*/





        FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(MainActivity.this)
                .setContentView(fabIconNew)
                .build();
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        TextView text = new TextView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.write));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.share));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.videofragment));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.sound));


        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                .attachTo(rightLowerButton)
                .build();


        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);
                Animation alpha_in = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.animation_on);
                alpha_in.setDuration(300);
                mView.startAnimation(alpha_in);
                alpha_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                fabIconNew.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();

                Animation alpha_out = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.animation_off);
                alpha_out.setDuration(300);
                mView.startAnimation(alpha_out);
                alpha_out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        });

    } // end of oncreate function



    public void changeColor() {

        for (int i = 0; i <= 5; i++) {
            LayerDrawable bgDrawable = (LayerDrawable) mImageArray[i].getBackground();
            GradientDrawable gradientDrawable = (GradientDrawable) bgDrawable
                    .findDrawableByLayerId(R.id.layer3);
            gradientDrawable.setColor(ContextCompat.getColor(this,R.color.lightblack));
        }
    }

    public void setColor(ImageView imageView) {
        LayerDrawable bgDrawable = (LayerDrawable) imageView.getBackground();
        //  layerDrawable =(LayerDrawable)getResources().getDrawable(R.drawable.demolayerlist);
        GradientDrawable shape = (GradientDrawable) (bgDrawable.findDrawableByLayerId(R.id.layer3));
        // shape.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        // shape.setColor(Color.RED);
        shape.setColor(ContextCompat.getColor(this, R.color.orange));
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 25, 25, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

} // end of class






