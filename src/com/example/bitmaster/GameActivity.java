package com.example.bitmaster;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GameActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		
		_size = 8;
		grid = new Grid(this, _size);
		
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(50*_size,50*_size, Gravity.CENTER_HORIZONTAL);
		fl.addView(grid,p);
		
		goal = (TextView) findViewById(R.id.goal);
		goal.setText(String.valueOf(0));
		
		current = (TextView)findViewById(R.id.current);
		String bin = grid.getBin(); 
		int dec = 0;
		for(int i = 0; i<bin.length(); i++) {
			if(Integer.valueOf(bin.substring(i, i+1)) == 0)
				dec = dec * 2;
			else
				dec = (dec * 2) +1;
		}
		
		current.setText(String.valueOf(dec));
	}
	
	public void setAnswers(int[] a) {
		_answers = a;
	}
	
	public void update(Boolean result) {
		current.setText(String.valueOf(grid.getBin()));
		if (result){
			_count ++;
			goal.setText(String.valueOf(_answers[_count]));
		}
			
	}
	
	TextView goal;
	TextView current;
	Grid grid;
	private int[] _answers;
	private int _size;
	private Random _random;
	private int _count;
}
