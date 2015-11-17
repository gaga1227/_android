package com.ggg.demos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private static final String[] demos = {"Spinner Demo"};

	/**
	 * Create intent and start activity of a given class
	 * @param cls
	 */
	public void gotoActivity(Class<?> cls) {
		if (cls != null) {
			Intent intent = new Intent(this, cls);
			startActivity(intent);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// populate list view
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, demos));
	}

	/**
	 * This method will be called when an item in the list is selected.
	 * Subclasses should override. Subclasses can call
	 * getListView().getItemAtPosition(position) if they need to access the
	 * data associated with the selected item.
	 *
	 * @param l        The ListView where the click happened
	 * @param v        The view that was clicked within the ListView
	 * @param position The position of the view in the list
	 * @param id       The row id of the item that was clicked
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Log.i("onListItemClick l", "" + l);
		Log.i("onListItemClick v", "" + v);
		Log.i("onListItemClick pos", "" + position);
		Log.i("onListItemClick id", "" + id);

		Class<?> targetCls = null;

		switch (position) {
			case 0:
				targetCls = SpinnerDemo.class;
				break;
		}

		if (targetCls != null) {
			this.gotoActivity(SpinnerDemo.class);
		} else {
			Log.i("onListItemClick", "cannot find mapped activity class");
		}
	}
}
