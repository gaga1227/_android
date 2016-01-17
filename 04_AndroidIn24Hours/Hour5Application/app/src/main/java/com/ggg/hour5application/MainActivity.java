package com.ggg.hour5application;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */

    long delay = 5000;
    TextView resultsTextView;
    Button uiThreadButton;

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get text view
        resultsTextView = (TextView) findViewById(R.id.resultsTextView);

        // init UI thread button
        uiThreadButton = (Button) findViewById(R.id.uiThreadButton);
        uiThreadButton.setOnClickListener(uiButtonListener);
    }


    /**
     * UI Thread delay
     */
    View.OnClickListener uiButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SystemClock.sleep(delay);
            resultsTextView.setText("Updated on UI Thread");
        }
    };
}
