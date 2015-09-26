package com.ggg.mathgamechapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

	//text views
	TextView textObjectPartA;
	TextView textObjectPartB;
	TextView textObjectScore;
	TextView textObjectLevel;

	//buttons
	Button buttonAnswers[] = new Button[3];

	//game mechanic
	int currentScore = 0;
	int currentLevel = 1;
	int correctAnswer;
	boolean answerPicked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		//assign UI elements to vars
		textObjectPartA = (TextView)findViewById(R.id.textPartA);
		textObjectPartB = (TextView)findViewById(R.id.textPartB);
		textObjectScore = (TextView)findViewById(R.id.textScore);
		textObjectLevel = (TextView)findViewById(R.id.textLevel);

		buttonAnswers[0] = (Button)findViewById(R.id.buttonChoice1);
		buttonAnswers[1] = (Button)findViewById(R.id.buttonChoice2);
		buttonAnswers[2] = (Button)findViewById(R.id.buttonChoice3);

		//register button events
		buttonAnswers[0].setOnClickListener(this);
		buttonAnswers[1].setOnClickListener(this);
		buttonAnswers[2].setOnClickListener(this);

		//start game
		updateScoreAndLevel(-9999);
		setQuestion();
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
		TextView toastMsgTextView =
				(TextView)toastMsg.getView().findViewById(android.R.id.message);
		String toastMsgText;

		//check answer
		if (isCorrect(answerGiven)) {
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

//		//update buttons
//		for (int i = 0; i < buttonAnswers.length; i++) {
//			//update clicked button theme
//			if (buttonAnswers[i] == buttonClicked) {
//				if (answerGiven == correctAnswer) {
//					buttonClicked.setBackgroundColor(0xFF27AE60);
//				} else {
//					buttonClicked.setBackgroundColor(0xFFFF0000);
//				}
//				buttonClicked.setTextColor(0xFFFFFFFF);
//			}
//			//prevent further click
//			buttonAnswers[i].setEnabled(false);
//		}

		//update stats and set new question
		updateScoreAndLevel(answerGiven);
		setQuestion();
	}

	//prep and populate a new question
	void setQuestion() {
		//generate the parts of the question
		int numberRange = Math.min(100, currentLevel * 3);
		Random randInt = new Random();
		int partA = randInt.nextInt(numberRange);
		int partB = randInt.nextInt(numberRange);

		//don't want a zero value
		partA++;
		partB++;

		//assign answer values
		correctAnswer = partA * partB;
		int wrongAnswer1 = correctAnswer - 2;
		int wrongAnswer2 = correctAnswer + 2;

		//update text views
		textObjectPartA.setText("" + partA);
		textObjectPartB.setText("" + partB);

		//update button views
		int buttonLayout = randInt.nextInt(buttonAnswers.length);
		switch (buttonLayout) {
			case 0:
				buttonAnswers[0].setText("" + correctAnswer);
				buttonAnswers[1].setText("" + wrongAnswer1);
				buttonAnswers[2].setText("" + wrongAnswer2);
				break;
			case 1:
				buttonAnswers[1].setText("" + correctAnswer);
				buttonAnswers[2].setText("" + wrongAnswer1);
				buttonAnswers[0].setText("" + wrongAnswer2);
				break;
			case 2:
				buttonAnswers[2].setText("" + correctAnswer);
				buttonAnswers[0].setText("" + wrongAnswer1);
				buttonAnswers[1].setText("" + wrongAnswer2);
				break;
		}

		//so the click handlers are ready
		answerPicked = false;
	}

	//update score and level depends on the answer
	void updateScoreAndLevel(int answerGiven) {
		if(isCorrect(answerGiven)){
			currentScore += currentLevel;
			currentLevel++;
		} else {
			currentScore = 0;
			currentLevel = 1;
		}

		//Actually update the two TextViews
		textObjectScore.setText("Score: " + currentScore);
		textObjectLevel.setText("Level: " + currentLevel);
	}

	//check if answer is correct
	boolean isCorrect(int answerGiven) {
		return (answerGiven == correctAnswer);
	}
}
