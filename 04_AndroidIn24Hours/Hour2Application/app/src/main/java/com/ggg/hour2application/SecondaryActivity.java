package com.ggg.hour2application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        /**
         * Handle intent data from main activity, or other apps
         */

        // message var
        String message = "Waiting for message...";

        // get passed intent with data
        Intent passedIntent = getIntent();

        // check intent data
        if (passedIntent != null) {
            if (passedIntent.hasExtra("MESSAGE_KEY")) {
                message = passedIntent.getStringExtra("MESSAGE_KEY");
            }

            // This will handle any intent with 'Intent.EXTRA_TEXT' data from other apps
            // e.g. browser -> share page
            // Intent type is filtered in manifest file
            else if (passedIntent.hasExtra(Intent.EXTRA_TEXT)) {
                message = passedIntent.getStringExtra(Intent.EXTRA_TEXT);
            }
        }

        // find and populate message text view
        TextView messageTextView = (TextView) findViewById(R.id.messageTextView);
        messageTextView.setText(message);
    }

}
