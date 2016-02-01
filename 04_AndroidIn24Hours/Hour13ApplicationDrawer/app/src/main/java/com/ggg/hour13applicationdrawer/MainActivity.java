package com.ggg.hour13applicationdrawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */

    // toolbar
    private Toolbar toolbar;

    // layout views
    private DrawerLayout drawerLayout;

    // drawer toggle
    private ActionBarDrawerToggle drawerToggle;

    // menu list views
    private ListView drawerLeftListView;
    private ListView drawerRightListView;

    // menu options
    private String[] menuOptionsLeft;
    private String[] menuOptionsRight;


    /**
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init
        _initToolbar();
        _initDefaultDrawer();
        _initRightDrawer();
        _initDrawerMenus();
    }


    /**
     * onPostCreate
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout
        // This should be called from your Activity's onPostCreate method
        drawerToggle.syncState();
    }


    /**
     * Toolbar
     */
    private void _initToolbar() {
        // setup action bar using toolbar view
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        catch (NullPointerException exp) {
            Log.e("Error", exp.getMessage());
        }
    }


    /**
     * Drawer Left (default with DrawerToggle)
     */
    private void _initDefaultDrawer() {
        drawerLeftListView = (ListView) findViewById(R.id.left_drawer);
        drawerRightListView = (ListView) findViewById(R.id.right_drawer);

        // get drawer views
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // create DrawerToggle
        // Notes: ActionBarDrawerToggle will ONLY handle a LEFT sided Drawer
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            // Called when a drawer has settled in a completely open state.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                // make sure only one of the drawers is open at any time
                if (drawerView.equals(drawerLeftListView)) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, drawerRightListView);
                }
                if (drawerView.equals(drawerRightListView)) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, drawerLeftListView);
                }

                // prevent menu icon being updated unless it is the default drawer
                if (!drawerView.equals(drawerLeftListView)) {
                    // workaround to force NOT update the menu icon
                    super.onDrawerSlide(drawerView, 0);
                }
            }

            // Called when a drawer has settled in a completely closed state.
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                // make sure only one of the drawers is open at any time
                if (drawerView.equals(drawerLeftListView)) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, drawerRightListView);
                }
                if (drawerView.equals(drawerRightListView)) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, drawerLeftListView);
                }
            }

            // Called when a drawer's position changes. e.g. during sliding
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // stop animating menu icon due to it's hidden
                super.onDrawerSlide(drawerView, 0);
            }
        };

        // setup DrawerToggle with DrawerLayout
        drawerLayout.setDrawerListener(drawerToggle);
    }


    /**
     * Drawer Right (handled manually via a button view)
     */
    private void _initRightDrawer() {
        // using TextView as right drawer toggle button
        TextView rightDrawerToggle = (TextView) findViewById(R.id.rightDrawerToggle);
        rightDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // toggle logic on right drawer
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
    }


    /**
     * Drawers Menus
     */
    private void _initDrawerMenus() {
        // get menu options data
        menuOptionsLeft = getResources().getStringArray(R.array.drawer_menu_left);
        menuOptionsRight = getResources().getStringArray(R.array.drawer_menu_right);

        // setup menus via adaptor
        drawerLeftListView.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.menu_textview_left,
                menuOptionsLeft
        ));
        drawerRightListView.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.menu_textview_right,
                menuOptionsRight
        ));

        // select handlers
        drawerLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: loading fragment content into content view

                // set current activity title
                setTitle(menuOptionsLeft[position]);
                // set active menu option
                drawerLeftListView.setItemChecked(position, true);
                // close drawer
                drawerLayout.closeDrawer(drawerLeftListView);
            }
        });
        drawerRightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: loading fragment content into content view

                // set current activity title
                setTitle(menuOptionsRight[position]);
                // set active menu option
                drawerRightListView.setItemChecked(position, true);
                // close drawer
                drawerLayout.closeDrawer(drawerRightListView);
            }
        });
    }
}
