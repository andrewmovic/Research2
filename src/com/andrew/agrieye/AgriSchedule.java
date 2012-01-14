package com.andrew.agrieye;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgriSchedule extends Activity{
	private Button btStart;
	private Toast toast;
	private EditText etInterval;
	private TextView tvHasil;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agrieye_schedule);
		etInterval = (EditText) findViewById(R.id.etInterval);
		btStart = (Button) findViewById(R.id.btStart);
		tvHasil = (TextView) findViewById(R.id.tvHasil);
		
		btStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toast properties
				Context context = getApplicationContext();
				String message = "Schedule is Active";
				int duration = Toast.LENGTH_SHORT;
				tvHasil.setText(etInterval.getText().toString());
				toast = Toast.makeText(context, message, duration);
				toast.show();
			}
		});
	
	}
	public void turnOn(String portNum, String status){
		
	}
}
