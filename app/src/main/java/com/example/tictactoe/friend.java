package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class friend extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

        private boolean player1Turn = true;

        private MediaPlayer mediaPlayer, mediaPlayer1;

        private ImageView power, reset;

        private boolean isReset= false;

        private int roundCount;

        private int player1Points;
        private int player2Points;

        private TextView textViewPlayer1, GoBack;
        private TextView textViewPlayer2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_friend);

            mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.termination);
            mediaPlayer1= MediaPlayer.create(getApplicationContext(), R.raw.button_click);

            textViewPlayer1 = findViewById(R.id.p1);
            textViewPlayer2 = findViewById(R.id.friendId);
            GoBack=(TextView) findViewById(R.id.gobackId);

            GoBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), friend_helper.class));
                    finish();
                }
            });

            power= (ImageView) findViewById(R.id.power);
            reset= (ImageView) findViewById(R.id.reset);

            power.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer1.start();
                    finish();
                    System.exit(0);
                }
            });

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer1.start();
                    buttons[0][0].setText(""); buttons[0][1].setText(""); buttons[0][2].setText("");
                    buttons[1][0].setText(""); buttons[1][1].setText(""); buttons[1][2].setText("");
                    buttons[2][0].setText(""); buttons[2][1].setText(""); buttons[2][2].setText("");
                    isReset=true;
                    textViewPlayer1.setText("");
                    textViewPlayer2.setText("");
                }
            });

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String buttonID = "button_" + i + j;
                    int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                    buttons[i][j] = findViewById(resID);
                    buttons[i][j].setOnClickListener(this);
                }
            }

            //  Button buttonReset = findViewById(R.id.button_reset);
        }

        @Override
        public void onClick(View v) {
            mediaPlayer1.start();
            if (!((Button) v).getText().toString().equals("")) {
                return;
            }

            if (player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }

            roundCount++;

            if (checkForWin()) {
                if (player1Turn) {
                        player1Wins();
                } else {
                        player2Wins();
                }
            } else if (roundCount == 9) {
                draw();
            } else {
                player1Turn = !player1Turn;
            }

        }

        private boolean checkForWin() {
            String[][] field = new String[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = buttons[i][j].getText().toString();
                }
            }

            for (int i = 0; i < 3; i++) {
                if (field[i][0].equals(field[i][1])
                        && field[i][0].equals(field[i][2])
                        && !field[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < 3; i++) {
                if (field[0][i].equals(field[1][i])
                        && field[0][i].equals(field[2][i])
                        && !field[0][i].equals("")) {
                    return true;
                }
            }

            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")) {
                return true;
            }

            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")) {
                return true;
            }

            return false;
        }

        private void player1Wins() {
            Intent intent= getIntent();
            String player1= intent.getStringExtra("name");
            mediaPlayer.start();
            textViewPlayer1.setText(player1 + " " + "wins");
        }

        private void player2Wins() {
            Intent intent= getIntent();
            String player11= intent.getStringExtra("friendName");
            mediaPlayer.start();
            textViewPlayer2.setText(player11 + " " + "wins");
        }

        private void draw() {
            mediaPlayer.start();
        }
    }