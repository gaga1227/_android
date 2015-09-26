package com.ggg.mathgamechapter2;

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

		//Play button
		Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
		buttonPlay.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		Intent i;
		i = new Intent(this, GameActivity.class);
		startActivity(i);
	}
}
