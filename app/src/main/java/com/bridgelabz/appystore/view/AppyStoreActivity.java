package com.bridgelabz.appystore.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.controller.CategoryController;
import com.bridgelabz.appystore.utility.BackgroundSoundService;


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
        setContentView(R.layout.activity_main);
        mLogin= (Button) findViewById(R.id.loginbutton);
        mTextView = (TextView) findViewById(R.id.logintextview);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent content = new Intent(AppyStoreActivity.this,CategoryActivity.class);
                startActivity(content);

            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent content = new Intent(AppyStoreActivity.this,CategoryActivity.class);
                startActivity(content);

            }
        });
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
    }

}
