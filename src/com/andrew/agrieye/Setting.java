package com.andrew.agrieye;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.andrew.agrieye.Picnic;
import android.view.View.OnClickListener;
import com.andrew.agrieye.PreferenceConnector;

public class Setting extends Activity {
	// properties
	Button btOff, btSave, btRevert;
	EditText edIpAddress, edPortSerial, edPortParalel;
	TextView tvIpAddress, tvSerial, tvParalel;
	Picnic pic;
	int udpPortS, udpPortP;
	String ipAddress;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		init();
		readSetting();
		
		// save data method
		btSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				save();
				finish();
			}
		});
		
		// revert data method
		btRevert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reset();
			}
		});
		
		// back to main menu method
		btOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	// called when first activity created, initialization 
	public void init() {
		// button inisiation
		btOff = (Button) findViewById(R.id.btOff);
		btSave = (Button) findViewById(R.id.btSave);
		btRevert = (Button) findViewById(R.id.btRevert);
		
		// text view
		tvIpAddress = (TextView) findViewById(R.id.tvIpAddress);
		tvSerial = (TextView) findViewById(R.id.tvSerial);
		tvParalel = (TextView) findViewById(R.id.edPortParalel);
		
		// edit text
		edIpAddress = (EditText) findViewById(R.id.edIpAddress);
		edPortSerial = (EditText) findViewById(R.id.edPortSerial);
		edPortParalel = (EditText) findViewById(R.id.edPortParalel);
		readSetting();
		tvIpAddress.setText(PreferenceConnector.readString(this, PreferenceConnector.IPADDRESS, null));
		tvSerial.setText(PreferenceConnector.readString(this, PreferenceConnector.PORTSERIAL, null));
		tvParalel.setText(PreferenceConnector.readString(this, PreferenceConnector.PORTPARALEL, null));
	}
	
	// connect to picnic
	public void connectPicnic() {
		ipAddress = PreferenceConnector.readString(this,PreferenceConnector.IPADDRESS, "192.168.64.44");
	    udpPortS = PreferenceConnector.readInteger(this, PreferenceConnector.PORTSERIAL,10001);
	    udpPortP = PreferenceConnector.readInteger(this, PreferenceConnector.PORTPARALEL, 10002);	    
	    pic = new Picnic(ipAddress,udpPortS,udpPortP);
	}
	
	// public class picnic setting
	
	public char statusChannel(int numberChannel) {
		connectPicnic();
		// read from picnic
		byte rb = pic.getRB();
	
		// convert to long, avoid the negative value 
		long rbLong = rb & 0xFF;
		
		// convert long to string
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
		
		// count length number
		int sumNumber = strResult.length();
		int diffNumber = 8 - sumNumber;
		String endResult = zeroAdd[diffNumber] + strResult;
		
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
		
		return  statCh[numberChannel-1];
		
	}
		
	public void save() {
		String ipAddress = edIpAddress.getText().toString();
		String portSerial = edPortSerial.getText().toString();
		String portParalel = edPortParalel.getText().toString();
		
		if(ipAddress != null) 
			PreferenceConnector.writeString(this, PreferenceConnector.IPADDRESS, ipAddress);
		if(portSerial != null)
			PreferenceConnector.writeString(this, PreferenceConnector.PORTSERIAL, portSerial);
		if(portParalel != null)
			PreferenceConnector.writeString(this, PreferenceConnector.PORTPARALEL, portParalel);
	}
	
	public void reset() {
		PreferenceConnector.getEditor(this).remove(PreferenceConnector.IPADDRESS).commit();
		PreferenceConnector.getEditor(this).remove(PreferenceConnector.PORTSERIAL).commit();
		PreferenceConnector.getEditor(this).remove(PreferenceConnector.PORTPARALEL).commit();
		readSetting();
	}
	// setting data configuration
	private void readSetting() {
		edIpAddress.setText(PreferenceConnector.readString(this,PreferenceConnector.IPADDRESS,null));
		edPortSerial.setText(PreferenceConnector.readString(this,PreferenceConnector.PORTSERIAL,null));
		edPortParalel.setText(PreferenceConnector.readString(this,PreferenceConnector.PORTPARALEL,null));
		
	}
	
	
}
