package com.andrew.agrieye.schedule;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.andrew.agrieye.picnic.Picnic;
import com.andrew.agrieye.picnic.PicnicConfig;
import com.andrew.agrieye.R;

public class ScheduleActivity extends Activity {
	private Button btCheck1;
	private ToggleButton tbChannel4;
	private Toast toast;
	private String currentIpAddress;
	private SharedPreferences setting;
	private Picnic pic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities);
		
		// method initialized view
		initView();
		btCheck1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}	
		});
		
	}

	public boolean checkAvailability(){
		// check status connectivity        
	     try {
	    	 // Create a URL for the desired page        	  	
            //URL url = new URL("http://"+currentIpAddress+"");
	    	 //URL url = new URL("http://192.168.64.44");
	    	 // Read all the text returned by the server
            String host = currentIpAddress;
	    	//String host = "192.168.64.44";
	    	InetAddress inet = InetAddress.getByName(host);
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        	return false;
        }
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		btCheck1 = (Button) findViewById(R.id.btCek1);
		tbChannel4 = (ToggleButton) findViewById(R.id.tbChannel4);
		//currentIpAddress = setting.getString("ip", "192.168.64.44");
		currentIpAddress ="192.168.64.45";
	}
	
	private void toastAv(String message){		
		Context context = getApplicationContext();
		toast =Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
		
}
