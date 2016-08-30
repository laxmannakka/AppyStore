package com.bridgelabz.appystore.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bridgelabz.appystore.R;

public class MainActivity extends AppCompatActivity {


    Button mlogin;
    TextView mtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlogin= (Button) findViewById(R.id.button);
        mtextview = (TextView) findViewById(R.id.textview2);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent content = new Intent(MainActivity.this,CategoryView.class);
                startActivity(content);

            }
        });
        mtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent content = new Intent(MainActivity.this,CategoryView.class);
                startActivity(content);

            }
        });



    }



}
