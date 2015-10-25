package com.ggg.snake;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.Random;

public class GameActivity extends Activity {

	//game view
	SnakeView snakeView;

	//graphics
	Bitmap headBitmap;
	Bitmap bodyBitmap;
	Bitmap tailBitmap;
	Bitmap appleBitmap;

	//Sound
	private SoundPool soundPool;
	private SoundPool.Builder soundPoolBuilder;
	private String[] soundAssetsFiles = {"sample1.ogg", "sample2.ogg", "sample3.ogg", "sample4.ogg"};
	private int[] soundAssets = {-1, -1, -1, -1};

	//screen related
	int topGap;
	int screenWidth;
	int screenHeight;

	//stats
	long lastFrameTime;
	long threadInterval = 500;
	int score;
	int fps;

	//The size in pixels of a place on the game board
	int blockSize;
	int numBlocksWide = 20;
	int numBlocksHigh;

	//Game objects
	int snakePosLimit;
	int [] snakeX;
	int [] snakeY;
	int snakeLength;
	int appleX;
	int appleY;

	//for snake movement
	//0 = up, 1 = right, 2 = down, 3= left
	int directionOfTravel = 3;

	/**
	 * onCreate
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//set orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//request to remove title
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);

		//create menu view and set as layout content
		this.snakeView = new SnakeView(this);
		this.snakeView.setLayoutParams(
				new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.MATCH_PARENT)
		);
		ViewGroup gameLayout = (ViewGroup)findViewById(R.id.gameLayout);
		gameLayout.addView(this.snakeView);

		//sound related
		this._createSoundPool();
		this._loadSFX(this.soundAssetsFiles);

		//load graphics
		this._loadGameGraphics();
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
	 * onTouchEvent
	 * @param motionEvent
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		float currentTouchX;

		//switch on event type if getAction() is also valid
		switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_UP:
				//get current touch x
				currentTouchX = motionEvent.getX();

				//touch on right half
				if (currentTouchX >= screenWidth / 2) {
					//turn right
					directionOfTravel++;

					//loop back to 0
					if(directionOfTravel == 4) {
						directionOfTravel = 0;
					}

					//play sfx
					soundPool.play(soundAssets[1], 1, 1, 0, 0, 1);
				}
				//touch on left half
				else {
					//turn right
					directionOfTravel--;

					//loop back to 0
					if(directionOfTravel == -1) {
						directionOfTravel = 3;
					}

					//play sfx
					soundPool.play(soundAssets[2], 1, 1, 0, 0, 1);
				}
		}

		//handled
		return true;
	}

	/**
	 * onKeyDown
	 * @param keyCode
	 * @param event
	 * @return
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return false;
	}

	/**
	 * onPause
	 */
	@Override
	protected void onPause() {
		super.onPause();
		this.snakeView.pause();
	}

	/**
	 * onResume
	 */
	@Override
	protected void onResume() {
		super.onResume();
		this.snakeView.resume();
	}

