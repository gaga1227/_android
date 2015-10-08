package com.ggg.memorygame;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

	//Prepare objects and sound references

	//initialize sound variables
	private SoundPool soundPool;
	private SoundPool.Builder soundPoolBuilder;
	int sample1 = -1;
	int sample2 = -1;
	int sample3 = -1;
	int sample4 = -1;

	//for our UI
	TextView textScore;
	TextView textDifficulty;
	TextView textWatchGo;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button buttonReplay;

	//Some variables for our thread
	int difficultyLevel = 3;

	//Game sequence
	int[] sequenceToCopy = new int[100];
	int elementToPlay = 0;
	boolean playSequence = false;

	//For checking the players answer
	int playerResponses;
	int playerScore;
	boolean isResponding;

	//thread handler
	private Handler myHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

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
			descriptor = assetManager.openFd("sample4.ogg");
			sample4 = soundPool.load(descriptor, 0);
		} catch (IOException e) {
			//catch exceptions here
		}

		//First the TextViews
		_updateTextViews();

		//Now the buttons
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		buttonReplay = (Button) findViewById(R.id.buttonReplay);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		buttonReplay.setOnClickListener(this);

		//This is the code which will define our thread
		myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				if (playSequence) {
					//All the thread action will go here
				}

				//send next one in 900ms
				myHandler.sendEmptyMessageDelayed(0, 900);
			}
		};//end of thread handler

		//kick of thread
		myHandler.sendEmptyMessage(0);
	};

	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {

	}

	//function - update all text views
	private void _updateTextViews() {
		//assign text view refs
		if (textScore == null) {
			textScore = (TextView)findViewById(R.id.textScore);
		}
		if (textDifficulty == null) {
			textDifficulty = (TextView)findViewById(R.id.textDifficulty);
		}
		if (textWatchGo == null) {
			textWatchGo = (TextView)findViewById(R.id.textWatchGo);
		}

		//set values
		textScore.setText("Score: " + playerScore);
		textDifficulty.setText("Level: " + difficultyLevel);
	}

	//functions - game sequence
	public void createSequence() {
		//For choosing a random button
		Random randInt = new Random();
		int ourRandom;
		//clean existing sequence array
		sequenceToCopy = new int[sequenceToCopy.length];
		//generate sequence steps based on difficulty
		for (int i = 0; i < difficultyLevel; i++) {
			//get a random number between 1 and 4
			ourRandom = randInt.nextInt(4);
			//make sure it is not zero
			ourRandom ++;
			//Save that number to our array
			sequenceToCopy[i] = ourRandom;
		}
	}

	public void playASequence() {
		//prep sequence
		createSequence();
		//prep vars and view
		isResponding = false;
		elementToPlay = 0;
		playerResponses = 0;
		textWatchGo.setText("WATCH!");
		//start playing
		playSequence = true;
	}

	public void sequenceFinished(){
		//stop playing
		playSequence = false;
		//prep vars and view
		//make sure all the buttons are made visible
		button1.setVisibility(View.VISIBLE);
		button2.setVisibility(View.VISIBLE);
		button3.setVisibility(View.VISIBLE);
		button4.setVisibility(View.VISIBLE);
		textWatchGo.setText("GO!");
		isResponding = true;
	}
}
