package com.ggg.hour5application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */

    long delay = 5000;

    ContentLoadingProgressBar loader;
    TextView resultsTextView;
    Button uiThreadButton;
    Button workerThreadButton;
    Button asyncTaskButton;
    Button intentServiceButton;

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loader
        loader = (ContentLoadingProgressBar) findViewById(R.id.loader);

        // text view
        resultsTextView = (TextView) findViewById(R.id.resultsTextView);

        // init UI thread button
        uiThreadButton = (Button) findViewById(R.id.uiThreadButton);
        uiThreadButton.setOnClickListener(uiButtonListener);

        // init worker thread button
        workerThreadButton = (Button) findViewById(R.id.workerThreadButton);
        workerThreadButton.setOnClickListener(workerButtonListener);

        // init async thread button
        asyncTaskButton = (Button) findViewById(R.id.asyncTaskButton);
        asyncTaskButton.setOnClickListener(asyncButtonListener);

        // init intent service button
        intentServiceButton = (Button) findViewById(R.id.intentServiceButton);
        intentServiceButton.setOnClickListener(intentServiceButtonListener);
    }


    /**
     * updateTextView
     * @param input
     */
    private void updateTextView(String input) {
        if (resultsTextView != null && input != null && input.length() > 0) {
            resultsTextView.setText(input);
            Log.i("INFO", input);
        } else {
            Log.i("INFO", "Invalid view or input!");
        }
    }


    /**
     * UI Thread delay
     */
    View.OnClickListener uiButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateTextView("Start delay in main thread...");
            SystemClock.sleep(delay);
            updateTextView("Delayed and Updated on UI Thread (Blocking)");
        }
    };


    /**
     * Worker Thread delay
     */
    View.OnClickListener workerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loader.show();
            updateTextView("Start delay in worker thread...");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(delay);
                    resultsTextView.post(new Runnable() {
                        @Override
                        public void run() {
                            updateTextView("Delayed on Worker Thread and Updated on UI Thread (Non-Blocking)");
                            loader.hide();
                        }
                    });
                }
            }).start();
        }
    };


    /**
     * Async Thread delay
     */

    View.OnClickListener asyncButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DelayTask().execute();
        }
    };

    // inner class for async task
    class DelayTask extends AsyncTask<Integer, Integer, Integer> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         *
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            // delay in async background thread
            SystemClock.sleep(5000);
            // return result
            return 0;
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.show();
            updateTextView("Start delay in async task...");
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         *
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param result The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            // update text after receiving correct delay result
            if (result == 0) {
                loader.hide();
                updateTextView("Delayed on Async Thread (Non-Blocking)");
            }
        }
    }


    /**
     * Intent Service delay
     */

    View.OnClickListener intentServiceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loader.show();
            updateTextView("Start delay from intent service...");

            // create and start delay service intent
            Intent delayIntent = new Intent(getApplicationContext(), DelayService.class);
            startService(delayIntent);
        }
    };

    // inner class for broadcast intent receiver
    class DelayReceiver extends BroadcastReceiver {

        /**
         * This method is called when the BroadcastReceiver is receiving an Intent
         * broadcast.  During this time you can use the other methods on
         * BroadcastReceiver to view/modify the current result values.  This method
         * is always called within the main thread of its process, unless you
         * explicitly asked for it to be scheduled on a different thread using
         * {@link Context#registerReceiver(BroadcastReceiver,
         * IntentFilter, String, Handler)}. When it runs on the main
         * thread you should
         * never perform long-running operations in it (there is a timeout of
         * 10 seconds that the system allows before considering the receiver to
         * be blocked and a candidate to be killed). You cannot launch a popup dialog
         * in your implementation of onReceive().
         *
         * <p><b>If this BroadcastReceiver was launched through a &lt;receiver&gt; tag,
         * then the object is no longer alive after returning from this
         * function.</b>  This means you should not perform any operations that
         * return a result to you asynchronously -- in particular, for interacting
         * with services, you should use
         * {@link Context#startService(Intent)} instead of
         * {@link Context#bindService(Intent, ServiceConnection, int)}.  If you wish
         * to interact with a service that is already running, you can use
         * {@link #peekService}.
         *
         * <p>The Intent filters used in {@link Context#registerReceiver}
         * and in application manifests are <em>not</em> guaranteed to be exclusive. They
         * are hints to the operating system about how to find suitable recipients. It is
         * possible for senders to force delivery to specific recipients, bypassing filter
         * resolution.  For this reason, {@link #onReceive(Context, Intent) onReceive()}
         * implementations should respond only to known actions, ignoring any unexpected
         * Intents that they may receive.
         *
         * @param context The Context in which the receiver is running.
         * @param intent  The Intent being received.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // get received intent action and bundle
            String intentAction = intent.getAction();
            Bundle intentExtras = intent.getExtras();

            // update text view when received qualified broadcast intent
            if (intentAction == DelayService.ACTION_DELAY) {
                if (intentExtras.getString(DelayService.EXTRA_MESSAGE) != null) {
                    loader.hide();
                    updateTextView("Delayed on intent service (Non-Blocking)");
                }
            }
        }
    }

    // create broadcast receiver
    DelayReceiver delayReceiver = new DelayReceiver();

    // start/stop broadcast receiver with activity lifecycle
    @Override
    protected void onResume() {
        super.onResume();

        // start receiver and listening with intent filter
        registerReceiver(delayReceiver, new IntentFilter(DelayService.ACTION_DELAY));
    }
    @Override
    protected void onPause() {
        super.onPause();

        // stop receiver and listening
        unregisterReceiver(delayReceiver);
    }
}
