package com.example.bitmaster;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
		_bin = "";
		_bins = new int[side][side];
		_curCell = new int[2];
		_prevCell = new int[2];
		_selCoords = new int[side][side];
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
	//bacon is a food group for real bros
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){		
			case MotionEvent.ACTION_MOVE:
				{
					int x = (int) event.getX() / 50;
					int y = (int) event.getY() / 50;
					if (x > (_side - 1) || y > (_side - 1) || x < 0 || y < 0)
						return true;
					if (x != _curCell[0] || y != _curCell[1]) {
						_prevCell[0] = _curCell[0];
						_prevCell[1] = _curCell[1];
						_curCell[0] = x;
						_curCell[1] = y;
						if (_selCoords[x][y] == 1) {
							_views[_prevCell[1]][_prevCell[0]].setBackgroundColor(Color.YELLOW);
							_views[y][x].setBackgroundColor(Color.YELLOW);
							_selCoords[x][y] = 0;
							_selCoords[_prevCell[1]][_prevCell[0]] = 0;
							return true;
						}
						Log.i("information", "move called");
						_selCoords[x][y] = 1;
						_views[y][x].setBackgroundColor(Color.RED);
						return true;
					}
					return false;
				}
	
			case MotionEvent.ACTION_DOWN:
			{
				int x = (int) event.getX() / 50;
				int y = (int) event.getY() / 50;
				if (x > (_side - 1) || y > (_side - 1) || x < 0 || y < 0)
					return true;
				if (x != _curCell[0] || y != _curCell[1]) {
						_prevCell[0] = _curCell[0];
						_prevCell[1] = _curCell[1];
						_curCell[0] = x;
						_curCell[1] = y;
						if (_selCoords[x][y] == 1) {
							_views[_prevCell[1]][_prevCell[0]].setBackgroundColor(Color.YELLOW);
							_views[y][x].setBackgroundColor(Color.YELLOW);
							_selCoords[x][y] = 0;
							_selCoords[_prevCell[1]][_prevCell[0]] = 0;
							return true;
						}
						Log.i("information", "move called");
						_selCoords[x][y] = 1;
						_views[y][x].setBackgroundColor(Color.RED);
						return true;
					}
					return false;
				}	
	
			case MotionEvent.ACTION_UP:
				{
					Log.i("info", "up called");
					for (int i = 0; i < _side; i++) {
						for (int n = 0; n < _side; n++) {
							if (_selCoords[n][i] == 1) {
									_views[i][n].setBackgroundColor(Color.YELLOW);
									_bin += _views[i][n].getText();
									_selCoords[n][i] = 0;
							}
						}
					}
					return true;
				}
			}
				return false;
	}
	
	

	private int[] _curCell;
	private int[] _prevCell;
	private int[][] _bins;
	private String _bin;
	private TextView[][] _views;
	private int[][] _selCoords;
	private Context _context;
	private int _side;
	//private TableLayout _table;

}