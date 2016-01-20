package com.ggg.hour6application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * vars
         */

        final EditText nameInput = (EditText) findViewById(R.id.nameInput);
        final TextView textView = (TextView) findViewById(R.id.textView);
        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);


        /**
         * sync text view to name input
         */
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.setText("Name: " + nameInput.getText());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        /**
         * Image button toggle
         */
        imageButton.setTag(true);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean defaultValue = (Boolean) imageButton.getTag();
                if (defaultValue) {
                    imageButton.setImageResource(R.drawable.gaga);
                    imageButton.setTag(false);
                } else {
                    imageButton.setImageResource(R.mipmap.ic_launcher);
                    imageButton.setTag(true);
                }
            }
        });
    }
}
