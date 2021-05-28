package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // 0 = O
    // 1 = X
    // 2 = NULL
    boolean gameActive=true;
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    // Possible winning positions
    int [][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void gameReset(View view)
    {
        gameActive=true;
        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status= findViewById(R.id.status);
        status.setText("O's Turn | Tap to Play");


    }

    public void playerTap(View view)    // passing view of type View to the playerTap func
    {
        ImageView img= (ImageView) view;    // created img of type ImageView and stored typecast view
        int tappedImage= Integer.parseInt(img.getTag().toString());    // retrieving tag of img
        if(!gameActive) {
            gameReset(view);
        }
        else if (gameState[tappedImage]==2)
        {
            img.setTranslationY(-1000f);
            gameState[tappedImage]=activePlayer;
            if(activePlayer==0)
            {
                activePlayer=1;
                img.setImageResource(R.drawable.circle);
                TextView status= findViewById(R.id.status);
                status.setText("X's Turn | Tap to Play");
            }
            else
            {
                activePlayer=0;
                img.setImageResource(R.drawable.cross);
                TextView status= findViewById(R.id.status);
                status.setText("O's Turn | Tap to Play");

            }
            img.animate().translationYBy(1000f).setDuration(300);

            for(int [] winP: winPos)
            {
                if(gameState[winP[0]] == gameState[winP[1]] && gameState[winP[1]] == gameState[winP[2]] && gameState[winP[0]]!=2)
                {
                    String winner;
                    if (gameState[winP[0]]==0) winner="O is the Winner";
                    else winner="X is the Winner";
                    TextView status= findViewById(R.id.status);
                    status.setText(winner);
                    gameActive=false;
                }
            }
            boolean flag=true;
            for(int i=0; i<gameState.length; i++) {
                if (gameState[i] == 2) {
                    flag = false;
                    break;
                }
            }
                if(flag && gameActive)
                {
                    gameActive=false;
                    String draw="The Game is Draw";
                    TextView status= findViewById(R.id.status);
                    status.setText(draw);
                }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}