package com.andrew.agrieye;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AndroidScheduledActivity extends Activity {
	/** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.schedule_activity);
	      Button buttonStart = (Button)findViewById(R.id.start);
	      buttonStart.setOnClickListener(new Button.OnClickListener(){

	  @Override
	  public void onClick(View arg0) {
	   // TODO Auto-generated method stub
	    Intent myIntent = new Intent(getBaseContext(),MyScheduleReceiver.class);

	    PendingIntent pendingIntent
	     = PendingIntent.getBroadcast(getBaseContext(),
	       0, myIntent, 0);
	  
	    AlarmManager alarmManager
	      = (AlarmManager)getSystemService(ALARM_SERVICE);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(System.currentTimeMillis());
	    calendar.add(Calendar.SECOND, 10);
	    long interval = 60 * 1000; //
	    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
	      calendar.getTimeInMillis(), interval, pendingIntent);
	    finish();
	  }});
	  }
}
