package com.example.bitmaster;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case (R.id.action_settings):
			
			Intent settingsActivity = new Intent(getBaseContext(),
			PrefActivity.class);
			startActivity(settingsActivity);
			return true;
		}
		return false;
	}
	
	public void startGame(View Button) {
		Intent gameActivity = new Intent(getBaseContext(), GameActivity.class);
		startActivity(gameActivity);
		
	}
	
	public void about(View Button) {
		setContentView(R.layout.about);
	}
	
	public void story(View Button){
		setContentView(R.layout.story);
	}
	
	public void tutorial(View Button) {
		setContentView(R.layout.tutorial);
	}
	
	public void home(View Button){
		setContentView(R.layout.start_layout);
	}
	
	public void startOptions(View Button) {
		Intent settingsActivity = new Intent(getBaseContext(), PrefActivity.class);
		startActivity(settingsActivity);
	}

}
