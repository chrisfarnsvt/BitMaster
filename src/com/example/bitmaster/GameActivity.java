package com.example.bitmaster;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		
		reset (null);
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
	
	public void setAnswers(int[] a) {
		_answers = a;
	}
	
	public void changeCurrent() {
		String bin = grid.getBin();
		long dec = 0;
		for(int i = 0; i<bin.length(); i++) {
			if(Long.valueOf(bin.substring(i, i+1)) == 0)
				dec = dec * 2;
			else
				dec = (dec * 2) +1;
		}
		if (String.valueOf(dec).length() < 8)
			current.setText(String.valueOf("Selected: " + dec));
	}
	
	public void update(Boolean result) {
		changeCurrent();
		mult.setText(String.valueOf("Multiplier: x" + grid.getMult()));
		if (result){
			_count ++;
			
			if(_count == _answers.length) {
				Toast.makeText(this, "You are a true Bitspiditioner!", Toast.LENGTH_SHORT).show();
				score.setText(String.valueOf("Points: " + grid.getPoints()));
				win();
			}
			else {
				Toast.makeText(this, "Well done, Bitsplorer!", Toast.LENGTH_SHORT).show();
				score.setText(String.valueOf("Points: " + grid.getPoints()));
				goal.setText(String.valueOf("Find: " +_answers[_count]));
			}
		}
			
	}
	
	public void reset (View Button) {
		
		FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		fl.removeAllViews();
		_size = 8;
		grid = new Grid(this, _size);
		
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(50*_size,50*_size, Gravity.CENTER_HORIZONTAL);
		fl.addView(grid,p);
		
		score = (TextView) findViewById(R.id.score);
		score.setText(String.valueOf("Points: 0"));
		score.setTextSize(20);
		
		mult = (TextView) findViewById(R.id.mult);
		mult.setText("Multiplier: x1");
		mult.setTextSize(20);
		
		goal = (TextView) findViewById(R.id.goal);
		goal.setText(String.valueOf("Find: " + _answers[0]));
		goal.setTextSize(20);
		
		current = (TextView)findViewById(R.id.current);
		current.setTextSize(20);
		String bin = grid.getBin(); 
		int dec = 0;
		for(int i = 0; i<bin.length(); i++) {
			if(Integer.valueOf(bin.substring(i, i+1)) == 0)
				dec = dec * 2;
			else
				dec = (dec * 2) +1;
		}
			for(int i : _answers) {
				grid.placeNum(i);
			}
		
		if (String.valueOf(dec).length() < 8)
		current.setText(String.valueOf("Selected: " + dec));
		_count = 0;
	}
	
	public void win() {
		FrameLayout fl = (FrameLayout)findViewById(R.id.frameLayout);
		fl.removeAllViews();
		
		WinPanel wp = new WinPanel(this);
		LayoutParams lp = new FrameLayout.LayoutParams(fl.getWidth(), fl.getHeight());
		fl.addView(wp, lp);

	}
		
		
	TextView mult;
	TextView score;
	TextView goal;
	TextView current;
	Grid grid;
	private int[] _answers;
	private int _size;
	private Random _random;
	private int _count;
	
}
