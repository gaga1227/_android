package com.ggg.multidimensionalarrayexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//store data
		String[][] countriesAndCities;
		countriesAndCities = new String[5][2];

		countriesAndCities [0][0] = "United Kingdom";
		countriesAndCities [0][1] = "London";
		countriesAndCities [1][0] = "USA";
		countriesAndCities [1][1] = "Washington";
		countriesAndCities [2][0] = "India";
		countriesAndCities [2][1] = "New Delhi";
		countriesAndCities [3][0] = "Brazil";
		countriesAndCities [3][1] = "Brasilia";
		countriesAndCities [4][0] = "Kenya";
		countriesAndCities [4][1] = "Nairobi";

		//display
		int country = 0;
		int capital = 1;

		Random randInt = new Random();
		int questionNumber;

		for (int i = 0; i < 3; i++) {
			questionNumber = randInt.nextInt(5);
			Log.i("info", "The capital of " + countriesAndCities[questionNumber][country]);
			Log.i("info", "is " + countriesAndCities[questionNumber][capital]);
		}
	}
}
