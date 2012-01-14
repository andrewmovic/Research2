package com.andrew.agrieye;

import android.app.ListActivity;
import android.content.Intent;
import android.view.MenuItem;

public class Menu extends ListActivity{
	/*
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater menuAgrieye = getMenuInflater();
		menuAgrieye.inflate(R.menu.agrieye_menu, menu);
		return true;
	}
	*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.aboutUs:
			// open the about us intent
			Intent i = new Intent("com.andrew.agrieye.ABOUT");
			startActivity(i);
			break;
		case R.id.preferences:
			// open the preferences intent
			Intent p = new Intent("com.andrew.agrieye.PREFS");
			startActivity(p);
			break;
		case R.id.logOut:
			finish();
			break;
		}
		return false;
		
	}
	
	
}
