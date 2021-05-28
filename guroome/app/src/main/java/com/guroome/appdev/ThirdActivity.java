package com.guroome.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private Button nextbt;
    private Button linebt1;
    private Button linebt2;
    private Button linebt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_third);

        nextbt=(Button)findViewById(R.id.next);
        nextbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences saveData = getSharedPreferences ("save",0);
                SharedPreferences.Editor editor = saveData.edit();
                editor.putInt("saveLayout",R.layout.activity_third);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        linebt1=(Button)findViewById(R.id.shape);
        linebt2=(Button)findViewById(R.id.shape2);
        linebt3=(Button)findViewById(R.id.shape3);

        linebt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        linebt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NextActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        linebt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }
}
