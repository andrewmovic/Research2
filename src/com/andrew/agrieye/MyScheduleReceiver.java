package com.andrew.agrieye;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyScheduleReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
	 // TODO Auto-generated method stub

	 Intent scheduledIntent = new Intent(context, MyScheduleActivity.class);
	 scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 context.startActivity(scheduledIntent);

	}
}
