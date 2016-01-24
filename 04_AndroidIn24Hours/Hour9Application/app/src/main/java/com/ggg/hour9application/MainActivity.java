package com.ggg.hour9application;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    /**
     * vars
     */

    // recycler view related
    RecyclerView recyclerView;
    RecyclerView.LayoutManager itemLayoutManager;

    // item data holder
    ArrayList<Item> listItems;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // prep items data
        listItems = makeItems();

        // get recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // set recycler view layout manager
        // use linear layout manager so recyclerView will be a list
        itemLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(itemLayoutManager);

        // set up adaptor and set to recycler view
        ItemAdaptor customAdaptor = new ItemAdaptor(listItems);
        recyclerView.setAdapter(customAdaptor);
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
