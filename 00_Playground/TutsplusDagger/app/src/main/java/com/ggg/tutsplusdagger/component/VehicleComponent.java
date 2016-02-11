package com.ggg.tutsplusdagger.component;

import com.ggg.tutsplusdagger.MainActivity;
import com.ggg.tutsplusdagger.model.Motor;
import com.ggg.tutsplusdagger.model.Vehicle;
import com.ggg.tutsplusdagger.module.VehicleModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {

    // method to be implemented by Dagger in 'DaggerVehicleComponent.class'
    // tells Dagger to inject all fields with @Inject within 'activity' when called
    void inject(MainActivity activity);

    Motor provideMotor();
    Vehicle provideVehicle();
}
