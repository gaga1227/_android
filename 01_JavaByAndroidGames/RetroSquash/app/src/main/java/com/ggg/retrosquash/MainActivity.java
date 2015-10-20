package com.ggg.retrosquash;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	/**
	 * vars/refs
	 */

	Canvas canvas;

	//game / inner class object
	SquashCourtView squashCourtView;

	//Sound
	//initialize sound variables
	private SoundPool soundPool;
	private SoundPool.Builder soundPoolBuilder;
	private String[] soundAssetsFiles = {"sample1.ogg", "sample2.ogg", "sample3.ogg", "sample4.ogg"};
	private int[] soundAssets = {-1, -1, -1, -1};

	//For getting display details like the number of pixels
	Display display;
	Point size;
	int screenWidth;
	int screenHeight;

	//Game objects
	int racketWidth;
	int racketHeight;
	int ballWidth;
	Point racketPosition;
	Point ballPosition;

	//for ball movement
	boolean ballIsMovingLeft;
	boolean ballIsMovingRight;
	boolean ballIsMovingUp;
	boolean ballIsMovingDown;

	//for racket movement
	boolean racketIsMovingLeft;
	boolean racketIsMovingRight;

	//stats
	long lastFrameTime;
	int fps;
	int score;
	int lives;

	/**
	 * create sound pool
	 */
	private void _createSoundPool() {
		if ((android.os.Build.VERSION.SDK_INT) >= 21) {
			this.soundPoolBuilder = new SoundPool.Builder();
			this.soundPoolBuilder.setAudioAttributes(
					new AudioAttributes.Builder()
							.setUsage(AudioAttributes.USAGE_MEDIA)
							.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
							.build()
			);
			this.soundPool = soundPoolBuilder.build();
		} else {
			this.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		}
	}

	/**
	 * load SFX assets
	 * @param assets - sound assets array in String
	 */
	private void _loadSFX(String[] assets) {
		if (assets.length > 0) {
			try {
				//Create objects of the 2 required classes
				AssetManager assetManager = getAssets();
				AssetFileDescriptor descriptor;

				//create our three fx in memory ready for use
				for (int i = 0; i < assets.length; i++) {
					descriptor = assetManager.openFd("" + assets[i]);
					this.soundAssets[i] = this.soundPool.load(descriptor, 0);
				}
			} catch (IOException e) {
				//catch exceptions here
			}
		}
	}

	/**
	 * update display info
	 */
	private void _updateDisplayInfo() {
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.gameLayout);
		this.screenWidth = layout.getWidth();
		this.screenHeight = layout.getHeight();

		Log.i("gameLayout W: ", "" + this.screenWidth);
		Log.i("gameLayout H: ", "" + this.screenHeight);
	}

	/**
	 * game view inner class
	 */
	class SquashCourtView extends SurfaceView {
		public SquashCourtView(Context context) {
			super(context);
			Log.i("SquashCourtView: ", "init");
		}
	}

	/**
	 * onWindowFocusChanged
	 * @param hasFocus
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		this._updateDisplayInfo();
	}

	/**
	 * on activity create
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//create game view and set as layout content
		this.squashCourtView = new SquashCourtView(this);
		this.squashCourtView.setLayoutParams(
				new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.MATCH_PARENT)
		);
		ViewGroup gameLayout = (ViewGroup)findViewById(R.id.gameLayout);
		gameLayout.addView(this.squashCourtView);

		//sound related
		this._createSoundPool();
		this._loadSFX(this.soundAssetsFiles);

		//The game objects
		this.racketPosition = new Point();
		this.racketPosition.x = this.screenWidth / 2;
		this.racketPosition.y = this.screenHeight - 20;
		this.racketWidth = this.screenWidth / 8;
		this.racketHeight = 10;
		this.ballWidth = this.screenWidth / 35;
		this.ballPosition = new Point();
		this.ballPosition.x = this.screenWidth / 2;
		this.ballPosition.y = 1 + this.ballWidth;
		this.lives = 3;
	}
}
