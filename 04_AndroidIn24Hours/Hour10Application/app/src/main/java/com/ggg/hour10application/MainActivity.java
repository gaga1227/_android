package com.ggg.hour10application;

import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * vars
     */
    String[] items;


    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get data
        items = getResources().getStringArray(R.array.items);

        // init demos
        initSpinner();
        initAutoCompleteTextView();
        initRadioGroup();
        initProgressBar();
    }


    /**
     * Make a toast with the input content
     * @param input
     */
    public void makeToast(String input) {
        if (input != null && input.length() > 0) {
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Spinner
     */
    public void initSpinner() {
        // find view
        Spinner v = (Spinner) findViewById(R.id.spinnerView);

        // set data adaptor to view
        v.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                items));

        // handle selection
        v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                makeToast("Spinner:\n" + items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * Auto Complete Text View
     */
    public void initAutoCompleteTextView() {
        // find view
        AutoCompleteTextView v = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // set data adaptor to view
        v.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                items));

        // handle inputs
        v.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                makeToast("AutoCompleteTextView:\n" + s.toString());
            }
        });
    }


    /**
     * Checkbox
     */
    public void onCheckboxSelected(View view) {
        if (view != null) {
            CheckBox v = (CheckBox) view;
            Boolean checked = v.isChecked();
            v.setText(checked ? "Checked" : "Unchecked");
        }
    }


    /**
     * RadioGroup
     */
    public void initRadioGroup() {
        // find view
        RadioGroup v = (RadioGroup) findViewById(R.id.radioGroup);

        // handle selection
        v.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                makeToast("Radio Button Selected:\n" + radioButton.getText());
            }
        });
    }


    /**
     * ProgressBar
     */

    public void initProgressBar() {
        //find views
        final Button btn = (Button) findViewById(R.id.taskButton);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.horizontalProgressBar);
        final ProgressBar progressSpinner = (ProgressBar) findViewById(R.id.progressBar);

        // progress steps
        final int steps = 5;
        final int maxProgress = 1000;

        // AsyncTask inner class to simulate background progress
        class Task extends AsyncTask<Integer, Integer, Integer> {
            /**
             * Runs on the UI thread before {@link #doInBackground}.
             */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // update button view
                btn.setText("In Progress...");
                btn.setEnabled(false);

                // make sure progress bars are shown and reset
                progressBar.setProgress(0);
                progressBar.setMax(maxProgress);
                progressBar.setVisibility(View.VISIBLE);
                progressSpinner.setVisibility(View.VISIBLE);
            }

            /**
             * <p>Runs on the UI thread after {@link #doInBackground}. The
             * specified result is the value returned by {@link #doInBackground}.</p>
             */
            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);

                // reset button view
                btn.setText("Start Progress");
                btn.setEnabled(true);

                // hide progress spinner
                progressSpinner.setVisibility(View.INVISIBLE);
            }

            /**
             * Runs on the UI thread after {@link #publishProgress} is invoked.
             * The specified values are the values passed to {@link #publishProgress}.
             */
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);

                // update progress bar with animation
                ObjectAnimator animation = ObjectAnimator.ofInt(
                        progressBar,
                        "progress",
                        values[0]);
                animation.setDuration(500);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.start();
            }

            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             *
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             */
            @Override
            protected Integer doInBackground(Integer... params) {
                // delay in steps
                for (int i = 1; i <= steps; i++) {
                    SystemClock.sleep(1000);
                    // publish progress on each step
                    publishProgress(i * (maxProgress / steps));
                }

                // return result (any integer)
                return 0;
            }
        }

        // button handler
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start async task
                Task task = new Task();
                task.execute();
            }
        });
    }
}

