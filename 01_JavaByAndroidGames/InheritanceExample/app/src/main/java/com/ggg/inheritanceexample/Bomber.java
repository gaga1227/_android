package com.ggg.inheritanceexample;

import android.util.Log;

public class Bomber extends AlienShip {

	/**
	 * Constructor
	 */
	public Bomber() {
		super(100);

		//Weak shields for a bomber
		Log.i("Location: ", "Bomber constructor");
	}

	/**
	 * Abstract method - fire
	 */
	@Override
	public void fireWeapon() {
		Log.i("Firing weapon: ", "bombs away");
	}
}
