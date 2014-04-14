package com.example.bitmaster;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;

public class GameActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		_size = 8;
		Grid g = new Grid(this, _size);
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(50*_size,50*_size, Gravity.CENTER_HORIZONTAL);
		fl.addView(g,p);
		//work please
	}
	
	private int _size;
}
