package com.ggg.demos;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class D005ListViewCustomRow extends ListActivity {

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
		setContentView(R.layout.demo_005_list_view_custom_row);

		// set custom array adaptor to list view
		setListAdapter(new IconicAdapter());
	}

	/**
	 * Get list data item at a position and return data value
	 * @param position - data item position in the list
	 * @return - list item data
	 */
	private String getItemModel(int position) {
		return ((IconicAdapter) getListAdapter()).getItem(position);
	}

	/**
	 * Inner class for custom adaptor
	 */
	class IconicAdapter extends ArrayAdapter<String> {

		/**
		 * Constructor
		 */
		IconicAdapter() {
			super(
				D005ListViewCustomRow.this, // pass in this from parent(activity)
				R.layout.demo_005_list_view_custom_row_row,
				R.id.label,
				items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// create view of single row from template: demo_005_list_view_custom_row_row
			// with given params using super method
			View row = super.getView(position, convertView, parent);

			// try reuse a row using view holder pattern
			D005ListViewCustomRowViewHolder viewholder =
					(D005ListViewCustomRowViewHolder) row.getTag();

			// if current row has no view holder defined via 'getTag'
			// create a new one and assign it to row view via 'setTag'
			if (viewholder == null) {
				viewholder = new D005ListViewCustomRowViewHolder(row);
				row.setTag(viewholder);
			}

			// customise view based on model data length
			int labelLength = getItemModel(position).length();
			viewholder.icon.setImageResource(labelLength > 4 ? R.mipmap.delete : R.mipmap.ok);
			viewholder.size.setText(String.format(getString(R.string.size_template), labelLength));

			// finally return customised row view for display
			return row;
		}
	}
}
