package com.example.bitmaster;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WinPanel extends SurfaceView implements SurfaceHolder.Callback {

	   public WinPanel(Context context) {
	      super(context);
	      getHolder().addCallback(this); //This lets our panel receive information about changes to itself
	      _paint = new Paint();
	      _paint.setColor(Color.RED);
	      _paint.setTextAlign(Align.CENTER);
	      _paint.setTextSize(60);
	      _thread = new OurThread(this);
	      
	      
	   }
	   
	   @Override
	   public void surfaceCreated(SurfaceHolder holder) {
	      if (!_thread.isAlive()) { //In case there's no running thread, this sets one up
	    	  _thread = new OurThread(this); 
	    	  _thread.setRunning(true);
	    	  _thread.start();
	      }
	   }

	   @Override
	   public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	      // Store new extents of drawable surface
	      _maxWidth = width;
	      _maxHeight = height;
	   }

	   @Override
	   public void surfaceDestroyed(SurfaceHolder holder) {
	      if (_thread.isAlive()) //stops the thread
	    	  _thread.setRunning(false);
	   }
	   


	// Update all sprites in our list
	public void update(long elapsedTime) {
	   synchronized (_explodeList) {
		   addExplosion();
	      for (Explosion e: _explodeList) {
	         e.update();
	      }
	   }
	}

	// Draw all sprites in list
	public void doDraw(Canvas canvas, long elapsed) {
	   canvas.drawColor(Color.WHITE); //gives us a red background
	   canvas.drawText("YOU WIN!", _maxWidth/2, _maxHeight/2, _paint);
	   synchronized (_explodeList) { //this is done to prevent errors with memory and thread consistency (stops it from breaking horribly)
	      for (Explosion e : _explodeList) { //synchronized lets our two threads see exactly the same data
	         e.draw(canvas);
	      }
	   }
	   //Provides text info at the top of our display  
	}
	
	public void addExplosion(){
	   synchronized (_explodeList) {
		   Random r = new Random();
	      _explodeList.add(new Explosion(5, r.nextInt(1000), r.nextInt(1000)));
	      _numSprites = _explodeList.size();
	   }
	}

	   
	   private ArrayList<Explosion> _explodeList = new ArrayList<Explosion>();
	   private int _numSprites;
	   private Paint _paint;
	   public static float _maxWidth; //These are only public because I didn't feel like adding getters this time
	   public static float _maxHeight;
	   private OurThread _thread; //The thread we have for rendering visuals
}