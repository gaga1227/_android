package com.commonsware.empublite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;

public class EmPubLiteActivity extends Activity {
	String[] items = {"this", "is", "a", "really", "silly", "list"};
	ListView listView;
	ArrayAdapter itemsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//create adaptor
		itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		//use adaptor
		listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(itemsAdapter);
	}

	//buttonUpdate onClick handler in XML
	public void shuffleList(View view) {
		Collections.shuffle(Arrays.asList(items));
		listView.setAdapter(itemsAdapter);

		Log.i("buttonUpdate", "clicked");
		Log.i("items", "" + items[0]);
	}
}
