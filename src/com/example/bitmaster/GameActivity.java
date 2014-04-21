package com.example.bitmaster;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

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
		goal.setText(String.valueOf(_answers[0]));
		
		current = (TextView)findViewById(R.id.current);
		String bin = grid.getBin(); 
		int dec = 0;
		for(int i = 0; i<bin.length(); i++) {
			if(Integer.valueOf(bin.substring(i, i+1)) == 0)
				dec = dec * 2;
			else
				dec = (dec * 2) +1;
		}
			/*grid.placeNum(10, Color.BLUE);
			grid.placeNum(13, Color.RED);
			grid.placeNum(8, Color.GREEN);
			grid.placeNum(4, Color.MAGENTA);
			grid.placeNum(15, Color.WHITE);
			grid.placeNum(7, Color.CYAN);*/
			boolean done = false;
			while (!done)
			done = grid.placeNum((int) Math.pow(2, 30), Color.MAGENTA);
		
		current.setText(String.valueOf(dec));
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
			Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show();
			if(_count == _answers.length);
				//make new grid
			else
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
