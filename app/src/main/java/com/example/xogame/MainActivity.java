package com.example.xogame;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int count = 1;
    private ArrayList<String> boardState = new ArrayList<>();
    //if count is odd number => the turn is for Player_1
    //if count is even number => the turn is for Player_2

    int player_1Score = 0;
    int player_2Score = 0;

    TextView player_1;
    TextView player_2;

    ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player_1 = findViewById(R.id.player1_score);
        player_2 = findViewById(R.id.player2_score);
        rootLayout = findViewById(R.id.main);
        initializeGame();
    }

    private void initializeGame(){
        if(boardState.size() < 9){
            for(int i=0 ; i<9 ; i++){
                boardState.add("");
            }
        }
        else{
            boardState.clear();
        }

        Log.e("TAG", "after initializeGame: "+ boardState);
    }

    private void updateBoardStateValue(int buttonId, String userValue){
        Log.e("TAG", "buttonId" + userValue);
        if(buttonId == R.id.button_1){
            boardState.set(0, userValue);
        }
        else if(buttonId == R.id.button_2){
            boardState.set(1, userValue);
        }
        else if(buttonId == R.id.button_3){
            boardState.set(2, userValue);
        }
        else if(buttonId == R.id.button_4){
            boardState.set(3, userValue);
        }
        else if(buttonId == R.id.button_5){
            boardState.set(4, userValue);
        }
        else if(buttonId == R.id.button_6){
            boardState.set(5, userValue);
        }
        else if(buttonId == R.id.button_7){
            boardState.set(6, userValue);
        }
        else if(buttonId == R.id.button_8){
            boardState.set(7, userValue);
        }
        else if(buttonId == R.id.button_9){
            boardState.set(8, userValue);
        }
    }

    public boolean checkWin(String playerCode){
        //check who when
        //WINNER => increment the winner score by 3
        //EQUALITY => increment the both score by 1
        Log.e("TAG", "check win: "+ boardState);

        //ROW
        for(int i=0 ; i<9; i+=3){
            if(
                    boardState.get(i).equals(playerCode) &&
                            boardState.get(i+1).equals(playerCode)&&
                            boardState.get(i+2).equals(playerCode)
            ) return true;

        }

        //Columns
        for(int i=0 ; i<3; i++){

            if(
                    boardState.get(i).equals(playerCode) &&
                            boardState.get(i+3).equals(playerCode)&&
                            boardState.get(i+6).equals(playerCode)
            ) return true;
        }

        //Diagonal
            if(
                    boardState.get(0).equals(playerCode)&&
                            boardState.get(4).equals(playerCode)&&
                            boardState.get(8).equals(playerCode)
            ) return true;

            if(
                    boardState.get(2).equals(playerCode) &&
                            boardState.get(4).equals(playerCode)&&
                            boardState.get(6).equals(playerCode)
            ) return true;

            return false;

    }

    private void resetGame(){
        count = 1;
        for (int i=0 ; i<9 ; i++){
            boardState.set(i,"");
        }
        //reset buttons text to empty string
        Button button1 = findViewById(R.id.button_1);
        button1.setText("");
        Button button2 = findViewById(R.id.button_2);
        button2.setText("");
        Button button3 = findViewById(R.id.button_3);
        button3.setText("");
        Button button4 = findViewById(R.id.button_4);
        button4.setText("");
        Button button5 = findViewById(R.id.button_5);
        button5.setText("");
        Button button6 = findViewById(R.id.button_6);
        button6.setText("");
        Button button7 = findViewById(R.id.button_7);
        button7.setText("");
        Button button8 = findViewById(R.id.button_8);
        button8.setText("");
        Button button9 = findViewById(R.id.button_9);
        button9.setText("");
    }

    public void onButtonClicked(View view) {
        if(view instanceof Button){

            Button clickedButton = (Button) view;
            Log.e("TAG", "onButtonClicked: "+ clickedButton.getId());

            if(clickedButton.getText().toString().isEmpty()) {
                Log.e("TAG", "onButtonClicked: "+ count);
                if(count % 2 == 0){ //Player_2
                    clickedButton.setText("O");
                    updateBoardStateValue(clickedButton.getId(),"O");
                }
                else{//Player_1
                    clickedButton.setText("X");
                    updateBoardStateValue(clickedButton.getId(),"X");
                }
                count++;
                Log.e("TAG", "onButtonClicked: count after increment " + count);
            }

            //check who when
            //player_1 => use X
            if(checkWin("X")){
                player_1Score += 1;
                player_1.setText(player_1Score+"");
                Toast.makeText(clickedButton.getContext(), "Player 1 X WINS!!" , Toast.LENGTH_LONG).show();
                resetGame();
            }
            //player_2 => use O
            if(checkWin("O")){
                player_2Score += 1;
                player_2.setText(player_2Score+"");
                Toast.makeText(clickedButton.getContext(), "Player 2 O WINS!!" , Toast.LENGTH_LONG).show();
                resetGame();
            }
            if(count == 9){
                Toast.makeText(clickedButton.getContext(), "Drawn" , Toast.LENGTH_LONG).show();
                resetGame();
            }
        }
    }
}