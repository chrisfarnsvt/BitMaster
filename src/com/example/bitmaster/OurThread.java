package com.example.bitmaster;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/* This class moves all the drawing to a separate thread to
 * improve performance
 */

public class OurThread extends Thread {
	   private WinPanel _WinPanel; //The WinPanel we're displaying to
	   private SurfaceHolder _holder; //The drawable surface's container
	   private boolean _isRunning; //is it running?
	   private long _start; //The time the last update occurred at
	   private long _elapsed; //how much time has passed since last update

	   public OurThread(WinPanel WinPanel) {
	      _WinPanel = WinPanel;
	      _holder = _WinPanel.getHolder();
	   }

	   public void setRunning(boolean run) { //Are we running?
	      _isRunning = run;
	   }

	   /**
	    * runs our app
	    */
	   @Override
	   public void run() { 
	      Canvas canvas = null; //starts null so that a new canvas can be created at each new run

	      // Retrieve system time when this starts (in milliseconds)
	     _start = System.currentTimeMillis();

	      // Thread loop
	      while (_isRunning) {
	         canvas = _holder.lockCanvas(); //locks our Canvas in place (can't change)

	         if (canvas != null) {
	            _elapsed = System.currentTimeMillis() - _start;
	            _WinPanel.update(_elapsed); //We've grabbed elapsed time, and now can update based on that
	            _WinPanel.doDraw(canvas, _elapsed); //Draws the canvas again based on that time
	            _holder.unlockCanvasAndPost(canvas); //releases our canvas to reflect the changes we made
	         }
	         // Update the start time
	         _start = System.currentTimeMillis();
	      }
	   }
}