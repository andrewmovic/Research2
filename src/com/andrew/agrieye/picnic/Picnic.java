package com.andrew.agrieye.picnic;

/*
 *  $Id: Picnic.java,v 1.5 2008/06/24 03:07:15 t961578 Exp $
 *       Author: Maehara Masahide
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;

public class Picnic extends Activity {
    private InetAddress sAddr;
    private DatagramSocket soc;
    private DatagramPacket sendPacket,recvPacket;
    private int _pport; // Parallel Port UDP
    private int _lport; // LCD Port UDP
    private byte[] buf = new byte[8];
    private Map<String, Byte> raADCMap = new HashMap<String, Byte>();
    private Map<String, Byte> rabSETMap = new HashMap<String, Byte>();

    public Picnic(String ipaddr,int pport){
	this._pport = pport;
	try {
	    sAddr = InetAddress.getByName(ipaddr);	    
	    soc = new DatagramSocket();
	    // RA0->0x81, RA1->0x89, RA2->0x91, RA3->0x99, RA4->0xa1
	    raADCMap.put("RA0",(byte)0x81);
	    raADCMap.put("RA1",(byte)0x89);
	    raADCMap.put("RA2",(byte)0x91);
	    raADCMap.put("RA3",(byte)0x99);
	    raADCMap.put("RA4",(byte)0xa1);
	    // RA->0x05, RB->0x06
	    rabSETMap.put("RA",(byte)0x05);
	    rabSETMap.put("RB",(byte)0x06);
	} catch (UnknownHostException e){
	    e.printStackTrace();
	} catch (SocketException e){
	    e.printStackTrace();
	}
    }
    
    public Picnic(String ipaddr,int pport,int lport){
	this(ipaddr,pport);
	this._lport = lport;
    }

    public void communicate(byte[] cmd,int port){
	try {
	    sendPacket = new DatagramPacket(cmd,cmd.length,sAddr,port);
	    recvPacket = new DatagramPacket(buf,buf.length);
	    soc.send(sendPacket);
	    soc.receive(recvPacket);
	} catch (SocketException e){
	    e.printStackTrace();
	} catch (IOException e){
	    e.printStackTrace();
	} 
    }

    public void lcdClear(){
	byte[] cmd = {(byte)0x01,(byte)0x01};
	communicate(cmd,_lport);
    }

    public void lcdDisp(String str){
	byte[] cmd = new byte[str.length() + 1];
	byte[] s = str.getBytes();
	cmd[0] = (byte)0x00;
	for (int i = 0;i < str.length();i++){
	    cmd[i + 1] = s[i];
	}
	communicate(cmd,_lport);
    }

    public void lcdDisp(String str,int x,int y){
	byte[] cmd = new byte[str.length() + 2];
	byte[] s = str.getBytes();
	cmd[0] = (byte)0x01;
	cmd[1] = (byte)(0x80 | (y << 6) | x);
	for (int i = 0;i < str.length();i++){
	    cmd[i + 2] = s[i];
	}
	communicate(cmd,_lport);
    }

    public void getStatus(){
	byte[] cmd = {(byte)0x00	};
	communicate(cmd,_pport);
    }

    public byte getRA(){
	getStatus();
	return buf[0];
    }

    public byte getRB(){
	getStatus();
	return buf[1];
    }

    public void setHigh(String pport,int bit){
	byte[] cmd = new byte[3];
	cmd[0] = (byte)0x01;
	cmd[1] = ((Byte)rabSETMap.get(pport)).byteValue();
	cmd[2] = (byte)bit; 
	communicate(cmd,_pport);
    }

    public void setLow(String pport,int bit){
	byte[] cmd = new byte[3];
	cmd[0] = (byte)0x02;
	cmd[1] = ((Byte)rabSETMap.get(pport)).byteValue();
	cmd[2] = (byte)bit;
	communicate(cmd,_pport);
    }

    public void setInput(String pport,int bit){
	setHigh(pport,bit);
    }

    public void setOutput(String pport,int bit){
	setLow(pport,bit);
    }

    public int getADC(String ch,byte wait){
	byte[] cmd = new byte[3];
	cmd[0] = (byte)0x04;
	cmd[1] = ((Byte)raADCMap.get(ch)).byteValue();
	cmd[2] = wait;
	communicate(cmd,_pport);
        // Ver 1.4 -> Ver 1.5
        // return ((buf[4] << 8) | buf[5]);
        int Hi = (buf[4] & 0xff), Lo = (buf[5] & 0xff);
        return ((Hi << 8) | Lo);
    }

    public int getTemp(){
   	return (int)Math.ceil(getADC("RA4",(byte)0x00) * 500.0 / 1024.0);
    }

    public void close(){
	soc.close();
    }
    
    
}
