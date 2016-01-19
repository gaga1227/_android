package com.ggg.hour5application;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class DelayService extends IntentService {

    /**
     * vars
     */

    // using messages with app package names to make them unique
    public static final String ACTION_DELAY = "com.ggg.hour5application.action.DELAY";
    public static final String EXTRA_MESSAGE = "com.ggg.hour5application.extra.MESSAGE";

    long delay = 5000;


    /**
     * Constructor
     */
    public DelayService() {
        // starts background service once created in activity
        super("DelayIntentService");
    }


    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // delay in background service, and...
        SystemClock.sleep(delay);

        // setup broadcast intent and broadcast
        // so the main thread can update view upon receiving broadcast
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_DELAY);
        broadcastIntent.putExtra(EXTRA_MESSAGE, "UPDATE");
        sendBroadcast(broadcastIntent);
    }
}
