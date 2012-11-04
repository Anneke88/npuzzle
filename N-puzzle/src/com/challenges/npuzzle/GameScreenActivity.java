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

    private GameBoard board;
    private Bitmap bitmap; // temporary holder for puzzle picture
    private int screenWidth;
    private int screenHeight;
    private final int GAMESIZE = 16;    
    private final int GRIDSIZE = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//sets the orientation to portrait
        setContentView(R.layout.activity_game_screen);
        
        createGameBoard();
    }

    private void setScreenSize() {
       DisplayMetrics metrics = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(metrics);
       screenWidth = (int) (metrics.widthPixels * metrics.density);
       screenHeight = (int) (metrics.heightPixels * metrics.density);
    }

    private Bitmap resizeDrawabletoScreenSize(Drawable image) {
       setScreenSize();

       Bitmap bitmapDr = Bitmap.createScaledBitmap(((BitmapDrawable)image).getBitmap(), screenWidth, screenHeight, false);
       return bitmapDr;       
    }

    private final void createGameBoard() {
      bitmap = resizeDrawabletoScreenSize(getResources().getDrawable(R.drawable.card_hull));

      TableLayout gLayout;
      gLayout = (TableLayout)findViewById(R.id.gameLayout);    
      gLayout.removeAllViews();
      
      board = GameBoard.createGameBoard(this, bitmap, gLayout, screenWidth, screenHeight);      
      bitmap.recycle();
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