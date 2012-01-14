package com.andrew.agrieye;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.andrew.agrieye.R;

public class OneShotAlarm extends BroadcastReceiver{
	@Override
    public void onReceive(Context context, Intent intent)
    {
       Toast.makeText(context, R.string.one_shot_received, Toast.LENGTH_SHORT).show();
    }
}
