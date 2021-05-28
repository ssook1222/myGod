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

public class TenActivity extends AppCompatActivity {

    private Button next;
    private Button dict; //수정필요

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ten);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ElevenActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        dict=(Button)findViewById(R.id.dictionary);
        dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences saveData = getSharedPreferences ("DictSave",0);
                SharedPreferences.Editor editor = saveData.edit();
                editor.putInt("num",10);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), SeventeenActivity.class);
                startActivity(intent);
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
