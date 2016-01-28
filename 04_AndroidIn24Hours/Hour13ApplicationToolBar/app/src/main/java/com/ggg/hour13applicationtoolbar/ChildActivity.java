package com.ggg.hour13applicationtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        // find toolbar view and init
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // set content title
        String title = getIntent().getExtras().getString("CHILD_CONTENT_TITLE");
        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText(title);
        setTitle("Child: " + title);
    }
}
