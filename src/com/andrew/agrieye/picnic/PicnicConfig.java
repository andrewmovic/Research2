package com.andrew.agrieye.picnic;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import android.content.SharedPreferences;

/*picnic configuration files*/

public class PicnicConfig {
	private String ipAddress;
	public String currentIpAddress;
	private int udpPortP;
	private int udpPortS;
	private SharedPreferences getIpAddress;
	private Picnic pic;
	int a;
	
public PicnicConfig(int a) {
		this.a = a;
	}
	
	// function to check connectivity
	public boolean checkAvailability(){
        String host = "192.168.64.44";		
		// check status connectivity        
	     try {
	         URL url = new URL("http://"+host);
	         URLConnection connection = url.openConnection();
	         connection.setConnectTimeout(1000);
	         InputStream input = connection.getInputStream();
	         if (input != null) {
	        	 return true;
	         } else {
	        	 return false;
	         }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        	return false;
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// if else 
	// void method to connect to picnic using load library picnic
	public boolean connect(){
		//currentIpAddress = getIpAddress.getString("ip","192.168.64.44");
		currentIpAddress ="192.168.64.44";
		udpPortP = 10001; // static
		udpPortS = 10002; // static
		try {
			pic = new Picnic(currentIpAddress, udpPortP,udpPortS);			
			return true;
		} catch (UnknownError e) {
			// TODO: handle exception
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
		connect();
		pic.setHigh("RB", channel);
	}
	
	// method to turn off
	public void turnOff(int channel){
		connect();
		pic.setLow("RB", channel);
	}
	
	// function to print hello world;
	public String helloSaja() {
		String hello;
		hello = "coba saja";
		return hello;
	}
	
}