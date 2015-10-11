package com.ggg.memorygame;

import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

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

	//for our high score
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	String dataName = "MyData";
	String intName = "MyInt";
	int defaultInt = 0;
	int hiScore;

	//UI animation
	Animation wobble;
	boolean animIsPlaying = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		//load animation
		wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);

		//initialize our two SharedPreferences objects
		prefs = getSharedPreferences(dataName, MODE_PRIVATE);
		hiScore = prefs.getInt(intName, defaultInt);
		editor = prefs.edit(); //to put and commit

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
					_playSequenceStep();
				}

				//send next one in 900ms
				myHandler.sendEmptyMessageDelayed(0, 1000);
			}
		};//end of thread handler

		//kick of thread
		myHandler.sendEmptyMessage(0);

		//start game
		playASequence();
	};

	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		//only accept input if sequence not playing
		if(!playSequence && !animIsPlaying) {
			int responseStep = 0;

			switch (v.getId()) {
				//sequence responses
				case R.id.button1:
					//play a sound
					soundPool.play(sample1, 1, 1, 0, 0, 1);
					checkElement(1);
					responseStep = 1;
					break;
				case R.id.button2:
					//play a sound
					soundPool.play(sample2, 1, 1, 0, 0, 1);
					checkElement(2);
					responseStep = 2;
					break;
				case R.id.button3:
					//play a sound
					soundPool.play(sample3, 1, 1, 0, 0, 1);
					checkElement(3);
					responseStep = 3;
					break;
				case R.id.button4:
					//play a sound
					soundPool.play(sample4, 1, 1, 0, 0, 1);
					checkElement(4);
					responseStep = 4;
					break;

				//reset game status
				case R.id.buttonReplay:
					difficultyLevel = 3;
					playerScore = 0;
					textScore.setText("Score: " + playerScore);
					playASequence();
					break;
			}

			//log
			if (responseStep > 0) {
				Log.i("info", "Step response: " + responseStep);
			}
		}
	}

	/**
	 * <p>Notifies the start of the animation.</p>
	 *
	 * @param animation The started animation.
	 */
	@Override
	public void onAnimationStart(Animation animation) {
		if (animation == wobble) {
			animIsPlaying = true;
		}
	}

	/**
	 * <p>Notifies the end of the animation. This callback is not invoked
	 * for animations with repeat count set to INFINITE.</p>
	 *
	 * @param animation The animation which reached its end.
	 */
	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == wobble) {
			animIsPlaying = false;
		}
	}

	/**
	 * <p>Notifies the repetition of the animation.</p>
	 *
	 * @param animation The animation which was repeated.
	 */
	@Override
	public void onAnimationRepeat(Animation animation) {

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

	//play a sequence step
	private void _playSequenceStep() {

		//play step from a sequence
		int step = sequenceToCopy[elementToPlay];
		switch (step) {
			case 1:
				//anim a button
				button1.startAnimation(wobble);
				//play a sound
				soundPool.play(sample1, 1, 1, 0, 0, 1);
				break;
			case 2:
				//anim a button
				button2.startAnimation(wobble);
				//play a sound
				soundPool.play(sample2, 1, 1, 0, 0, 1);
				break;
			case 3:
				//anim a button
				button3.startAnimation(wobble);
				//play a sound
				soundPool.play(sample3, 1, 1, 0, 0, 1);
				break;
			case 4:
				//anim a button
				button4.startAnimation(wobble);
				//play a sound
				soundPool.play(sample4, 1, 1, 0, 0, 1);
				break;
		}

		//log
		Log.i("info", "Step: " + step);

		//move to next step
		elementToPlay++;

		//check if reached sequence end
		if (elementToPlay == difficultyLevel) {
			sequenceFinished();
		}
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

		//start sequence with a delay
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//start playing
				playSequence = true;
			}
		}, 1000);
	}

	public void sequenceFinished(){
		//stop playing
		playSequence = false;
		//prep vars and view
		textWatchGo.setText("GO!");
		isResponding = true;
	}

	//check user response against generated sequence
	public void checkElement(int thisElement) {
		if (isResponding) {
			//increment response step
			playerResponses++;

			//step response is correct
			if (sequenceToCopy[playerResponses - 1] == thisElement) {
				//update score
				playerScore = playerScore + ((thisElement + 1) * 2);
				textScore.setText("Score: " + playerScore);

				//if got the whole sequence
				if (playerResponses == difficultyLevel) {
					//don't checkElement anymore
					isResponding = false;
					//now raise the difficulty
					difficultyLevel++;
					//and play another sequence
					playASequence();
				}
			}
			//step response incorrect
			else {
				textWatchGo.setText("FAILED!");

				//don't checkElement anymore
				isResponding = false;

				//store new high score if achieved
				if (playerScore > hiScore) {
					hiScore = playerScore;
					editor.putInt(intName, hiScore);
					editor.commit();

					//notify in UI
					Toast.makeText(getApplicationContext(), "New High Score", Toast.LENGTH_LONG).show();

					//log
					Log.i("info", "Store new high score: " + hiScore);
				}
			}
		}
	}
}
