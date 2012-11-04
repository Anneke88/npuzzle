package com.challenges.npuzzle;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import com.challenges.npuzzle.R;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startGame(View v) {
		Intent myIntent = new Intent(MainActivity.this, GameScreenActivity.class);
		MainActivity.this.startActivity(myIntent);
	}

	// Main -- > Help
	public void explainControls(View v) {
		Context context = getApplicationContext();
		CharSequence text = "Shake to shuffle the board. Tilt the phone to move the pieces. You can also touch to slide.";
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	// Main -- > About
	public void explainGame(View v) {
		Context context = getApplicationContext();
		CharSequence text = "Move the puzzle pieces to complete the big picture. This game will help you remember the spelling of difficult words.";
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}