package com.example.bitmaster;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Grid extends TableLayout {
	public Grid(Context context, int side) {
		super(context);
		_context = context;
		_table = new TableLayout(_context);
		_side = side;
		_bins = new int[side][side];
		fillTable(_context, side);
	}
	
	private void fillTable(Context context, int side) {
		for (int i = 0; i < side; i++) {
			TableRow tr = new TableRow(context);
			for (int n = 0; n < side; n++){
				TextView tv = new TextView(context);
				int bin = (int) Math.random() * 2;
				_bins[i][n] = bin;
				tv.setText(bin + "");
				tr.addView(tv);
			}
			_table.addView(tr);
		}
	}

	private int[][] _bins;
	private Context _context;
	private int _side;
	private TableLayout _table;
}