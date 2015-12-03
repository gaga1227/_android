package com.ggg.demos;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.Arrays;

public class D004AutoCompleteTextView extends Activity implements TextWatcher {

	// vars
	private static final String[] items = {
		"January",
		"February",
		"March",
		"April",
		"May",
		"June",
		"July",
		"August",
		"September",
		"October",
		"November",
		"December"
	};
	private String selectionPrefix = "My favourite month is: ";
	private String selectedMonth = "???";

	private TextView selection;
	private AutoCompleteTextView edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_004_auto_complete_text_view);

		// find views
		selection = (TextView) findViewById(R.id.selection);
		edit = (AutoCompleteTextView) findViewById(R.id.edit);

		// set initial selection content
		selection.setText(selectionPrefix + selectedMonth);

		// register text change listener
		edit.addTextChangedListener(this);

		// add text candidates date via adaptor
		edit.setAdapter(new ArrayAdapter<String>(
				this,
				android.R.layout.simple_dropdown_item_1line,
				items));
	}

	/**
	 * This method is called to notify you that, within <code>s</code>,
	 * the <code>count</code> characters beginning at <code>start</code>
	 * are about to be replaced by new text with length <code>after</code>.
	 * It is an error to attempt to make changes to <code>s</code> from
	 * this callback.
	 *
	 * @param s
	 * @param start
	 * @param count
	 * @param after
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	/**
	 * This method is called to notify you that, within <code>s</code>,
	 * the <code>count</code> characters beginning at <code>start</code>
	 * have just replaced old text that had length <code>before</code>.
	 * It is an error to attempt to make changes to <code>s</code> from
	 * this callback.
	 *
	 * @param s
	 * @param start
	 * @param before
	 * @param count
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// get current input text
		selectedMonth = "" + edit.getEditableText();
		// reset selectedMonth value if changed text is not found in list
		if (!Arrays.asList(items).contains(selectedMonth)) {
			selectedMonth = "???";
		}
		// update view with validated selection text result
		selection.setText(selectionPrefix + selectedMonth);
	}

	/**
	 * This method is called to notify you that, somewhere within
	 * <code>s</code>, the text has been changed.
	 * It is legitimate to make further changes to <code>s</code> from
	 * this callback, but be careful not to get yourself into an infinite
	 * loop, because any changes you make will cause this method to be
	 * called again recursively.
	 * (You are not told where the change took place because other
	 * afterTextChanged() methods may already have made other changes
	 * and invalidated the offsets.  But if you need to know here,
	 * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
	 * to mark your place and then look up from here where the span
	 * ended up.
	 *
	 * @param s
	 */
	@Override
	public void afterTextChanged(Editable s) {

	}
}
