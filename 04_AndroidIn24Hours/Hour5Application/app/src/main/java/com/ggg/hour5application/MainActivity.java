package com.ggg.hour5application;

import android.os.AsyncTask;
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
    }


    /**
     * updateTextView
     * @param input
     */
    private void updateTextView(String input) {
        if (resultsTextView != null && input != null && input.length() > 0) {
            resultsTextView.setText(input);
            Log.i("INFO", "Text view updated after delay!");
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
            updateTextView("Start async task for delay...");
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
}
