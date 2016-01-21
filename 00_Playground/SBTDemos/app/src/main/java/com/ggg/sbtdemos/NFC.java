package com.ggg.sbtdemos;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NFC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        /**
         * Handle NFC intent: ACTION_NDEF_DISCOVERED
         */

        // get intent
        Intent nfcIntent = getIntent();

        // NDEF message array
        List<NdefMessage> msgsList = new ArrayList<NdefMessage>();

        // pass available NDEF messages if intent action matches 'ACTION_NDEF_DISCOVERED'
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(nfcIntent.getAction())) {
            Parcelable[] rawMsgs = nfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgsList.add((NdefMessage)rawMsgs[i]);
                }
            }
        }

        //process the msgs array
        if (msgsList.size() > 0) {
            // get url string from first NDEF record
            String deepLinkUrl = msgsList.get(0).getRecords()[0].toUri().toString();

            // start intent with deep link url data
            if (deepLinkUrl != null && deepLinkUrl.length() > 0) {
                Log.i("INFO", "onCreate: ACTION_NDEF_DISCOVERED and EXTRA_NDEF_MESSAGES found!");
                Log.i("INFO", deepLinkUrl);

                Intent deepLinkIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                deepLinkIntent.putExtra("SBT_DEEPLINK", deepLinkUrl);
                startActivity(deepLinkIntent);
            }
        }
        else {
            Log.i("INFO", "onCreate: No ACTION_NDEF_DISCOVERED or EXTRA_NDEF_MESSAGES found!");
        }
    }
}
