package com.ggg.mathgamechapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //vars
    int correctAnswer;
    boolean answerPicked = false;
    Button buttonAnswers[] = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Here we initialize all our variables
        int partA = 9;
        int partB = 9;
        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - 1;
        int wrongAnswer2 = correctAnswer + 1;

        /*
        Here we get a working object based on either the button
        or TextView class and base as well as link our new objects
        directly to the appropriate UI elements that we created
        previously
        */
        TextView textObjectPartA = (TextView)findViewById(R.id.textPartA);
        TextView textObjectPartB = (TextView)findViewById(R.id.textPartB);
        buttonAnswers[0] = (Button)findViewById(R.id.buttonChoice1);
        buttonAnswers[1] = (Button)findViewById(R.id.buttonChoice2);
        buttonAnswers[2] = (Button)findViewById(R.id.buttonChoice3);

        //Now we use the setText method of the class on our objects
        //to show our variable values on the UI elements.
        //Just like when we output to the console in the exercise -
        //Expressions in Java, only now we use setText method
        //to put the values in our variables onto the actual UI.
        textObjectPartA.setText("" + partA);
        textObjectPartB.setText("" + partB);
        //which button receives which answer, at this stage is arbitrary.
        buttonAnswers[0].setText("" + correctAnswer);
        buttonAnswers[1].setText("" + wrongAnswer1);
        buttonAnswers[2].setText("" + wrongAnswer2);

        //register button events
        buttonAnswers[0].setOnClickListener(this);
        buttonAnswers[1].setOnClickListener(this);
        buttonAnswers[2].setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //skip if already answered
        if (answerPicked) {
            return;
        }

        //set answered flag
        answerPicked = true;

        //get answer from click event target
        Button buttonClicked = (Button)view;
        int answerGiven = Integer.parseInt("" +  buttonClicked.getText());

        //prep toast message
        Toast toastMsg = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        TextView toastMsgTextView = (TextView)toastMsg.getView()
                .findViewById(android.R.id.message);
        String toastMsgText;

        //check answer
        if (answerGiven == correctAnswer) {
            toastMsgText = "Well done!";
        } else {
            toastMsgText = "Sorry,\n" + "that's wrong!";
        }

        //display toast message
        if( toastMsgTextView != null) {
            toastMsgTextView.setGravity(Gravity.CENTER);
            toastMsgTextView.setText(toastMsgText);
            toastMsg.show();
        }

        //update buttons
        for (int i = 0; i < buttonAnswers.length; i++) {
            //update clicked button theme
            if (buttonAnswers[i] == buttonClicked) {
                if (answerGiven == correctAnswer) {
                    buttonClicked.setBackgroundColor(0xFF27AE60);
                } else {
                    buttonClicked.setBackgroundColor(0xFFFF0000);
                }
                buttonClicked.setTextColor(0xFFFFFFFF);
            }

            //prevent further click
            buttonAnswers[i].setEnabled(false);
        }
    }
}
