package com.ggg.hour8application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */

    ListView pieListView;
    String[] pies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get list item from resources
        pies = getResources().getStringArray(R.array.pie_array);

        // get list view
        pieListView = (ListView) findViewById(R.id.listView);

        // create custom adaptor
        PieAdapter adapter = new PieAdapter(this, pies);

        // set custom adaptor
        pieListView.setAdapter(adapter);

        // add click listener to items
        pieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), pies[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
