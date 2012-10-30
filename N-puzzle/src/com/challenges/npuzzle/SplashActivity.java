package com.challenges.npuzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.challenges.npuzzle.R;

public class SplashActivity extends Activity {

	private final Runnable r_MainActivity = new Runnable() {
		public void run() {
			Intent myIntent = new Intent(SplashActivity.this,
					MainActivity.class);
			SplashActivity.this.startActivity(myIntent);
			finish();
		}
	};

	private final Handler h_getTo_nextActivity = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		startAnimations();
		checkBG();
		h_getTo_nextActivity.postDelayed(r_MainActivity, 4 * 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

	private void checkBG() {
		Context c_app = getApplicationContext();
		int areYouThere = c_app.getResources().getIdentifier("chopthatwordBG",
				"drawable", c_app.getPackageName());

		if (areYouThere == 0) {
			generateBG();
		}

	}

	private void generateBG() {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		final int height = dm.heightPixels;
		final int width = dm.widthPixels;

		Bitmap mp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		mp.eraseColor(Color.WHITE);
		
		try {
			mp.compress(Bitmap.CompressFormat.PNG, 90, new FileOutputStream(
					new File("/mnt/sdcard/chopthatwordBG.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void startAnimations() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l = (LinearLayout) findViewById(R.id.activity_splash);
		l.clearAnimation();
		l.startAnimation(anim);
	}
}