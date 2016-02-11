package com.ggg.tutsplusdagger.model;

import javax.inject.Inject;

public class Vehicle {

    /**
     * members
     */
    private Motor motor;


    /**
     * constructor
     */

    // Tells Dagger to use this constructor to inject to fields
    @Inject
    public Vehicle(Motor motor){
        this.motor = motor;
    }


    /**
     * methods
     */

    public void increaseSpeed(int value){
        motor.accelerate(value);
    }

    public void stop(){
        motor.brake();
    }

    public int getSpeed(){
        return motor.getRpm() * 2;
    }
}
