package com.ggg.persistence;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	//vars
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	String dataName = "MyData";
	String stringName = "MyString";
	String defaultString = ":-(";
	String currentString = "";
	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//initialize our two SharedPreferences objects
		prefs = getSharedPreferences(dataName, MODE_PRIVATE);
		editor = prefs.edit();
		//Either load our string or
		//if not available our default string
		currentString = prefs.getString(stringName, defaultString);

		//button
		button1 = (Button)findViewById(R.id.button);
		button1.setText(currentString);
		button1.setOnClickListener(this);
	}

	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		//Get a random number between 0 and 9
		Random randInt = new Random();
		int ourRandom = randInt.nextInt(10);

		//Add the random number to the end of currentString
		currentString = currentString + ourRandom;

		//Save currentString to a file in case the user
		//suddenly quits or gets a phone call
		editor.putString(stringName, currentString);
		editor.commit();

		//update the button text
		button1.setText(currentString);
	}
}
