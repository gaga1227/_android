package com.ggg.retrosquash;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.Random;

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
	int racketXLeftLimit;
	int racketXRightLimit;
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
	long threadInterval = 16;
	int fps;
	int score;
	int lives;

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
		this.ballPosition = new Point();
		this.racketHeight = 10;
		this.lives = 3;
	}

	/**
	 * onWindowFocusChanged
	 * @param hasFocus
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		this._updateDisplayInfo();
		this._updateDisplayObjects();
	}

	/**
	 * onPause
	 */
	@Override
	protected void onPause() {
		super.onPause();
		this.squashCourtView.pause();
	}

	/**
	 * onResume
	 */
	@Override
	protected void onResume() {
		super.onResume();
		this.squashCourtView.resume();
	}

	/**
	 * onStop
	 */
	@Override
	protected void onStop() {
		super.onStop();
		while (true) {
			this.squashCourtView.pause();
			break;
		}
		//stop this activity
		finish();
	}

	/**
	 * Take care of calling onBackPressed() for pre-Eclair platforms.
	 * @param keyCode
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//if back button pressed
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//handled
			return true;
		}
		return false;
	}

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

		Log.i("_updateDisplayInfo", "gameLayout Width: " + this.screenWidth);
		Log.i("_updateDisplayInfo", "gameLayout Height: " + this.screenHeight);
	}

	/**
	 * update display objects
	 */
	private void _updateDisplayObjects() {
		this.racketWidth = this.screenWidth / 8;
		this.racketPosition.x = this.screenWidth / 2;
		this.racketPosition.y = this.screenHeight - 20;
		this.racketXLeftLimit = this.racketWidth / 2;
		this.racketXRightLimit = this.screenWidth - this.racketWidth / 2;
		this.ballWidth = this.screenWidth / 35;
		this.ballPosition.x = this.screenWidth / 2;
		this.ballPosition.y = 1 + this.ballWidth;

		Log.i("_updateDisplayObjects", "racketWidth " + this.racketWidth);
		Log.i("_updateDisplayObjects", "racketPosition.x " + this.racketPosition.x);
		Log.i("_updateDisplayObjects", "racketPosition.y " + this.racketPosition.y);
		Log.i("_updateDisplayObjects", "racketXLeftLimit " + this.racketXLeftLimit);
		Log.i("_updateDisplayObjects", "racketXRightLimit " + this.racketXRightLimit);
		Log.i("_updateDisplayObjects", "ballWidth " + this.ballWidth);
		Log.i("_updateDisplayObjects", "ballPosition.x " + this.ballPosition.x);
		Log.i("_updateDisplayObjects", "ballPosition.y " + this.ballPosition.y);
	}

	/**
	 * game view inner class
	 */
	class SquashCourtView extends SurfaceView implements Runnable {

		/**
		 * vars/refs
		 */
		Thread ourThread = null;
		SurfaceHolder ourHolder;
		Paint paint;
		volatile boolean playingSquash; //will change its value from outside and inside of our thread
		int ballDirection;

		/**
		 * inner class constructor
		 * @param context
		 */
		public SquashCourtView(Context context) {
			super(context);
			Log.i("SquashCourtView: ", "constructor");

			this._init();
		}

		/**
		 * Starts executing the active part of the class code. This method is
		 * called when a thread is started that has been created with a class which
		 * implements {@code Runnable}.
		 */
		@Override
		public void run() {
			while (Thread.currentThread() == this.ourThread) {
				if (this.playingSquash && screenWidth > 0) {
					Log.i("Game Thread", "in Progress");

					//update game and model
					this.updateCourt();
					//draw UI
					this.drawCourt();
					//maintain thread FPS
					this.controlFPS();
				}
			}
		}

		/**
		 * onTouchEvent
		 * @param motionEvent The motion event.
		 * @return True if the event was handled, false otherwise.
		 */
		@Override
		public boolean onTouchEvent(MotionEvent motionEvent) {
			float currentTouchX;

			//switch on event type if getAction() is also valid
			switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
				//touchdown - check relative position and set x direction
				case MotionEvent.ACTION_DOWN:
					//get current touch x
					currentTouchX = motionEvent.getX();

					//if x pos overlay racket
					if (currentTouchX >= racketPosition.x - racketWidth / 2
						&& currentTouchX <= racketPosition.x + racketWidth / 2) {
						//remove direction
						racketIsMovingLeft = false;
						racketIsMovingRight = false;
					}
					//if x pos is to the racket's right
					else if (currentTouchX > racketPosition.x + racketWidth / 2) {
						//set direction
						racketIsMovingLeft = false;
						racketIsMovingRight = true;
					}
					//if x pos is to the racket's left
					else {
						//set direction
						racketIsMovingLeft = true;
						racketIsMovingRight = false;
					}
					break;

				//touchup - reset x directions on up
				case MotionEvent.ACTION_UP:
					//remove direction
					racketIsMovingLeft = false;
					racketIsMovingRight = false;
					break;
			}
			//handled
			return true;
		}

		/**
		 * pause
		 */
		public void pause() {
			//kill game thread
			this.playingSquash = false;
			if(this.ourThread != null) {
				this.ourThread.interrupt();
				this.ourThread = null;
			}
		}

		/**
		 * resume
		 */
		public void resume() {
			//start game thread
			this.playingSquash = true;
			if(this.ourThread == null) {
				this.ourThread = new Thread(this);
				this.ourThread.start();
			}
		}

		/**
		 * init game
		 */
		private void _init() {
			//assign refs
			this.ourHolder = getHolder();
			this.paint = new Paint();

			//set initial ball direction
			ballIsMovingDown = true;
			this._setRandomBallDirection();
		}

		/**
		 * _setRandomBallDirection
		 */
		private void _setRandomBallDirection() {
			//Send the ball in random direction
			Random randomNumber = new Random();
			ballDirection = randomNumber.nextInt(3);
			switch (this.ballDirection) {
				case 0:
					ballIsMovingLeft = true;
					ballIsMovingRight = false;
					break;
				case 1:
					ballIsMovingLeft = false;
					ballIsMovingRight = true;
					break;
				case 2:
					ballIsMovingLeft = false;
					ballIsMovingRight = false;
					break;
			}
		}

		/**
		 * updateCourt and game data model
		 */
		private void updateCourt() {
			//update racket position model
			if (racketIsMovingRight) {
				racketPosition.x += 10;
				if (racketPosition.x >= racketXRightLimit) {
					racketPosition.x = racketXRightLimit;
				}
			}
			else if (racketIsMovingLeft) {
				racketPosition.x -= 10;
				if (racketPosition.x <= racketXLeftLimit) {
					racketPosition.x = racketXLeftLimit;
				}
			}

			/**
			 * detect collisions
			 */

			//sound
			int soundToPlay = -1;

			//hit right of screen
			if (ballPosition.x + ballWidth > screenWidth) {
				ballIsMovingLeft = true;
				ballIsMovingRight = false;

				soundToPlay = soundAssets[0];
			}
			//hit left of screen
			if (ballPosition.x < 0) {
				ballIsMovingLeft = false;
				ballIsMovingRight = true;

				soundToPlay = soundAssets[0];
			}

			//we hit the top of the screen
			if (ballPosition.y <= 0) {
				ballIsMovingDown = true;
				ballIsMovingUp = false;
				ballPosition.y = 1;

				soundToPlay = soundAssets[1];
			}

			//Edge of ball has hit bottom of screen
			if (ballPosition.y > screenHeight - ballWidth) {
				//lose life
				lives -= 1;

				//reset if game over
				if (lives <= 0) {
					lives = 3;
					score = 0;

					soundToPlay = soundAssets[2];
				}

				//set ball position
				Random randomNumber = new Random();
				Log.i("info screenWidth: ", ""+screenWidth);
				int startX = randomNumber.nextInt(screenWidth - ballWidth) + 1;
				ballPosition.x = startX + ballWidth;
				ballPosition.y = 1 + ballWidth;

				//what horizontal direction should we use
				//for the next falling ball
				this._setRandomBallDirection();
			}

			//depending upon the two directions we should
			//be moving in adjust our x any positions
			if (ballIsMovingDown) {
				ballPosition.y += 6;
			}
			if (ballIsMovingUp) {
				ballPosition.y -= 10;
			}
			if (ballIsMovingLeft) {
				ballPosition.x -= 12;
			}
			if (ballIsMovingRight) {
				ballPosition.x += 12;
			}

			//Has ball hit racket
			if (ballPosition.y + ballWidth >= (racketPosition.y - racketHeight / 2)) {
				int halfRacket = racketWidth / 2;
				if (ballPosition.x + ballWidth > (racketPosition.x - halfRacket)
					&& ballPosition.x - ballWidth < (racketPosition.x + halfRacket)) {

					//rebound the ball vertically and play a sound
					soundToPlay = soundAssets[3];
					score++;
					ballIsMovingUp = true;
					ballIsMovingDown = false;

					//now decide how to rebound the ball horizontally
					if (ballPosition.x > racketPosition.x) {
						ballIsMovingRight = true;
						ballIsMovingLeft = false;
					} else {
						ballIsMovingRight = false;
						ballIsMovingLeft = true;
					}
				}
			}

			//play sound
			if (soundToPlay != -1) {
				soundPool.play(soundToPlay, 1, 1, 0, 0, 1);
			}

			//logs
			Log.i("racketWidth", "" + racketWidth);
			Log.i("racketHeight", "" + racketHeight);
			Log.i("racketPosition.x", "" + racketPosition.x);
			Log.i("racketPosition.y", "" + racketPosition.y);

			Log.i("racketIsMovingLeft", "" + racketIsMovingLeft);
			Log.i("racketIsMovingRight", "" + racketIsMovingRight);

			Log.i("ballWidth", "" + ballWidth);
			Log.i("ballPosition.x", "" + ballPosition.x);
			Log.i("ballPosition.y", "" + ballPosition.y);

			Log.i("ballIsMovingUp", "" + ballIsMovingUp);
			Log.i("ballIsMovingRight", "" + ballIsMovingRight);
			Log.i("ballIsMovingDown", "" + ballIsMovingDown);
			Log.i("ballIsMovingLeft", "" + ballIsMovingLeft);
		}

		/**
		 * drawCourt
		 */
		private void drawCourt() {
			if (this.ourHolder.getSurface().isValid()) {
				//lock canvas to start drawing
				canvas = this.ourHolder.lockCanvas();

				//set paint
				Paint paint = new Paint();
				paint.setColor(Color.argb(255, 255, 255, 255));
				paint.setTextSize(45);

				//clear last frame by setting the background and
				canvas.drawColor(Color.BLACK);

				//stats text
				canvas.drawText("Score:" + score +
								" Lives:" + lives +
								" fps:" + fps,
								20, 40, paint);

				//the squash racket
				canvas.drawRect(racketPosition.x - (racketWidth / 2),
								racketPosition.y - (racketHeight / 2),
								racketPosition.x + (racketWidth / 2),
								racketPosition.y + racketHeight,
								paint);

				//the ball
				canvas.drawRect(ballPosition.x,
								ballPosition.y,
								ballPosition.x + ballWidth,
								ballPosition.y + ballWidth,
								paint);

				//completed drawing and update to canvas
				this.ourHolder.unlockCanvasAndPost(canvas);
			}
		}

		/**
		 * controlFPS
		 */
		private void controlFPS() {
			//delta time in milliseconds
			long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);

			//calculate FPS if has time difference
			if (timeThisFrame > 0) {
				fps = (int)(1000 / timeThisFrame);
			}

			//calculate time to sleep
			long timeToSleep = threadInterval - timeThisFrame;

			//sleep thread on interval
			if (timeToSleep > 0) {
				try {
					this.ourThread.sleep(timeToSleep);
				}
				catch (InterruptedException e) {
				}
			}

			//set current time as last frame time
			lastFrameTime = System.currentTimeMillis();
		}
	}
}
