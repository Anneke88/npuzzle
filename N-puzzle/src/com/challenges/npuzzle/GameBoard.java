package com.challenges.npuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;

public final class GameBoard {
	private static GameBoard board = null;
	private Bitmap bitmap;
	private Context context;
	private GamePiece emptyGamePiece;
	private TableLayout gameLayout;
	private List<GamePiece> gamePieces = null;
	private List<TableRow> tableRow = null;
	private int numberOfMoves;
	private int boardWidth;
	private int boardHeight;
	// The game is set as a 15-piece puzzle
	private final int GAMESIZE = 16;
	private final int GRIDSIZE = 4;

	private GameBoard(Context context, Bitmap bitmap, TableLayout gLayout, 	int width, int height) {
		this.context = context;
		this.boardWidth = width;
		this.boardHeight = height;
		this.bitmap = Bitmap.createScaledBitmap(bitmap, this.boardWidth,
				this.boardHeight, true);
		this.numberOfMoves = 0;
		this.gameLayout = gLayout;
		init();
	}

	public static void createGameBoard(Context context, Bitmap bitmap,
			TableLayout gLayout, int width, int height) {
		board = new GameBoard(context, bitmap, gLayout, width, height);
	}

	private void init() {
		initializeLists();
		createGamePieces();
		addToGameScreen();
	}

	// To avoid ugly bugs
	private void initializeLists() {
		if (gamePieces == null) {
			gamePieces = new ArrayList<GamePiece>(GAMESIZE);
		} else {
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

	// Chop the original bitmap into tiny little pieces and assign them to the correct GamePiece
	private void createGamePieces() {
		int gamePiece_width = bitmap.getWidth() / GRIDSIZE;
		int gamePiece_height = bitmap.getHeight() / GRIDSIZE;

		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				Bitmap bm = Bitmap.createBitmap(bitmap, column
						* gamePiece_width, row * gamePiece_height,
						gamePiece_width, gamePiece_height);

				// Get the latest piece of picture piece, erase it black
				// This is the emptyGamePiece and add it to the end of the List<GamePiece>
				if ((row == GRIDSIZE - 1) && (column == GRIDSIZE - 1)) {
					bm = Bitmap.createBitmap(gamePiece_width, gamePiece_height,
							bm.getConfig());
					bm.eraseColor(Color.BLACK);
					emptyGamePiece = new GamePiece(context, bm, row, column,
							"EMPTY");
					gamePieces.add(emptyGamePiece);
				} else {
					// Regular gamePieces
					GamePiece tempGamePiece = new GamePiece(context, bm, row,
							column, row + "-" + column);
					gamePieces.add(tempGamePiece);
				}
			} // end column
		}// end row
	}

	// Add gamePieces to the tableRow, and add tableRow to the gameLayout for display
	public void addToGameScreen() {
		Iterator<GamePiece> it = (shuffleGamePieces()).iterator();
		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				tableRow.get(row).addView(it.next());
			} // end column
			gameLayout.addView(tableRow.get(row));
		} // end row
	}

	// Shuffle the gamePieces before being added to the GameBoard for display
	public List<GamePiece> shuffleGamePieces() {

		Collections.shuffle(gamePieces);
		gamePieces.remove(emptyGamePiece);
		gamePieces.add(emptyGamePiece);

		for (int row = 0; row < GRIDSIZE; row++) {
			for (int column = 0; column < GRIDSIZE; column++) {
				gamePieces.get(GRIDSIZE * row + column).setCurrent(row, column);
			}
		}

		numberOfMoves = 0;
		return gamePieces;
	}

	// Pickup the ontouchevent signal from GamePiece and pass it on
	public static void updateGameStateSignal(GamePiece gp) {
		board.updateGameState(gp);
	}

	// Update game state and play proper animation
	private void updateGameState(GamePiece gp) {

		GamePiece emptyPiece = emptyGamePiece;

		// Check if the incoming GamePiece is next to a emptyGamePiece
		if (gp.isNextTo(emptyGamePiece)) {
			// TRANSITION ANIMATIONS
			if (gp.getCurrentColumn() < emptyGamePiece.getCurrentColumn()) {
				emptyGamePiece.bringToFront();
				// LEFT
				emptyGamePiece.startAnimation(AnimationUtils.loadAnimation(
						this.context, R.anim.left_animation));
			} else if (gp.getCurrentColumn() > emptyGamePiece
					.getCurrentColumn()) {
				emptyGamePiece.bringToFront();
				// RIGHT
				emptyGamePiece.startAnimation(AnimationUtils.loadAnimation(
						this.context, R.anim.right_animation));
			} else if (gp.getCurrentRow() < emptyGamePiece.getCurrentRow()) {
				emptyGamePiece.bringToFront();
				// UP
				emptyGamePiece.startAnimation(AnimationUtils.loadAnimation(
						this.context, R.anim.up_animation));
			} else if (gp.getCurrentRow() > emptyGamePiece.getCurrentRow()) {
				emptyGamePiece.bringToFront();
				// DOWN
				emptyGamePiece.startAnimation(AnimationUtils.loadAnimation(
						this.context, R.anim.down_animation));
			}

			// TODO Doesnt work completely. Tiles move properly but BGs dont match
			// emptyGamePiece needs to maintain blackBG and emptyGamePiece state but switch with GamePiece
			emptyGamePiece = gp;
			emptyGamePiece.setCurrent(gp);
			// GamePiece needs to switch with emptyGamePiece
			gp = emptyPiece;
			gp.setCurrent(emptyPiece);

			numberOfMoves++;
		}
	}	
	
	// For score keeping purposes
	public int getnumberOfMoves() {
		return numberOfMoves;
	}

	public void setNumberOfMoves(int numberOfMoves) {
		this.numberOfMoves = numberOfMoves;
	}
}