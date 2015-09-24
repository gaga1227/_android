package com.ggg.mathgamechapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Here we initialize all our variables
        int partA = 9;
        int partB = 9;
        int correctAnswer = partA * partB;
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
        Button buttonObjectChoice1 = (Button)findViewById(R.id.buttonChoice1);
        Button buttonObjectChoice2 = (Button)findViewById(R.id.buttonChoice2);
        Button buttonObjectChoice3 = (Button)findViewById(R.id.buttonChoice3);

        //Now we use the setText method of the class on our objects
        //to show our variable values on the UI elements.
        //Just like when we output to the console in the exercise -
        //Expressions in Java, only now we use setText method
        //to put the values in our variables onto the actual UI.
        textObjectPartA.setText("" + partA);
        textObjectPartB.setText("" + partB);
        //which button receives which answer, at this stage is arbitrary.
        buttonObjectChoice1.setText("" + correctAnswer);
        buttonObjectChoice2.setText("" + wrongAnswer1);
        buttonObjectChoice3.setText("" + wrongAnswer2);
    }
}
