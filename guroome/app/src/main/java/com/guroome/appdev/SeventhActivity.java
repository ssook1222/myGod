package com.guroome.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SeventhActivity extends AppCompatActivity {

    private Button play;
    private Button cafe;
    private Button fastfood;
    private Button movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seventh);

        play=(Button)findViewById(R.id.play);
        cafe=(Button)findViewById(R.id.coffee);
        fastfood=(Button)findViewById(R.id.fastfood);
        movie=(Button)findViewById(R.id.movie);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeventhActivity.class);
                startActivity(intent);
            }
        });

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FifthActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        fastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SixthActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
