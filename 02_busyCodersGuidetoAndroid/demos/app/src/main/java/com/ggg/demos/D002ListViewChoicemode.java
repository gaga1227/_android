package com.ggg.demos;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class D002ListViewChoicemode extends ListActivity {

	// list data
	private static final String[] items = {
		"lorem", "ipsum", "dolor", "sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue", "purus"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_002_list_view_choicemode);

		// set array adaptor to list view
		setListAdapter(new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_multiple_choice,
				items));
	}
}
