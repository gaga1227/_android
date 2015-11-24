package com.ggg.demos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class D001SpinnerView extends Activity implements AdapterView.OnItemSelectedListener {

	private TextView selection;
	private static final String[] items = {"lorem", "ipsum", "dolor"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_001_spinner_view);

		// get views
		selection = (TextView)findViewById(R.id.selection);
		android.widget.Spinner spin = (android.widget.Spinner)findViewById(R.id.spinner);

		// link data to adaptor, then to spinner view
		ArrayAdapter<String> aa = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item,
				items);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);

		// spinner handlers
		spin.setOnItemSelectedListener(this);
	}

	/**
	 * <p>Callback method to be invoked when an item in this view has been
	 * selected. This callback is invoked only when the newly selected
	 * position is different from the previously selected position or if
	 * there was no selected item.</p>
	 * <p/>
	 * Impelmenters can call getItemAtPosition(position) if they need to access the
	 * data associated with the selected item.
	 *
	 * @param parent   The AdapterView where the selection happened
	 * @param view     The view within the AdapterView that was clicked
	 * @param position The position of the view in the adapter
	 * @param id       The row id of the item that is selected
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		selection.setText("Selected: " + items[position]);
	}

	/**
	 * Callback method to be invoked when the selection disappears from this
	 * view. The selection can disappear for instance when touch is activated
	 * or when the adapter becomes empty.
	 *
	 * @param parent The AdapterView that now contains no selected item.
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		selection.setText("");
	}
}
