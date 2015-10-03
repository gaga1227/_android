package com.ggg.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Make a button from the button in our layout
		Button button =(Button)findViewById(R.id.button);
		//Make each it listen for clicks
		button.setOnClickListener(this);
	}

	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		//go to game activity
		Intent i;
		i = new Intent(this, GameActivity.class);
		startActivity(i);
	}
}
