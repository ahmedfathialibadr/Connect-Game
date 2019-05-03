package com.example.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = yellow and 1 = red
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};  // 2 means unplayed
    int[][] winningPositions = {{2,1,0},{5,4,3},{8,7,6},{2,5,8},{1,4,7},{0,3,6},{2,4,6},{0,4,8}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;


      int tappedCounter = Integer.parseInt(counter.getTag().toString());

      if(gameState[tappedCounter]==2&&gameIsActive) {
          gameState[tappedCounter]=activePlayer;
          counter.setTranslationY(-1000);
          if (activePlayer == 0) {
              counter.setImageResource(R.drawable.yellow);
              activePlayer = 1;
          } else {
              counter.setImageResource(R.drawable.red);
              activePlayer = 0;
          }
          counter.animate().translationYBy(1000).rotation(360).setDuration(300);
    for(int[] winningPosition : winningPositions){
        if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                &&gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                    gameState[winningPosition[0]]!=2){
            gameIsActive = false;
            String winner = "red";
            if(gameState[winningPosition[0]]==0){
                winner = "yellow";
            }
            TextView winnerMessage = findViewById(R.id.winnerMessage);
            winnerMessage.setText(winner + " has Won!");
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.VISIBLE);
        }else {
            boolean gameIsOver = true;
            for(int counterState : gameState){
                if(counterState==2)gameIsOver=false;
            }
         if(gameIsOver){
             TextView winnerMessage = findViewById(R.id.winnerMessage);
             winnerMessage.setText("its a Draw !");
             LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
             layout.setVisibility(View.VISIBLE);
         }
        }
    }
      }
    }
  public void playAgain(View view){
        gameIsActive = true;
      LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
      layout.setVisibility(View.INVISIBLE);
      activePlayer = 0;
      for(int i =0;i < gameState.length;i++){
          gameState[i]=2;
      }
      GridLayout layout1 = findViewById(R.id.gridLayout);
      for(int i=0; i<layout1.getChildCount();i++){
          ((ImageView)layout1.getChildAt(i)).setImageResource(0);
      }

  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
