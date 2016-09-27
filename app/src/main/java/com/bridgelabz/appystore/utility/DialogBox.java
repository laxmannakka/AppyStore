package com.bridgelabz.appystore.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgelabz.appystore.R;
import com.bridgelabz.appystore.view.CategoryActivity;

/**
 * Created by bridgeit007 on 26/9/16.
 */

public class DialogBox {


    // This function shows the Dialouge box
    public static void    showDialouge(Context context){
        // Creatig the object of Dialog box
        final Dialog dialog = new Dialog(context, R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //setting the tittle bar as none
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.diologuebox); // Setting view
        // Initilizing the Textview
        TextView showdata = (TextView)dialog.findViewById(R.id.textview);
        showdata.setText(R.string.parenting_icon_text); // Setting the text
        // showing the dialogue
        dialog.show();

        // Initilizing the image in Dialouge box
        ImageView imageView = (ImageView) dialog.findViewById(R.id.profilePic);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); // dismiss the dialouge box
            }
        });

    }
}
