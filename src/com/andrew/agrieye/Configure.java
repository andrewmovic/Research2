package com.andrew.agrieye;

import android.app.Activity;
import com.andrew.agrieye.Picnic;
import com.andrew.agrieye.PreferenceConnector;

public class Configure extends Activity {
	private String ipAddress;
	private int udpPortS, udpPortP;
	public Picnic pic;
	
	
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
		char statCh[] = new char[8];
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
	
}