	/**
	 * onStop
	 */
	@Override
	protected void onStop() {
		super.onStop();
		while (true) {
			this.snakeView.pause();
			break;
		}
		//stop this activity
		finish();
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
	 * load Game Graphics
	 */
	private void _loadGameGraphics() {
		headBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_head);
		bodyBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_body);
		tailBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_tail);
		appleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_apple);
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
	 * update Game Objects
	 */
	private void _updateGameObjects() {
		//work out top gap and screen blocks
		topGap = screenHeight / 14;
		blockSize = screenWidth / numBlocksWide;
		numBlocksHigh = (screenHeight - topGap) / blockSize;

		//scale the bitmaps to match the block size
		headBitmap = Bitmap.createScaledBitmap(headBitmap, blockSize, blockSize, false);
		bodyBitmap = Bitmap.createScaledBitmap(bodyBitmap, blockSize, blockSize, false);
		tailBitmap = Bitmap.createScaledBitmap(tailBitmap, blockSize, blockSize, false);
		appleBitmap = Bitmap.createScaledBitmap(appleBitmap, blockSize, blockSize, false);
	}

	/**
	 * game view inner class
	 */
	class SnakeView extends SurfaceView implements Runnable {

		Thread ourThread = null;

		SurfaceHolder ourHolder;
		Canvas canvas;

		volatile boolean playingGame;

		/**
		 * inner class constructor
		 * @param context
		 */
		public SnakeView(Context context) {
			super(context);
			Log.i("SnakeView: ", "constructor");

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
				if (this.playingGame && screenWidth > 0) {
					Log.i("Game Thread", "in Progress");

					this.update();
					this.draw();
					this.controlFPS();
				}
			}
		}

		/**
		 * init game
		 */
		private void _init() {
			//assign refs
			this.ourHolder = getHolder();

			//snake position array with upper limit
			snakePosLimit = 200;
			snakeX = new int[snakePosLimit];
			snakeY = new int[snakePosLimit];

			//reset score
			score = 0;

			//prep initial game objects models
			this.getSnake();
			this.getApple();
		}

		/**
		 * add new snake model to screen center
		 */
		private void getSnake() {
			snakeLength = 3;

			//start snake head in the middle of screen
			snakeX[0] = numBlocksWide / 2;
			snakeY[0] = numBlocksWide / 2;

			//Then the body (to the left)
			snakeX[1] = snakeX[0] + 1;
			snakeY[1] = snakeY[0];

			//And the tail (to the left again)
			snakeX[2] = snakeX[1] + 1;
			snakeY[2] = snakeY[1];
		}

		/**
		 * get random new apple screen position
		 */
		private void getApple(){
			appleX = this.getRandomBlock();
			appleY = this.getRandomBlock();
		}

		/**
		 * getRandomBlock
		 * @return random block position in one axis
		 */
		private int getRandomBlock() {
			Random random = new Random();
			return (random.nextInt(numBlocksWide - 1) + 1);
		}

		/**
		 * update
		 */
		private void update() {
			int headRotation = 0;
			int tailRotation = 0;

			//update display info and related properties
			_updateDisplayInfo();

			//update display game objects
			_updateGameObjects();

			//Check if the player get the apple
			if(snakeX[0] == appleX && snakeY[0] == appleY) {
				//grow snake
				snakeLength++;
				//update score
				score = score + snakeLength;
				//respawn apple
				this.getApple();
				//play sfx
				soundPool.play(soundAssets[3], 1, 1, 0, 0, 1);
			}

			//move the snake body only, starting from the back
			for(int i = snakeLength; i > 0; i--){
				//copy position from previous segment
				snakeX[i] = snakeX[i-1];
				snakeY[i] = snakeY[i-1];
			}

			//work out tail rotation based on previous segments
			if (snakeY[snakeLength-2] == snakeY[snakeLength-1]) {
				//direction is right
				if (snakeX[snakeLength-2] > snakeX[snakeLength-1]) {
					tailRotation = 180;
				}
				//direction is left
				else {
					tailRotation = 0;
				}
			}
			else if (snakeX[snakeLength-2] == snakeX[snakeLength-1]) {
				//direction is down
				if (snakeY[snakeLength-2] > snakeY[snakeLength-1]) {
					tailRotation = -90;
				}
				//direction is up
				else {
					tailRotation = 90;
				}
			}

			//Move the head in the appropriate direction
			switch (directionOfTravel){
				case 0://up
					snakeY[0]--;
					headRotation = 90;
					break;
				case 1://right
					snakeX[0]++;
					headRotation = 180;
					break;
				case 2://down
					snakeY[0]++;
					headRotation = -90;
					break;
				case 3://left
					snakeX[0]--;
					headRotation = 0;
					break;
			}

			Log.i("Snake Head X", "" + snakeX[0]);
			Log.i("Snake Head Y", "" + snakeY[0]);

			//rotate head and tail images
			Matrix matrixHead = new Matrix();
			Matrix matrixTail = new Matrix();
			matrixHead.postRotate(headRotation);
			matrixTail.postRotate(tailRotation);

			//create a new bitmap from the original using the matrix to transform the result
			headBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_head);
			headBitmap = Bitmap.createBitmap(
					headBitmap,
					0, 0, headBitmap.getWidth(), headBitmap.getHeight(),
					matrixHead, true);
			headBitmap = Bitmap.createScaledBitmap(headBitmap, blockSize, blockSize, false);

			tailBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_tail);
			tailBitmap = Bitmap.createBitmap(
					tailBitmap,
					0, 0, tailBitmap.getWidth(), tailBitmap.getHeight(),
					matrixTail, true);
			tailBitmap = Bitmap.createScaledBitmap(tailBitmap, blockSize, blockSize, false);

			//Have we had an accident
			boolean dead = false;

			//with a wall
			if (snakeX[0] <= -1 || snakeY[0] <= -1) dead = true;
			if (snakeX[0] >= numBlocksWide || snakeY[0] >= numBlocksHigh) dead = true;

			//or eaten ourselves?
			for (int i = snakeLength - 1; i > 0; i--) {
				if ((i > 4) && (snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])) {
					dead = true;
				}
			}

			//start again if dead
			if (dead) {
				//reset
				score = 0;
				directionOfTravel = 3;//left

				//reset graphics
				headBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_head);
				tailBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_tail);
				headBitmap = Bitmap.createScaledBitmap(headBitmap, blockSize, blockSize, false);
				tailBitmap = Bitmap.createScaledBitmap(tailBitmap, blockSize, blockSize, false);

				//respawn snake
				getSnake();
				//play sfx
				soundPool.play(soundAssets[0], 1, 1, 0, 0, 1);

				Log.i("Snake Dead", "Reset");
			}
		}

		/**
		 * draw
		 */
		private void draw() {
			if (this.ourHolder.getSurface().isValid()) {
				//lock canvas to start drawing
				this.canvas = this.ourHolder.lockCanvas();

				//set paint
				Paint paint = new Paint();
				paint.setColor(Color.argb(255, 255, 255, 255));
				paint.setTextAlign(Paint.Align.CENTER);
				paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
				paint.setTextSize(topGap / 2);

				//clear last frame by setting the background and
				this.canvas.drawColor(Color.BLACK);

				//draw score text
				this.canvas.drawText(
						"Score: " + score,
						this.canvas.getWidth() / 2,
						topGap - 6,
						paint);

				//draw a border - 4 lines, top right, bottom, left
				paint.setStrokeWidth(3);//4 pixel border
				this.canvas.drawLine(
						1,
						topGap,
						screenWidth - 1,
						topGap,
						paint);
				this.canvas.drawLine(
						screenWidth - 1,
						topGap,
						screenWidth - 1,
						topGap + (numBlocksHigh * blockSize),
						paint);
				this.canvas.drawLine(
						screenWidth - 1,
						topGap + (numBlocksHigh * blockSize),
						1,
						topGap + (numBlocksHigh * blockSize),
						paint);
				this.canvas.drawLine(
						1,
						topGap,
						1,
						topGap + (numBlocksHigh * blockSize),
						paint);

				//Draw the snake head
				this.canvas.drawBitmap(
						headBitmap,
						snakeX[0] * blockSize,
						(snakeY[0] * blockSize) + topGap,
						paint);

				//Draw body
				for(int i = 1; i < snakeLength - 1; i++) {
					this.canvas.drawBitmap(
							bodyBitmap,
							snakeX[i] * blockSize,
							(snakeY[i] * blockSize) + topGap,
							paint);
				}

				//Draw tail
				this.canvas.drawBitmap(
						tailBitmap,
						snakeX[snakeLength - 1] * blockSize,
						(snakeY[snakeLength - 1] * blockSize) + topGap,
						paint);

				//draw the apple
				this.canvas.drawBitmap(
						appleBitmap,
						appleX * blockSize,
						(appleY * blockSize) + topGap,
						paint);

				//completed drawing and update to canvas
				this.ourHolder.unlockCanvasAndPost(this.canvas);
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

		/**
		 * pause
		 */
		public void pause() {
			this.playingGame = false;
			//kill game thread
			if(this.ourThread != null) {
				this.ourThread.interrupt();
				this.ourThread = null;
			}
		}

		/**
		 * resume
		 */
		public void resume() {
			this.playingGame = true;
			//start game thread
			if(this.ourThread == null) {
				this.ourThread = new Thread(this);
				this.ourThread.start();
			}
		}
	}
}
