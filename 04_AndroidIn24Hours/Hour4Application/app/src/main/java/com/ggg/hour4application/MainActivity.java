package com.ggg.hour4application;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */

    // message request code used as a key between activity intents
    public final static int MESSAGE_REQUEST_CODE = 0;

    // views
    TextView messageTextView;
    Button getMessageButton;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get views
        messageTextView = (TextView)findViewById(R.id.textView);
        getMessageButton = (Button)findViewById(R.id.button);

        // updated text view with saved message text if available
        if (savedInstanceState != null) {
            String savedMessage = savedInstanceState.getString("SAVED_MESSAGE");
            if (savedMessage != null && savedMessage.length() > 0) {
                messageTextView.setText(savedMessage);
            }
        }

        // register button click handler
        getMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start intent with message request
                Intent getResultIntent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivityForResult(getResultIntent, MESSAGE_REQUEST_CODE);
            }
        });
    }


    /**
     * onActivityResult
     * @param requestCode
     * @param resultCode
     * @param resultData
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        // look for specific request with matching request code and
        // make sure the result status is OK
        if (requestCode == MESSAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get message data string and populate to text view
            String message = resultData.getStringExtra("MESSAGE_DATA_KEY");
            if (message != null && message.length() > 0) {
                messageTextView.setText(message);
            }
        }
    }


    /**
     * onSaveInstanceState
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // adding data to saved instance state Bundle
        savedInstanceState.putString("SAVED_MESSAGE", messageTextView.getText().toString());
    }
}
