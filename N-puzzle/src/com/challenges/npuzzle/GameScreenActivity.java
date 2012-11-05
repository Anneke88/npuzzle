package com.challenges.npuzzle;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.support.v4.app.NavUtils;

public class GameScreenActivity extends Activity {

	private Bitmap tempBitmap;
	private int screenWidth;
	private int screenHeight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_game_screen);

		// Initialize screenWidth, screenHeight
		initScreenSize();
		// Create game board for display
		createGameBoard();
	}

	private final void createGameBoard() {
		// Resize the drawable resource to the screen size and convert to Bitmap
		tempBitmap = resizeDrawabletoScreenSize(getResources().getDrawable(R.drawable.card_leviathan));

		// TableLayout has rows,columns that we can use to store our GamePieces
		TableLayout gLayout;
		gLayout = (TableLayout) findViewById(R.id.gameLayout);
		gLayout.removeAllViews();

		GameBoard.createGameBoard(this, tempBitmap, gLayout, screenWidth, screenHeight);
		tempBitmap.recycle();
	}

	// Get and set screen size width, height to use in resizing picture
	private void initScreenSize() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		this.screenWidth = (int) (metrics.widthPixels * metrics.density);
		this.screenHeight = (int) (metrics.heightPixels * metrics.density);
	}

	// Resize the drawable resource to the screen size and convert to Bitmap
	private Bitmap resizeDrawabletoScreenSize(Drawable image) {
		Bitmap tempBitmap = Bitmap.createScaledBitmap(((BitmapDrawable) image).getBitmap(), screenWidth, screenHeight, false);
		return tempBitmap;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_game_screen, menu);
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