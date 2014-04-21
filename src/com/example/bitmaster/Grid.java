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
		_answers = new int[5];
		_used = new boolean[side][side];
		for (int i = 0; i < 5; i++) {
			_answers[i] = (int)(Math.random()*25);
		}
		((GameActivity) _context).setAnswers(_answers);
		_selCoords = new int[side][side];
		_views = new TextView[side][side];
		fillTable(_context, _side);
		setOnTouchListener(this);
	}
	
	/**
	 * this method places a number randomly on the grid using valid moves to place each digit
	 * a starting point is randomly generated using the local variables x and y
	 * @param n the number to put in the grid
	 * @return
	 */
	public boolean placeNum(int n, int color) {
		String bin = Integer.toBinaryString(n);
		int x = (int)(Math.random() * _side);
		int y = (int)(Math.random() * _side);
		
		for (int i = 0; i < bin.length(); i++) {
			String s = bin.substring(i,i+1);
			//this random is determining whether to move horizontally or vertically
			int r = (int) (Math.random() * 2);
			//move horizontal
			if (r == 0) {
				//determine which direction to move in
				r = (int) (Math.random() * 2);
				//move right
				if (r==0 && x<_side-1) {
					if( _used[x+1][y] != true) {
						_bins[x+1][y] = Integer.valueOf(s);
						_views[x+1][y].setText(s);
						_views[x+1][y].setBackgroundColor(color);
						_used[x+1][y] = true;
						x+=1;
					}
				}
				//move left
				else if (r==1 && x>0) {
					if (_used[x-1][y] != true) {
						_bins[x-1][y] = Integer.valueOf(s);
						_views[x-1][y].setText(s);
						_views[x-1][y].setBackgroundColor(color);
						_used[x-1][y] = true;
						x-=1;
					}
				}
				else i--;
				
			}
			//move vertically
			else {
				//determine which direction to move in
				r = (int) (Math.random() * 2);
				//move up
				if (r==0 && y<_side) {
					if (_used[x][y+1] != true) {
						_bins[x][y+1] = Integer.valueOf(s);
						_views[x][y+1].setText(s);
						_views[x][y+1].setBackgroundColor(color);
						_used[x][y+1] = true;
						y+=1;
					}
				}
				
				//move down
				else if (r ==1 && y>0) {
					if (_used[x][y-1] != true) {
						_bins[x][y-1] = Integer.valueOf(s);
						_views[x][y-1].setText(s);
						_views[x][y-1].setBackgroundColor(color);
						_used[x][y-1] = true;
						y-=1;
					}
				}
				else i--;
			}
		
		}
		return false;
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
			case MotionEvent.ACTION_MOVE: {
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
							_selCoords[_prevCell[1]][_prevCell[0]] = 0;
							_bin = _bin.substring(0, _bin.length() - 1);
							return true;
							
						}
						_selCoords[x][y] = 1;
						_bin += _views[y][x].getText() + "";
						_views[y][x].setBackgroundColor(Color.RED);
						return true;
					}
					return false;
				}	
	
			case MotionEvent.ACTION_DOWN:
			{
				_bin = "";
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
							_selCoords[_prevCell[1]][_prevCell[0]] = 0;
							_bin = _bin.substring(0, _bin.length() - 1);
							return true;
							
						}
						_selCoords[x][y] = 1;
						_bin += _views[y][x].getText() + "";
						_views[y][x].setBackgroundColor(Color.RED);
						return true;
					}
					return false;
				}	
	
			case MotionEvent.ACTION_UP:
				{
					for (int i = 0; i < _side; i++) {
						for (int n = 0; n < _side; n++) {
							if (_selCoords[n][i] == 1) {
									_views[i][n].setBackgroundColor(Color.YELLOW);
									_selCoords[n][i] = 0;
							}
						}
					}
					((GameActivity) _context).update(checkAnswer(_bin));
					_curCell[0] = -1;
					_curCell[1] = -1;
					_prevCell[0] = -1;
					_prevCell [1] = -1;
					Log.i("bin is", _bin);
					return true;
				}
		}
		return false;
	}
	
	private boolean checkAnswer(String bin) {
		int check = Integer.parseInt(bin, 2);
		for (int i = 0; i < 5; i++) {
			if (_answers[i] == check) {
				_answers[i] = -1;
				return true;
			}
		}
		return false;
	}

	public String getBin() {
		return _bin;
	}

	
	private boolean _isRight;
	private int[] _answers;
	private int[] _curCell;
	private int[] _prevCell;
	private int[][] _bins;
	private boolean[][] _used;
	private String _bin;
	private TextView[][] _views;
	private int[][] _selCoords;
	private Context _context;
	private int _side;
}