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
import android.widget.Toast;

public class ElevenActivity extends AppCompatActivity {

    private Button buy;
    private Button dict; //수정필요
    private Button capu;
    private boolean isClicked = false; //카푸치노 눌렀는가?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_eleven);
        dict=(Button)findViewById(R.id.dictionary);
        dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences saveData = getSharedPreferences ("DictSave",0);
                SharedPreferences.Editor editor = saveData.edit();
                editor.putInt("num",11);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), SeventeenActivity.class);
                startActivity(intent);
            }
        });

        capu=(Button)findViewById(R.id.capu);
        capu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked=true;
                Toast.makeText(getApplicationContext(), "카푸치노를 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });
        buy=(Button)findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked==true)
                {Intent intent = new Intent(getApplicationContext(), ThirteenActivity.class);
                startActivity(intent);
                }
            }
        });

    }

    public void onBackPressed(){
        AlertDialog.Builder goorumBulider = new AlertDialog.Builder(this);
        goorumBulider.setMessage("공부를 그만하시겠습니까?");

        goorumBulider.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                onDestroy();
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
