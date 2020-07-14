package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class friend_helper extends AppCompatActivity implements View.OnClickListener {

    private EditText name, friendname, select;
    private Button Click;
    private ImageView power;
    private MediaPlayer mediaPlayer, mediaPlayer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_helper);

        mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.intent);
        mediaPlayer1= MediaPlayer.create(getApplicationContext(), R.raw.fairy_tale_indrotuctory);
        mediaPlayer1.start();

        power= (ImageView) findViewById(R.id.power_Id);
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        name= (EditText) findViewById(R.id.name);
        friendname= (EditText) findViewById(R.id.friend_name);

        Click= (Button) findViewById(R.id.clickId);

        Click.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clickId:
                mediaPlayer.start();
                Thread t1= new Thread();
                try {
                    t1.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (name.getText().toString().length()==0 || friendname.getText().toString().length()==0)
                    Toast.makeText(this, "Fill required fields!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), friend.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("friendName", friendname.getText().toString());
                    startActivity(intent);
                    finish();
                }
        }
    }
}