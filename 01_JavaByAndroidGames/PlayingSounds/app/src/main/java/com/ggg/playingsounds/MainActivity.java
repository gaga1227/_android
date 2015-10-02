package com.ggg.playingsounds;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	//vars
	private SoundPool.Builder soundPoolBuilder;
	private SoundPool soundPool;
	int sample1 = -1;
	int sample2 = -1;
	int sample3 = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//create sound pool
		if ((android.os.Build.VERSION.SDK_INT) >= 21) {
			soundPoolBuilder = new SoundPool.Builder();
			soundPoolBuilder.setAudioAttributes(
				new AudioAttributes.Builder()
						.setUsage(AudioAttributes.USAGE_MEDIA)
						.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
						.build()
			);
			soundPool = soundPoolBuilder.build();
		} else {
			soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		}

		//load sfx assets
		try {
			//Create objects of the 2 required classes
			AssetManager assetManager = getAssets();
			AssetFileDescriptor descriptor;
			//create our three fx in memory ready for use
			descriptor = assetManager.openFd("sample1.ogg");
			sample1 = soundPool.load(descriptor, 0);
			descriptor = assetManager.openFd("sample2.ogg");
			sample2 = soundPool.load(descriptor, 0);
			descriptor = assetManager.openFd("sample3.ogg");
			sample3 = soundPool.load(descriptor, 0);
		} catch(IOException e) {
			//catch exceptions here
		}

		//Make a button from each of the buttons in our layout
		Button button1 =(Button) findViewById(R.id.button);
		Button button2 =(Button) findViewById(R.id.button2);
		Button button3 =(Button) findViewById(R.id.button3);

		//Make each of them listen for clicks
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
	}


	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		int sample;
		switch (v.getId()) {
			case R.id.button2:
				sample = sample2;
				break;
			case R.id.button3:
				sample = sample3;
				break;
			default:
				sample = sample1;
		}
		soundPool.play(sample, 1, 1, 0, 0, 1);
	}
}
