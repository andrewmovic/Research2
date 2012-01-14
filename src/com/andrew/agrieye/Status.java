package com.andrew.agrieye;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.andrew.agrieye.Picnic;

public class Status extends Activity {
	TextView tvIpAddress, tvIpAddressSmall,tvTemp, tvMonitor,tvMonitorDefault,tvRb4,tvRb5,tvRb6,tvRb7;
	Button btL4,btL5, btL6,btL7, btOff;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
			
		initObject();   // object initialization method		
		
		// initialization of picnic
	
		// from shared preferences
        String ipAddress;
        //int udpPortP, udpPortS;

        // ip address ( default 192.168.11.224 -> ito lab )
        SharedPreferences getIpAddress;
        getIpAddress = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    	String etIpSetting = getIpAddress.getString("ip", "192.168.11.224");
 
        ipAddress = etIpSetting;
        tvIpAddress.setText(ipAddress);
        
        int udpPortP, udpPortS;
        udpPortP = 10001; // static
        udpPortS = 10002; // static

        //final Picnic pic = new Picnic("192.168.64.44",10001,10002);
        final Picnic pic;
        pic = new Picnic(ipAddress,udpPortP,udpPortS);
        
		// set Temperature
		int temp;
		try {
			temp = pic.getTemp();
			tvTemp.setText(Integer.toString(temp)+" Celcius");
		} catch (UnknownError e) {
			e.printStackTrace();
			tvTemp.setText("Error");
		} 
		// set the check status
		try {
			byte rb;
			rb = pic.getRB();
		
			// convert to long, avoid the negative value 
			long rbLong = rb & 0xFF;
			
			// convert to string
			String strResult = Long.toString(rbLong,2);
		
			// create array string 
			String[] zeroAdd = new String[8];
			zeroAdd[0] = "";
			zeroAdd[1] = "0";
			zeroAdd[2] = "00";
			zeroAdd[3] = "000";
			zeroAdd[4] = "0000";
			zeroAdd[5] = "00000";
			zeroAdd[6] = "000000";
			zeroAdd[7] = "0000000";
			
			// length number
			int sumNumber = strResult.length();
			int diffNumber = 8 - sumNumber;
			String endResult = zeroAdd[diffNumber] + strResult;

			//tvMonitor.setText(zeroAdd[diffNumber]+strResult);
			
			// status in each por 
			final char statCh[] = new char[8];
			statCh[0] = endResult.charAt(7);
			statCh[1] = endResult.charAt(6);
			statCh[2] = endResult.charAt(5);
			statCh[3] = endResult.charAt(4);
			statCh[4] = endResult.charAt(3);
			statCh[5] = endResult.charAt(2);
			statCh[6] = endResult.charAt(1);
			statCh[7] = endResult.charAt(0);
			
			// status channel
			//int statuse = Activity.
			//tvRb4.setText(Character.toString(statCh[4]));
			// channel 4
			if (Character.getNumericValue(statCh[4]) == 1) {
				btL4.setText("On");
				btL4.setBackgroundColor(android.graphics.Color.GREEN);
			}
			
			// chanel 5
			if (Character.getNumericValue(statCh[5]) == 1) {
				btL5.setText("On");
				btL5.setBackgroundColor(android.graphics.Color.GREEN);
			}
			// channel 6
			if (Character.getNumericValue(statCh[6]) == 1) {
				btL6.setText("On");
				btL6.setBackgroundColor(android.graphics.Color.GREEN);
			}
			// channel 7
			if (Character.getNumericValue(statCh[7]) == 1) {
				btL7.setText("On");
				btL7.setBackgroundColor(android.graphics.Color.GREEN);
			} 
		} catch(UnknownError e) {
			e.printStackTrace();
		}

		// back to main menu
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
		// text view
		tvIpAddress = (TextView) findViewById(R.id.tvIpAddress);	
		tvTemp = (TextView) findViewById(R.id.tvTemp);
		tvRb4 = (TextView) findViewById(R.id.tvRb4);
		tvRb5 = (TextView) findViewById(R.id.tvRb5);
		tvRb6 = (TextView) findViewById(R.id.tvRb6);
		tvRb7 = (TextView) findViewById(R.id.tvRb7);
		
		// button
		btL4 = (Button) findViewById(R.id.btL4);
		btL5 = (Button) findViewById(R.id.btL5);
		btL6 = (Button) findViewById(R.id.btL6);
		btL7 = (Button) findViewById(R.id.btL7);
		btOff = (Button) findViewById(R.id.btOff);			
	}	
}