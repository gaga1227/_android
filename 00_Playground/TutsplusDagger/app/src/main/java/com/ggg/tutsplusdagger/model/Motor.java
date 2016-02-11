package com.ggg.tutsplusdagger.model;

import javax.inject.Inject;

public class Motor {

    /**
     * members
     */
    private int rpm;


    /**
     * constructor
     */

    public Motor(){
        this.rpm = 1;
    }


    /**
     * methods
     */

    public int getRpm(){
        return rpm;
    }

    public void accelerate(int value){
        rpm = rpm + value;
    }

    public void brake(){
        rpm = 0;
    }
}
