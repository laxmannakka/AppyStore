package com.bridgelabz.appystore.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.appystore.R;

import java.util.Random;

/**
 * Created by bridgeit007 on 5/10/16.
 */

public class ChildLockDialogBox extends Dialog implements View.OnClickListener {

    private static final String TAG="DialogFragment";
    Button button[] = new Button[9];

    public ChildLockDialogBox(Context context) {
        super(context);
    }

    enum Numbers{ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE}

    //array of 3 random numbers
    int arr[] = new int[3];
    int i,count=0;

    /*byte to check the status of buttons
    * whether they are pressed or released
    * if pressed byte will be 1
    * if released byte will be 0*/
    byte b[] = new byte[9];

    //to display 3 random numbers
    TextView num[] = new TextView[3];


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialouge2);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getAttributes().windowAnimations=R.style.PauseDialog;
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        button[0] = (Button)findViewById(R.id.button1);
        button[1] = (Button)findViewById(R.id.button2);
        button[2] = (Button)findViewById(R.id.button3);
        button[3] = (Button)findViewById(R.id.button4);
        button[4] = (Button)findViewById(R.id.button5);
        button[5] = (Button)findViewById(R.id.button6);
        button[6] = (Button)findViewById(R.id.button7);
        button[7] = (Button)findViewById(R.id.button8);
        button[8] = (Button)findViewById(R.id.button9);

        for(i=0;i<button.length; i++)
            button[i].setOnClickListener(this);

        num[0] = (TextView)findViewById(R.id.num1);
        num[1] = (TextView)findViewById(R.id.num2);
        num[2] = (TextView)findViewById(R.id.num3);

        int k=1;

        /*loop to generate 3 random numbers
        * */
        for (i = 0; i < 3; i++) {
            arr[i] = randomNumberGeneration(k, k+2);
            k+=3;
        }

        /*loop to convert integer random number into alphabetical form
        * and print it on the TextView
        * */
        for(i=0; i<arr.length; i++)
            num[i].setText(findNumber(arr[i]));
    }

    @Override
    public void onClick(View v)
    {
        for(i=0; i<button.length; i++)
            if(v == button[i])
                changeButtonState(i);

        //if 3 buttons are pressed then it will check for authentication
        if(count == 3)
            authenticationMethod();
    }

    //change view of button when it is pressed
    void buttonPressed(int n)
    {
        button[n].setBackgroundResource(R.drawable.shape_for_press);
    }

    //change view of button when it is released
    void buttonReleased(int n)
    {
        button[n].setBackgroundResource(R.drawable.shape_for_release);
    }

    //method to generate random numbers
    int randomNumberGeneration(int min, int max)
    {
        Random rn = new Random();
        int a = max - min +1;
        int b = rn.nextInt(max) % a;
        int randNum = min+b;

        return randNum;
    }

    //method to convert integer random number into it's alphabetical form
    String findNumber(int n)
    {
        return Numbers.values()[n-1].toString();
    }

    void changeButtonState(int n)
    {
        if(b[n] == 0)
        {
            buttonPressed(n);
            b[n] = 1;
            count++;
        }
        else {
            buttonReleased(n);
            b[n] = 0;
            count--;
        }
    }

    //method to verify authentication
    void authenticationMethod()
    {
        if(b[arr[0]-1] == 1  && b[arr[1]-1] == 1 && b[arr[2]-1]== 1)
        {
            Toast.makeText(getContext(),"Authenticated",Toast.LENGTH_LONG).show();

            //close the Dialog
            dismiss();

            Log.i(TAG,"Authenticated***************");
        }
        else
            resetInput();
    }
    void resetInput()
    {
        Toast.makeText(getContext(), "Wrong Input", Toast.LENGTH_SHORT).show();
        for(i=0; i<b.length; i++)
        {
            button[i].setBackgroundResource(R.drawable.shape_for_release);
            b[i] = 0;
            count = 0;
        }
    }



}
