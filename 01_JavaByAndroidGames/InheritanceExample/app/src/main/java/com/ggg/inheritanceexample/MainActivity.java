package com.ggg.inheritanceexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//init objects
		Fighter aFighter = new Fighter();
		Bomber aBomber = new Bomber();

		//AlienShip is abstract
		//Cannot create object from it, only from its subclasses
		//AlienShip alienShip = new AlienShip(500);

		//But our objects of the subclasses can still do
		//everything the AlienShip is meant to do
		aFighter.shipName = "Meier Fighter";
		aBomber.shipName = "Newell Bomber";

		//And because of the overridden constructor
		//That still calls the super constructor
		//They have unique properties
		Log.i("aFighter Shield:", "" + aFighter.getShieldStrength());
		Log.i("aBomber Shield:", ""+ aBomber.getShieldStrength());

		//As well as certain things in certain ways
		//That are unique to the subclass
		aFighter.fireWeapon();
		aBomber.fireWeapon();

		//Take down those alien ships
		//Focus on the bomber it has a weaker shield
		aBomber.hitDetected();
		aBomber.hitDetected();
		aBomber.hitDetected();
		aBomber.hitDetected();
		aBomber.hitDetected();
	}

}
