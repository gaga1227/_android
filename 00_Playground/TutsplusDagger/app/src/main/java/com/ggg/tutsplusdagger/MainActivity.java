package com.ggg.tutsplusdagger;

import com.ggg.tutsplusdagger.component.DaggerVehicleComponent;
import com.ggg.tutsplusdagger.component.VehicleComponent;
import com.ggg.tutsplusdagger.model.Motor;
import com.ggg.tutsplusdagger.model.Vehicle;
import com.ggg.tutsplusdagger.module.VehicleModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    // members
    String debugTextString = "";
    TextView debugText;

    // auto construct and inject instance to field
    @Inject Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // build dagger component
        VehicleComponent component = DaggerVehicleComponent.builder()
                .vehicleModule(new VehicleModule())
                .build();

        // use dagger component to provide instances
        Motor motor = component.provideMotor();
        Motor motor2 = component.provideMotor();
        Vehicle vehicle2 = component.provideVehicle();

        component.inject(this);

        // debug info
        debugTextString += "motor.getRpm() -> ";
        debugTextString += String.valueOf(motor.getRpm());
        debugTextString += "\n";

        debugTextString += "motor == motor2 -> ";
        debugTextString += String.valueOf(motor == motor2);
        debugTextString += "\n";

        debugTextString += "vehicle.getSpeed() -> ";
        debugTextString += String.valueOf(vehicle.getSpeed());
        debugTextString += "\n";

        debugTextString += "vehicle == vehicle2 -> ";
        debugTextString += String.valueOf(vehicle == vehicle2);
        debugTextString += "\n";

        debugText = (TextView)findViewById(R.id.debugText);
        debugText.setText(debugTextString);
    }
}