package com.ggg.hour2application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Launch secondary activity using explicit intent with data
         */

        // define button and register click callback
        Button activityButton = (Button) findViewById(R.id.button);
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new explicit intent
                Intent startIntent = new Intent(getApplicationContext(), SecondaryActivity.class);

                // add data and start intent
                startIntent.putExtra("MESSAGE_KEY", "Great, you got my message!");
                startActivity(startIntent);
            }
        });


        /**
         * Open map location with implicit intent
         */

        Button mapButton = (Button) findViewById(R.id.button2);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // prepare geo URI data
                String geoString = "geo:37.422,-122.084?z=18";
                Uri geoURI = Uri.parse(geoString);

                // create intent with URI data
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoURI);

                // start intent if there is device app to handle such intent
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });


        /**
         * Displaying a Web Page
         */

        Button webButton = (Button) findViewById(R.id.button3);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // prepare web URI data
                String webString = "http://gaga-graphics.com";
                Uri webURI = Uri.parse(webString);

                // create intent with URI data
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webURI);

                // start intent if there is device app to handle such intent
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });


        /**
         * Make a call
         */

        Button callButton = (Button) findViewById(R.id.button4);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // prepare dial URI data
                String dialString = "tel:0421123456";
                Uri dialURI = Uri.parse(dialString);

                // create intent with URI data
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                // alternative way to add intent data
                dialIntent.setData(dialURI);

                // start intent if there is device app to handle such intent
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }
            }
        });

    }
}
