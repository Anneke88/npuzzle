package com.challenges.npuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.widget.TextView;
// Kept for debugging
//import android.widget.Toast;

public final class GamePiece extends TextView {

	private int currentRow, currentColumn;
	private int correctRow, correctColumn;
	private Bitmap mBitmap;
	private String title;

	public GamePiece(Context context, Bitmap bitmap, int mRow, int mColumn,
			String mTitle) {
		super(context);

		super.setCursorVisible(false);
		super.setTypeface(Typeface.DEFAULT_BOLD);
		super.setTextColor(Color.RED);
		title = mTitle;
		super.setText(title);

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

	public String getTitle() {
		return title;
	}	
	
	public Bitmap getBitmap() {
		return mBitmap;
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

	public void setTitle(String xtitle) {
		this.title = xtitle;
		super.setText(this.title);
	}

	// Set background of the current GamePiece
	public void setBG(Bitmap bm) {
		super.setBackgroundDrawable(new BitmapDrawable(bm));
	}
	
	// Set new position(row, column)
	public void setCurrent(int row, int column) {
		this.setCurrentRow(row);
		this.setCurrentColumn(column);
	}
	
	// Set new properties 
	public void setCurrent(GamePiece gp) {
		this.setBG(gp.getBitmap());
//		super.setBackgroundDrawable(new BitmapDrawable(gp.getBitmap()));
		setCurrentRow(gp.getCurrentRow());
		setCurrentColumn(gp.getCurrentColumn());
		this.setTitle(gp.getTitle());
	}

	// Am I next to the GamePiece gp?
	public boolean isNextTo(GamePiece gp) {
		return ((gp.getCurrentRow() == currentRow && (gp.getCurrentColumn() == currentColumn + 1 || gp
				.getCurrentColumn() == currentColumn - 1)) || (gp
				.getCurrentColumn() == currentColumn && (gp.getCurrentRow() == currentRow + 1 || gp
				.getCurrentRow() == currentRow - 1)));
	}

	// Trigger the animations and get the game state updated
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// DEBUG
		/*Toast.makeText(this.getContext(), 
                this.getCurrentColumn()+"/"+this.getCurrentRow(), Toast.LENGTH_SHORT).show();*/
		GameBoard.updateGameStateSignal(this);
		return super.onTouchEvent(event);
	}
}