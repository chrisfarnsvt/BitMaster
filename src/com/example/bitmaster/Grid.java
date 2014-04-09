package com.example.bitmaster;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Grid extends TableLayout implements View.OnTouchListener {
	public Grid(Context context, int side) {
		super(context);
		_context = context;
		_side = side;
		_bins = new int[side][side];
		_views = new TextView[side][side];
		fillTable(_context, _side);
		setOnTouchListener(this);
	}
	
	private void fillTable(Context context, int side) {
		for (int i = 0; i < side; i++) {
			TableRow tr = new TableRow(context);
			for (int n = 0; n < side; n++){
				TextView tv = new TextView(context);
				int bin = (int) (Math.random() * 2);
				_bins[i][n] = bin;
				_views[i][n] = new TextView(_context);
				_views[i][n].setText(bin + "");
				_views[i][n].setTextSize(24);
				_views[i][n].setBackgroundColor(Color.YELLOW);
				_views[i][n].setWidth(50);
				_views[i][n].setHeight(50);
				_views[i][n].setGravity(Gravity.CENTER);
				tr.addView(_views[i][n]);
			}
			this.addView(tr);
		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){		
			case MotionEvent.ACTION_MOVE:
				{
		
				}
	
			case MotionEvent.ACTION_DOWN:
				{
					_views[(int) (event.getY()/50)][(int) (event.getX()/50)].setBackgroundColor(Color.RED);
				}	
	
			case MotionEvent.ACTION_UP:
				{
		
				}
		}
		return true;
	}

	private int[][] _bins;
	private TextView[][] _views;
	private Context _context;
	private int _side;
	//private TableLayout _table;

}