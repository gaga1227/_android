package com.ggg.accessscopethisandstatic;

import android.util.Log;

/**
 * Created by gaga on 15/01/2015.
 */
public class AlienShip {

	/**
	 * vars
	 */
	private static int numShips;
	private int shieldStrength;
	public String shipName;

	/**
	 * constructor
	 */
	public AlienShip() {
		//increment ship count
		numShips++;

		//Can call private methods from here because I am part
		//of the class
		//If didn't have "this" then this call might be less clear
		//But this "this" isn't strictly necessary
		this.setShieldStrength(100);
		//Because of "this" I am sure I am setting
		//the correct shieldStrength
	}

	/**
	 * getNumShips
	 * @return total ships count
	 */
	public static int getNumShips() {
		return numShips;
	}

	/**
	 * setShieldStrength
	 */
	private void setShieldStrength(int strength) {
		//"this" distinguishes between the
		//member variable shieldStrength
		//And the local variable/parameter of the same name
		this.shieldStrength = strength;
	}

	/**
	 * getShieldStrength
	 * @return current ship shield strength
	 */
	public int getShieldStrength() {
		return this.shieldStrength;
	}

	/**
	 * hitDetected
	 */
	public void hitDetected(){
		Log.i("Incoming:", " Bam!!");

		shieldStrength -= 25;
		if (shieldStrength <= 0) {
			this.destroyShip();
		}
	}

	/**
	 * destroyShip
	 */
	private void destroyShip() {
		numShips--;
		Log.i("Explosion: ", ""+this.shipName + " destroyed");
	}
}
