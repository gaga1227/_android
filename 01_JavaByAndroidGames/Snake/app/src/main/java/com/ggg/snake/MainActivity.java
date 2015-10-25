package com.ggg.snake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	//for custom view
	SnakeAnimView snakeAnimView;

	//The snake title image
	Bitmap titleBitmap;
	int titleBitmapWidth;
	int titleBitmapHeight;
	Rect titleRectToBeDrawn;

	//title image position
	Point titlePosition;

	//The dimensions of title
	int frameWidth;
	int frameHeight;

	//screen info
	int screenWidth;
	int screenHeight;

	//stats
	long lastFrameTime;
	long threadInterval = 16;
	int fps;

	//To start the game from onTouchEvent
	Intent intent;

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
		setContentView(R.layout.activity_main);

		//title image
		this.titleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.snake_title);
		this.titleBitmapWidth = this.titleBitmap.getWidth();
		this.titleBitmapHeight = this.titleBitmap.getHeight();
		this.titlePosition = new Point();
		this.titleRectToBeDrawn = new Rect(0, 0, titleBitmapWidth, titleBitmapHeight);

		//create menu view and set as layout content
		this.snakeAnimView = new SnakeAnimView(this);
		this.snakeAnimView.setLayoutParams(
			new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT)
		);
		ViewGroup menuLayout = (ViewGroup)findViewById(R.id.menuLayout);
		menuLayout.addView(this.snakeAnimView);

		//game activity intent
		intent = new Intent(this, GameActivity.class);
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
		//start game activity on any touch event
		if (intent != null) {
			startActivity(intent);
		}
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
		this.snakeAnimView.pause();
	}

	/**
	 * onResume
	 */
	@Override
	protected void onResume() {
		super.onResume();
		this.snakeAnimView.resume();
	}

	/**
	 * onStop
	 */
	@Override
	protected void onStop() {
		super.onStop();
		while (true) {
			this.snakeAnimView.pause();
			break;
		}
		//stop this activity
		finish();
	}

	/**
	 * update display info
	 */
	private void _updateDisplayInfo() {
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.menuLayout);
		this.screenWidth = layout.getWidth();
		this.screenHeight = layout.getHeight();

		Log.i("_updateDisplayInfo", "menuLayout Width: " + this.screenWidth);
		Log.i("_updateDisplayInfo", "menuLayout Height: " + this.screenHeight);
	}

	/**
	 * game view inner class
	 */
	class SnakeAnimView extends SurfaceView implements Runnable {

		Thread ourThread = null;

		SurfaceHolder ourHolder;
		Canvas canvas;

		volatile boolean playingMenu;

		/**
		 * inner class constructor
		 * @param context
		 */
		public SnakeAnimView(Context context) {
			super(context);
			Log.i("SnakeAnimView: ", "constructor");

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
				if (this.playingMenu && screenWidth > 0) {
					Log.i("Menu Thread", "in Progress");

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
		}

		/**
		 * update
		 */
		private void update() {
			//update display info and related properties
			_updateDisplayInfo();

			//get image dimension
			frameWidth = screenWidth * 8 / 10;
			frameHeight = frameWidth / 3;

			//check and update title position
			//if never set
			if (titlePosition.x == 0 && titlePosition.y == 0) {
				//reset to offscreen top
				titlePosition.x = (screenWidth - frameWidth) / 2;
				titlePosition.y = 0 - frameHeight - 100;
			}
			//to move it from top to bottom and repeat
			else {
				titlePosition.y += 5;

				//check y boundary and reset if offscreen
				if (titlePosition.y > screenHeight) {
					titlePosition.y = 0 - frameHeight - 100;
				}
			}

			Log.i("titlePosition.x", "" + titlePosition.x);
			Log.i("titlePosition.y", "" + titlePosition.y);
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
				paint.setTextSize(36);

				//clear last frame by setting the background and
				this.canvas.drawColor(Color.BLACK);

				//title rect
				//(startX, startY, endX, endY)
				Rect destRect = new Rect(
						titlePosition.x,
						titlePosition.y,
						titlePosition.x + frameWidth,
						titlePosition.y + frameHeight);

				//draw title image
				this.canvas.drawBitmap(
						titleBitmap,
						titleRectToBeDrawn,
						destRect,
						paint);

				canvas.drawText(
						"Tap to Start",
						canvas.getWidth() / 2,
						titlePosition.y + frameHeight + 60,
						paint);

				//completed drawing and update to canvas
				this.ourHolder.unlockCanvasAndPost(this.canvas);

				Log.i("titleRectToBeDrawn", "" + titleRectToBeDrawn);
				Log.i("destRect", "" + destRect);
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
			this.playingMenu = false;
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
			this.playingMenu = true;
			//start game thread
			if(this.ourThread == null) {
				this.ourThread = new Thread(this);
				this.ourThread.start();
			}
		}
	}
}
