package com.challenges.npuzzle;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
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
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		startAnimations();
		// Go to MainActivity with a 2sec delay
		// 2 sec delay is a place holder. SplashActivity and Animations can be used to load/prepare data without boring the user.
		// Done to gain some time before moving to the MainActivity
		h_getTo_nextActivity.postDelayed(r_MainActivity, 2 * 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

	// A slow fade-in animation
	private void startAnimations() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l = (LinearLayout) findViewById(R.id.activity_splash);
		l.clearAnimation();
		l.startAnimation(anim);
	}
}