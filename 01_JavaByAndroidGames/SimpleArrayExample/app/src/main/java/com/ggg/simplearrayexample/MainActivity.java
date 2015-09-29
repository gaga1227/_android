package com.ggg.simplearrayexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int[] ourArray;
		ourArray = new int[5];

		ourArray[0] = 25;
		ourArray[1] = 50;
		ourArray[2] = 125;
		ourArray[3] = 68;
		ourArray[4] = 47;

		Log.i("info", "Here is ourArray:");
		Log.i("info", "[0] = "+ourArray[0]);
		Log.i("info", "[1] = "+ourArray[1]);
		Log.i("info", "[2] = "+ourArray[2]);
		Log.i("info", "[3] = "+ourArray[3]);
		Log.i("info", "[4] = "+ourArray[4]);

		int answer = ourArray[0] +
				ourArray[1] +
				ourArray[2] +
				ourArray[3] +
				ourArray[4];

		Log.i("info", "Answer = " + answer);
	}
}
