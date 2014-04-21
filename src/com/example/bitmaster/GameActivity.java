package com.example.bitmaster;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
	
	public void setAnswers(int[] a) {
		_answers = a;
	}
	
	public void update(Boolean result) {
		String bin = grid.getBin();
		long dec = 0;
		for(int i = 0; i<bin.length(); i++) {
			if(Long.valueOf(bin.substring(i, i+1)) == 0)
				dec = dec * 2;
			else
				dec = (dec * 2) +1;
		}
		current.setText(String.valueOf(dec));
		if (result){
			_count ++;
			if(_count == _answers.length) {
				Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show();
				reset(null);
			}
			else {
				Toast.makeText(this, "You (partially) won!", Toast.LENGTH_LONG).show();
				goal.setText(String.valueOf("Look for: " +_answers[_count]));
			}
		}
			
	}
	
	public void reset (View Button) {
		
		FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		
		_size = 8;
		grid = new Grid(this, _size);
		
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(50*_size,50*_size, Gravity.CENTER_HORIZONTAL);
		fl.addView(grid,p);
		
		goal = (TextView) findViewById(R.id.goal);
		goal.setText(String.valueOf("Look for: " + _answers[0]));
		goal.setTextSize(20);
		
		current = (TextView)findViewById(R.id.current);
		String bin = grid.getBin(); 
		int dec = 0;
		for(int i = 0; i<bin.length(); i++) {
			if(Integer.valueOf(bin.substring(i, i+1)) == 0)
				dec = dec * 2;
			else
				dec = (dec * 2) +1;
		}
			boolean done = false;
			for(int i : _answers) {
				grid.placeNum(i, Color.YELLOW);
			}
		
		current.setText(String.valueOf(dec));
		_count = 0;
	}
		
	
	TextView goal;
	TextView current;
	Grid grid;
	private int[] _answers;
	private int _size;
	private Random _random;
	private int _count;
	
}
