package com.challenges.mindsnacks.npuzzle;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

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
		
		h_getTo_nextActivity.postDelayed(r_MainActivity, 4 * 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

	private void startAnimations() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l = (LinearLayout) findViewById(R.id.activity_splash);
		l.clearAnimation();
		l.startAnimation(anim);
	}
}