package com.ggg.demos;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class D003GridView extends Activity implements AdapterView.OnItemClickListener {

	// vars
	private static final String[] items = {
		"lorem", "ipsum", "dolor",
		"sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue", "purus"
	};

	private TextView selection;
	private GridView grid;
	private int gridSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_003_grid_view);

		// get views
		selection = (TextView) findViewById(R.id.selection);
		grid = (GridView) findViewById(R.id.grid);

		// link data to grid view via adaptor
		grid.setAdapter(new ArrayAdapter<String>(
				this,
				R.layout.demo_003_grid_view_cell,
				items));

		// register grid view click handler
		grid.setOnItemClickListener(this);

		// set default selection text
		selection.setText("Select item to show text...");
	}

	/**
	 * Callback method to be invoked when an item in this AdapterView has
	 * been clicked.
	 * <p/>
	 * Implementers can call getItemAtPosition(position) if they need
	 * to access the data associated with the selected item.
	 *
	 * @param parent   The AdapterView where the click happened.
	 * @param view     The view within the AdapterView that was clicked (this
	 *                 will be a view provided by the adapter)
	 * @param position The position of the view in the adapter.
	 * @param id       The row id of the item that was clicked.
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// update selection view
		if (selection != null && items[position] != null) {
			selection.setText(items[(int)id]);
		}

		// highlight view
		if (view != null) {
			// update all children cell views
			gridSize = grid.getChildCount();
			for (int i = 0; i < gridSize; i++) {
				TextView cellView = (TextView) grid.getChildAt(i);
				if (cellView == view) {
					cellView.setBackgroundColor(Color.parseColor("#ff99ff"));
				} else {
					cellView.setBackgroundColor(0x00000000);
				}
			}
		}
	}
}
