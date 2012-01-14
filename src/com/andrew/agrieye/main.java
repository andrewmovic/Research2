package com.andrew.agrieye;

/* Main Program v 1.0
 * January 14, 2012 
 * andrew@ugm.ac.id */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.andrew.agrieye.picnic.PicnicConfig;

public class main extends Activity {
	/** Called when the activity is first created. */
	private Button btCheckConnectivity;
	private Button btOff;
	private Button btStatus;
	private Button btActuate;
	private Button btSetting;
	private Button btSchedule;
	private Button btSchedule2;	
	private SharedPreferences getIpAddress;
  	private static String etIpSetting;
  	private TextView tvStatIp;
  	private PicnicConfig picConfig;
  	private Toast toast;
  	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        picConfig = new PicnicConfig();
        // shared preferences
        getIpAddress = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		etIpSetting = getIpAddress.getString("ip", "192.168.11.224");
		
		
		initView();

        // method menu for each button
        btOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        // button menu Status
        btStatus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openStatus = new Intent("com.andrew.agrieye.STATUS");
				startActivity(openStatus);
			}
		});
          
        // button actuate
        btActuate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openActuate = new Intent("com.andrew.agrieye.ACTUATE");
				startActivity(openActuate);
			}
		});
        
        // menu setting
        btSetting.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openPrefs = new Intent("com.andrew.agrieye.PREFS");
				startActivity(openPrefs);
			}
		});
        
        // menu schedule
        btSchedule.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openSchedule = new Intent("com.andrew.agrieye.AGRISCHEDULE");
				startActivity(openSchedule);
			}
		});
         
        
        // set status ip
        tvStatIp.setText(etIpSetting);
        
        // bt Check Connectivity
        btCheckConnectivity.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(picConfig.checkAvailability()== true) {
					toastAv("Connection OK");
				} else {
					toastAv("No Connection");
				}
							
			}
		});
	}
	private void initView() {
		// TODO Auto-generated method stub
        btCheckConnectivity = (Button) findViewById(R.id.btCheckConnectivity);
        btOff = (Button) findViewById(R.id.btOff);
        btStatus = (Button) findViewById(R.id.btStatus);
        btActuate = (Button) findViewById(R.id.btActuate);
    	btSetting = (Button) findViewById(R.id.btSetting);
        btSchedule = (Button) findViewById(R.id.btSchedule);
        tvStatIp = (TextView) findViewById(R.id.tvStatIp);
	}
	
	// option menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.agrieye_menu, menu);
        return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	// void for toast
	private void toastAv(String message){		
		Context context = getApplicationContext();
		toast =Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	

}