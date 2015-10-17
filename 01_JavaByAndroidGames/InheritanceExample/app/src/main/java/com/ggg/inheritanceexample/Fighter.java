package com.ggg.inheritanceexample;

import android.util.Log;

public class Fighter extends AlienShip {

	/**
	 * Constructor
	 */
	public Fighter() {
		super(400);

		//Strong shields for a fighter
		Log.i("Location: ", "Fighter constructor");
	}

	/**
	 * Abstract method - fire
	 */
	@Override
	public void fireWeapon() {
		Log.i("Firing weapon: ", "lasers firing");
	}
}
