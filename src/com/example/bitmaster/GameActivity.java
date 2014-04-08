package com.example.bitmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class GameActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_layout);
		FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		Grid g = new Grid(this, 2);
		fl.addView(g);
	}
}
