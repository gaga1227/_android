package com.ggg.hour13applicationactionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup action bar contents
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        try {
            // set titles
            actionBar.setTitle(R.string.title_activity_main);
            actionBar.setSubtitle(R.string.subtitle_activity_main);
            // set home icon and show
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }
        catch (NullPointerException exp) {
            Log.e("Error", exp.getMessage());
        }
    }


    /**
     * onCreateOptionsMenu
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate menu resource with menu options
        // and display it
        if (menu != null) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            // return true to display menu
            return true;
        }

        // return default: false
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * onOptionsItemSelected
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get selected menu item info
        int itemId = item.getItemId();
        String itemTitle = item.getTitle().toString();

        // handle menu item selection here
        if (item != null) {
            Toast.makeText(MainActivity.this, itemTitle, Toast.LENGTH_SHORT).show();

            Intent childIntent = new Intent(MainActivity.this, ChildActivity.class);
            childIntent.putExtra("CHILD_CONTENT_TITLE", itemTitle);
            startActivity(childIntent);

            //return true to handle selection action here rather than pass it up the chain
            return true;
        }

        // default return false to pass event up the chain
        return super.onOptionsItemSelected(item);
    }
}
