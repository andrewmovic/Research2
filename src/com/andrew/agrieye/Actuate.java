package com.andrew.agrieye;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.andrew.agrieye.Picnic;
import com.andrew.agrieye.picnic.PicnicConfig;

public class Actuate extends Activity {
	FrameLayout frameLayoutLogo;
	Button btOff;
	ToggleButton tbLed4,tbLed5,tbLed6,tbLed7;
	private Toast toast;
	private PicnicConfig picConfig;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actuate);
		picConfig = new PicnicConfig();
		
		initObject(); // object initialization method
				// set check or unchecked for each toggle button
		// led 4
		if (Character.getNumericValue(statCh[4]) == 1) {
			tbLed4.setChecked(true);
		}
		// led 5
		if (Character.getNumericValue(statCh[5]) == 1) {
			tbLed5.setChecked(true);
		}
		// led 6
		if (Character.getNumericValue(statCh[6]) == 1) {
			tbLed6.setChecked(true);
		}
		// led 7
		if (Character.getNumericValue(statCh[7]) == 1) {
			tbLed7.setChecked(true);
		}			
		
		// Toggle button LED 4
        tbLed4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tbLed4.isChecked()) {
					pic.setHigh("RB", 4);
					toastOn(4);
		        } else {
		        	pic.setLow("RB", 4);
		        	toastOff(4);
		        }
				
			}
		});
     // Toggle button LED 5
        tbLed5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tbLed5.isChecked()) {
					pic.setHigh("RB", 5);
					toastOn(5);
		        } else {
		        	pic.setLow("RB", 5);
		        	toastOff(5);
		        }
				
			}
		});
        
     // Toggle button LED 6
        tbLed6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tbLed6.isChecked()) {
					pic.setHigh("RB", 6);
					toastOn(6);
		        } else {
		        	pic.setLow("RB", 6);
		        	toastOff(6);
		        }
				
			}


		});
     // Toggle button LED 7
        tbLed7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tbLed7.isChecked()) {
					pic.setHigh("RB", 7);
					toastOn(7);
		        } else {
		        	pic.setLow("RB", 7);
		        	toastOff(7);
		        }
				
			}
		});
        
     // back home
     		frameLayoutLogo.setOnClickListener(new OnClickListener() {
     			
     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     				
     			}
     		});
     		
     	// button off
     	btOff.setOnClickListener(new OnClickListener() {
     			
     		@Override
     		public void onClick(View v) {
     			// TODO Auto-generated method stub
     			finish();
     		}
     	});       
        
	}

	private void initObject() {
		// TODO Auto-generated method stub
		// button inisiation
		btOff = (Button) findViewById(R.id.btOff);
		tbLed4 = (ToggleButton) findViewById(R.id.tbLed4);
		tbLed5 = (ToggleButton) findViewById(R.id.tbLed5);
		tbLed6 = (ToggleButton) findViewById(R.id.tbLed6);
		tbLed7 = (ToggleButton) findViewById(R.id.tbLed7);
		frameLayoutLogo = (FrameLayout) findViewById(R.id.frameLayoutLogo);
		
	
	}
	private void toastOn(int number){		
		Context context = getApplicationContext();
		String messages = "Channel "+ Integer.toString(number) + " is Set High";
		toast =Toast.makeText(context, messages, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	private void toastOff(int number) {
		Context context = getApplicationContext();
		String messages = "Channel "+ Integer.toString(number) + " is Set Low";
		toast =Toast.makeText(context, messages, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	
}
