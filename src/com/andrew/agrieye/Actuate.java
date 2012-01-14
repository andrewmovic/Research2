package com.andrew.agrieye;

/* Actuation Activity v 1.0
 * January 14, 2012 
 * andrew@ugm.ac.id */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.andrew.agrieye.picnic.PicnicConfig;


public class Actuate extends Activity {
	private Toast toast;
	private PicnicConfig picConfig;
	private SharedPreferences getIpAddress;
  	private static String etIpSetting;
  	FrameLayout frameLayoutLogo;
	Button btOff;
	ToggleButton tbLed4,tbLed5,tbLed6,tbLed7;
  	private TextView tvMonitorStatus;
  	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actuate);
		
		// get current ip address from shared preferences
        getIpAddress = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		etIpSetting = getIpAddress.getString("ip", "192.168.11.224");
	
		// pic connect
		picConfig = new PicnicConfig(etIpSetting);			
		initObject(); // object initialization method
		
		// set check or unchecked for each toggle button
		// toggle button 4
		if(picConfig.checkStatus(4) == "1") {
			tbLed4.setChecked(true);
		}
		// set toggle button 5
		if(picConfig.checkStatus(5) == "1") {
			tbLed5.setChecked(true);
		}
		// set toggle button 6
		if(picConfig.checkStatus(6) == "1") {
			tbLed6.setChecked(true);
		}
		// set toggle button 7
		String hasil7 = picConfig.checkStatus(7);
		if( hasil7 == "1") {
			boolean mBool = true;
			tbLed7.setChecked(mBool);
		}	
		// check set text for check status
		String empat, lima, enam, tujuh;
		empat = picConfig.checkStatus(4);
		lima = picConfig.checkStatus(5);
		enam = picConfig.checkStatus(6);
		tujuh = picConfig.checkStatus(7);
		tvMonitorStatus.setText(empat+lima+enam+tujuh);
		
		
		// action for each toogle button
		
		// Toggle button LED 4
        tbLed4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tbLed4.isChecked()) {
					picConfig.turnOn(4);
					toastOn(4);
		        } else {
		        	picConfig.turnOff(4);
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
					picConfig.turnOn(5);
					toastOn(5);
		        } else {
		        	picConfig.turnOff(5);
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
					picConfig.turnOn(6);
					toastOn(6);
		        } else {
		        	picConfig.turnOff(6);
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
					picConfig.turnOn(7);
					toastOn(7);
		        } else {
		        	picConfig.turnOff(7);
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
 	// button back
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
		tvMonitorStatus = (TextView) findViewById(R.id.tvMonitorStatus);
	
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