package com.ggg.hour4application;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageActivity extends AppCompatActivity {

    /**
     * vars
     */

    // views
    EditText messageEditText;
    Button sendMessageButton;
    Button cancelButton;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // get views
        messageEditText = (EditText)findViewById(R.id.editText);
        sendMessageButton = (Button)findViewById(R.id.buttonSend);
        cancelButton = (Button)findViewById(R.id.buttonCancel);

        // disable send button initially
        sendMessageButton.setEnabled(false);


        /**
         * Text change handler
         */
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // toggle send message button depends on the message length
                sendMessageButton.setEnabled(s.length() > 0);
            }

            // not used
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });


        /**
         * Button click handlers
         */
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get message text
                String messageText = messageEditText.getText().toString();

                // create result data intent with data and matching message data key
                Intent resultIntent = new Intent();
                resultIntent.putExtra("MESSAGE_DATA_KEY", messageText);

                // set OK result code with result intent and finish activity
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set cancelled result code and finish activity
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
