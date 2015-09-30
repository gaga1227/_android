package com.ggg.simplethreadtimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	//vars
	private Handler myHandler;
	boolean gameOn;
	long secondsElapsed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//thread handler
		myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				if (gameOn) {
					secondsElapsed++;
					Log.i("info", "seconds = " + secondsElapsed);
					myHandler.sendEmptyMessageDelayed(0, 1000);
				}
			}
		};

		//reset elapsed time
		secondsElapsed = -1;
	}

	@Override
	protected void onPause() {
		super.onPause();

		//pause game/thread
		gameOn = false;
	}

	@Override
	protected void onResume() {
		super.onResume();

		//start/resume game/thread
		gameOn = true;
		myHandler.sendEmptyMessage(0);
	}
}
