package com.ggg.hour10application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */
    String[] items;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get data
        items = getResources().getStringArray(R.array.items);

        // init demos
        initSpinner();
        initAutoCompleteTextView();
    }


    /**
     * Make a toast with the input content
     * @param input
     */
    public void makeToast(String input) {
        if (input != null && input.length() > 0) {
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Spinner
     */
    public void initSpinner() {
        // find view
        Spinner v = (Spinner) findViewById(R.id.spinnerView);

        // set data adaptor to view
        v.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                items));

        // handle selection
        v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                makeToast("Spinner:\n" + items[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * Auto Complete Text View
     */
    public void initAutoCompleteTextView() {
        // find view
        AutoCompleteTextView v = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // set data adaptor to view
        v.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                items));

        // handle inputs
        v.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                makeToast("AutoCompleteTextView:\n" + s.toString());
            }
        });
    }
}

