package com.ggg.hour13applicationtoolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find toolbar view and init
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    /**
     * onCreateOptionsMenu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate menu resource into view
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // must return true to be displayed
        return true;
    }


    /**
     * onOptionsItemSelected
     * @param item
     * @return
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
