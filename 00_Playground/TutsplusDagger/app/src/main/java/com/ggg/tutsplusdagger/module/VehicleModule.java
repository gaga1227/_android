package com.ggg.tutsplusdagger.module;

import com.ggg.tutsplusdagger.model.Motor;
import com.ggg.tutsplusdagger.model.Vehicle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VehicleModule {

    @Provides @Singleton
    Motor provideMotor() {
        return new Motor();
    }
}
