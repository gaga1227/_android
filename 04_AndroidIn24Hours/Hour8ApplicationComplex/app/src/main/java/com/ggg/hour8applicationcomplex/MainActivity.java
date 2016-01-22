package com.ggg.hour8applicationcomplex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */
    ListView listView;
    ArrayList<Item> listItems;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get list view
        listView = (ListView) findViewById(R.id.listView);

        // prep items data
        listItems = makeItems();

        // set up adaptor
        ItemAdaptor customAdaptor = new ItemAdaptor(this, listItems);

        // set adaptor to list view
        listView.setAdapter(customAdaptor);

        // set item click handler
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item clickedItem = listItems.get(position);
                Toast.makeText(getApplicationContext(), clickedItem.mName, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Prep all items objects into array list
     * @return
     */
    private ArrayList<Item> makeItems() {
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item("Apple", "An old-fashioned favorite.", 1.5));
        items.add(new Item("Blueberry", "Made with fresh Maine blueberries.", 1.5));
        items.add(new Item("Cherry", "Delicious and fresh made daily.", 2.0));
        items.add(new Item("Coconut Cream", "A customer favorite.", 2.5));
        items.add(new Item("Apple", "An old-fashioned favorite.", 1.5));
        items.add(new Item("Blueberry", "Made with fresh Maine blueberries.", 1.5));
        items.add(new Item("Cherry", "Delicious and fresh made daily.", 2.0));
        items.add(new Item("Coconut Cream", "A customer favorite.", 2.5));
        items.add(new Item("Apple", "An old-fashioned favorite.", 1.5));
        items.add(new Item("Blueberry", "Made with fresh Maine blueberries.", 1.5));
        items.add(new Item("Cherry", "Delicious and fresh made daily.", 2.0));
        items.add(new Item("Coconut Cream", "A customer favorite.", 2.5));
        items.add(new Item("Apple", "An old-fashioned favorite.", 1.5));
        items.add(new Item("Blueberry", "Made with fresh Maine blueberries.", 1.5));
        items.add(new Item("Cherry", "Delicious and fresh made daily.", 2.0));
        items.add(new Item("Coconut Cream", "A customer favorite.", 2.5));

        return items;
    }
}
