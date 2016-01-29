package com.ggg.hour13applicationtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ChildActivity extends AppCompatActivity {

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);


        /**
         * Toolbar
         */

        // find toolbar view and set as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setSubtitle("Subtitle");
            // enable back button to parent activity
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException exp) {
            Log.e("Error", exp.getMessage());
        }


        /**
         * Filters Spinner
         */

        // get filter view and data
        final String[] filters = getResources().getStringArray(R.array.filters);
        AppCompatSpinner filtersSpinner = (AppCompatSpinner) findViewById(R.id.filters);

        // setup spinner with data
        filtersSpinner.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.text_row,
                filters));

        // pre-select a filter
        filtersSpinner.setSelection(1);

        // selection handler
        filtersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ChildActivity.this, filters[position], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        /**
         * set content title
         */

        // get title
        String title = getIntent().getExtras().getString("CHILD_CONTENT_TITLE");

        // set title to body textview
        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText(title);

        // set title to toolbar
        setTitle("Child: " + title);
    }
}
