package com.guroome.appdev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nextbt;
    private Button linebt1;
    private Button linebt2;
    private Button linebt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int saveView = R.layout.activity_main;
        SharedPreferences preferences =getSharedPreferences("save",0);
        saveView=preferences.getInt("saveLayout",0);
        if(saveView==R.layout.activity_third)
        {
            Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        nextbt=(Button)findViewById(R.id.next);
        nextbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NextActivity.class);
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

    @Override
    public void onBackPressed(){
        AlertDialog.Builder goorumBulider = new AlertDialog.Builder(this);
        goorumBulider.setMessage("공부를 그만하시겠습니까?");

        goorumBulider.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        goorumBulider.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        goorumBulider.setTitle("구름이 종료");
        goorumBulider.show();
    }
}
