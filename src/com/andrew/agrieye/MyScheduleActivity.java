package com.andrew.agrieye;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyScheduleActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 // TODO Auto-generated method stub
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.layout_scheduleactivity);

	 Button buttonDismiss = (Button)findViewById(R.id.dismiss);

	 buttonDismiss.setOnClickListener(new Button.OnClickListener(){

	  @Override
	  public void onClick(View arg0) {
	   // TODO Auto-generated method stub
	   finish();
	  }});
	}
}
