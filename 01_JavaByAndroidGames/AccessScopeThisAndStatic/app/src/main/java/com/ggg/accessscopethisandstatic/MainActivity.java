package com.ggg.accessscopethisandstatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//every time we do this the constructor runs
		AlienShip girlShip = new AlienShip();
		AlienShip boyShip = new AlienShip();

		//Look no objects but using the static method
		Log.i("numShips: ", "" + AlienShip.getNumShips());

		//This works because shipName is public
		girlShip.shipName = "Corrine Yu";
		boyShip.shipName = "Andre LaMothe";

		//girlship.shieldStrength = 999;
		//This won't work because shieldStrength is private

		//And we can't do this because it's private
		//boyship.setShieldStrength(1000000);

		//But we have a public getter
		Log.i("girlShip strength: ", "" + girlShip.getShieldStrength());
		Log.i("boyShip strength: ", "" + boyShip.getShieldStrength());

		//let's shoot some ships
		girlShip.hitDetected();
		Log.i("girlShip strength: ", "" + girlShip.getShieldStrength());
		Log.i("boyShip strength: ", "" + boyShip.getShieldStrength());

		boyShip.hitDetected();
		boyShip.hitDetected();
		boyShip.hitDetected();

		girlShip.hitDetected();
		Log.i("girlShip strength: ", "" + girlShip.getShieldStrength());
		Log.i("boyShip strength: ", "" + boyShip.getShieldStrength());

		boyShip.hitDetected();//ahhh

		Log.i("girlShip strength: ", "" + girlShip.getShieldStrength());
		Log.i("boyShip strength: ", "" + boyShip.getShieldStrength());

		Log.i("numShips: ", "" + AlienShip.getNumShips());

		if (boyShip.getShieldStrength() <= 0) {
			boyShip = null;
		}
	}
}
