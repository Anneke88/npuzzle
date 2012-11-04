package com.challenges.npuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.widget.TextView;

public final class GamePiece extends TextView {

	private int currentRow, currentColumn;
	private int correctRow, correctColumn;
	private Bitmap mBitmap;

	public GamePiece(Context context, Bitmap bitmap, int mRow, int mColumn,String mTitle) {
		super(context);

		super.setCursorVisible(false);
		super.setTypeface(Typeface.DEFAULT_BOLD);
		super.setTextColor(Color.RED);
		super.setText(mTitle);

		this.mBitmap = bitmap;
		super.setBackgroundDrawable(new BitmapDrawable(mBitmap));

		this.currentRow = mRow;
		this.currentColumn = mColumn;
		this.correctRow = mRow;
		this.correctColumn = mColumn;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	public int getCorrectRow() {
		return correctRow;
	}

	public int getCorrectColumn() {
		return correctColumn;
	}

	public void setCurrentRow(int row) {
		this.currentRow = row;
	}

	public void setCurrentColumn(int column) {
		this.currentColumn = column;
	}

	public void setCorrectRow(int row) {
		this.correctRow = row;
	}

	public void setCorrectColumn(int column) {
		this.correctColumn = column;
	}

	public Bitmap getBitmap() {
		return this.mBitmap;
	}
	
	public void setBG(Bitmap bm) {
		super.setBackgroundDrawable(new BitmapDrawable(bm));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
}