package com.ggg.inheritanceexample;

import android.util.Log;

public abstract class AlienShip {

	// vars
	private static int numShips;
	private int shieldStrength;
	public String shipName;
	private boolean isDestroyed;

	/**
	 * Constructor
	 */
	public AlienShip(int shieldStrength) {
		Log.i("Location: ", "AlienShip constructor");
		numShips++;
		this.setShieldStrength(shieldStrength);
		this.isDestroyed = false;
	}

	/**
	 * Abstract method - fire
	 */
	public abstract void fireWeapon();//Ahh my body

	/**
	 * Set current ship shield strength
	 * @param shieldStrength - new shield strength
	 */
	private void setShieldStrength(int shieldStrength) {
		this.shieldStrength = shieldStrength;
	}

	/**
	 * Get current ship shield strength
	 * @return current ship shield strength
	 */
	public int getShieldStrength() {
		return this.shieldStrength;
	}

	/**
	 * When ship is hit
	 */
	public void hitDetected(){
		this.shieldStrength -= 25;
		Log.i("Incoming: ", "Bam!!");

		if (this.shieldStrength <= 0 && !this.isDestroyed){
			this.destroyShip();
		}
	}

	/**
	 * When ship is destroyed
	 */
	private void destroyShip() {
		this.isDestroyed = true;
		numShips--;
		Log.i("Explosion: ", "" + this.shipName + " destroyed");
	}
}
