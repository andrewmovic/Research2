package com.andrew.agrieye.picnic;

/* PicnicConfig 1.0
 * Picnic configuration for AgriEye System
 * January 14, 2012 
 * andrew@ugm.ac.id */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class PicnicConfig {
	private String ipAddress;
	private int udpPortP;
	private int udpPortS;
	private Picnic pic;
	private String result;
	
	// constructor for set Ip Address
	public PicnicConfig(String theIpAddress) {
		this.ipAddress = theIpAddress;
	}
	// method for check connectivity
	
	public boolean checkConnectivity(){
		try {
			URL url = new URL("http://"+ipAddress);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(1000);
			InputStream input = connection.getInputStream();
			if(input != null) {
				return true;
			} else {
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;	
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	// void method to connect to picnic
	public boolean connect(){
		udpPortP = 10001; // static
		udpPortS = 10002; // static
		try {
			pic = new Picnic(ipAddress,udpPortP, udpPortS);			
			return true;
		} catch (UnknownError e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	// check status channel 1 - 7 
	public boolean checkStatus(int channel) {
		// ---- check status button
		if(connect() == true) {	
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
			
			// status in each channel
			final char statCh[] = new char[8];
			statCh[0] = endResult.charAt(7);
			statCh[1] = endResult.charAt(6);
			statCh[2] = endResult.charAt(5);
			statCh[3] = endResult.charAt(4);
			statCh[4] = endResult.charAt(3);
			statCh[5] = endResult.charAt(2);
			statCh[6] = endResult.charAt(1);
			statCh[7] = endResult.charAt(0);	
			if(statCh[channel] == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		} 
	}
	// method to turn on
	public void turnOn(int channel){
		if(connect() == true ) {
			pic.setHigh("RB", channel);
			result = "ON";
		} else {
			result = "OFF";
		}
	}
	
	// method to turn off
	public void turnOff(int channel){
		if(connect() == true) {
			pic.setLow("RB", channel);
			result = "OFF";
		} else {
			result = "ON";
		}
	}
	
}