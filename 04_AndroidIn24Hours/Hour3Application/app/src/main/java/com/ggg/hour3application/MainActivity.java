package com.ggg.hour3application;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Get resource values
         */

        // get text content and color from resource
        String textView2String = getResources().getString(R.string.textview_2);
        Integer textView2Color = getResources().getColor(R.color.colorAccent);

        // apply text content and color
        TextView textView2 = (TextView)findViewById(R.id.textview2);
        textView2.setText(textView2String);
        textView2.setTextColor(textView2Color);
    }
}
