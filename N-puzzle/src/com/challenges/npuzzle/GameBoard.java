package com.challenges.npuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;

public final class GameBoard {
	private static GameBoard board = null;
	private Bitmap bitmap;
	private Context context;
	private List<GamePiece> gamePieces = null;
	private GamePiece emptyGamePiece;
	private TableLayout gameLayout;
	private List<TableRow> tableRow = null;
	private int numberOfMoves;
	private int boardWidth;
	private int boardHeight;
	private final int GAMESIZE = 16;
	private final int GRIDSIZE = 4;

	private GameBoard(Context context, Bitmap bitmap, TableLayout gLayout,
			int width, int height) {
		this.context = context;
		this.boardWidth = width;
		this.boardHeight = height;
		this.bitmap = Bitmap.createScaledBitmap(bitmap, this.boardWidth,
				this.boardHeight, true);
		this.numberOfMoves = 0;
		this.gameLayout = gLayout;
		init();
	}

	public static GameBoard createGameBoard(Context context, Bitmap bitmap,
			TableLayout gLayout, int width, int height) {
		board = new GameBoard(context, bitmap, gLayout, width, height);
		return board;
	}

	private void init() {
		initializeLists();
		createGamePieces();
//		shuffleGamePieces();
	}

	private void initializeLists() {
		if (gamePieces == null) {
			gamePieces = new ArrayList<GamePiece>(GAMESIZE);
		} else {
			// Be sure to clean up old tiles
			for (int i = 0; i < gamePieces.size(); i++) {
				gamePieces.get(i).getBitmap().recycle();
				gamePieces = new ArrayList<GamePiece>(GAMESIZE);
			}
		}

		tableRow = new ArrayList<TableRow>(GRIDSIZE);

		for (int row = 0; row < GRIDSIZE; row++) {
			tableRow.add(new TableRow(context));
		}
	}

	private void createGamePieces() {
		int gamePiece_width = bitmap.getWidth() / GRIDSIZE;
		int gamePiece_height = bitmap.getHeight() / GRIDSIZE;

		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				Bitmap bm = Bitmap.createBitmap(bitmap, column
						* gamePiece_width, row * gamePiece_height,
						gamePiece_width, gamePiece_height);

				if ((row == GRIDSIZE - 1) && (column == GRIDSIZE - 1)) {
					bm = Bitmap.createBitmap(gamePiece_width, gamePiece_height,
							bm.getConfig());
					bm.eraseColor(Color.BLACK);
					emptyGamePiece = new GamePiece(context, bm, row, column, "");
					gamePieces.add(emptyGamePiece);
				} else {
					GamePiece tempGamePiece = new GamePiece(context, bm, row,
							column, row + "-" + column);
					gamePieces.add(tempGamePiece);
				}
			} // end column
		}// end row
		 addToGameScreen(); 
	}

	public void addToGameScreen() {		
		Iterator<GamePiece> it = (shuffleGamePieces()).iterator();
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				tableRow.get(row).addView(it.next());
			} // end column
			gameLayout.addView(tableRow.get(row));			
		} // end row
	}

	public List<GamePiece> shuffleGamePieces() {

		Collections.shuffle(gamePieces);
		gamePieces.remove(emptyGamePiece);
		gamePieces.add(emptyGamePiece);

		numberOfMoves = 0;
		
		return gamePieces;
	}

	public int getnumberOfMoves() {
		return numberOfMoves;
	}
}