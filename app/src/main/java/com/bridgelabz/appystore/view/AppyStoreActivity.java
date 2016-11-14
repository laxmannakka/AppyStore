package com.bridgelabz.appystore.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.utility.Background;


/**
 * <Purpose>
 *
 *
 * */

public class AppyStoreActivity extends AppCompatActivity {

    //Button for login
    Button mLogin;
    //display the text
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent svc=new Intent(AppyStoreActivity.this, Background.class);
        startService(svc);
        setContentView(R.layout.activity_main);
        mLogin= (Button) findViewById(R.id.loginbutton);
        mTextView = (TextView) findViewById(R.id.logintextview);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent content = new Intent(AppyStoreActivity.this,MainActivity.class);
                startActivity(content);

            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent content = new Intent(AppyStoreActivity.this,MainActivity.class);
                startActivity(content);
            }
        });

    }


}


