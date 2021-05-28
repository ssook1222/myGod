package com.guroome.appdev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class ThirteenActivity extends AppCompatActivity {

    private Button basic;
    private Button hot;
    private Button none;
    private Button order;
    private boolean isClicked_1 = false; //기본 눌렀는가?
    private boolean isClicked_2 = false; //핫 눌렀는가?
    private boolean isClicked_3 = false; //없음 눌렀는가?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thirteen);

        basic=(Button)findViewById(R.id.basic);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked_1=true;
                Toast.makeText(getApplicationContext(), "기본 사이즈를 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });

        hot=(Button)findViewById(R.id.hot);
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked_2=true;
                Toast.makeText(getApplicationContext(), "뜨거운 음료를 선택하셨습니다", Toast.LENGTH_SHORT).show();
            }
        });

        none=(Button)findViewById(R.id.none);
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked_3=true;
                Toast.makeText(getApplicationContext(), "샷 추가 없음을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        order=(Button)findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked_1==true&&isClicked_2==true&&isClicked_3==true)
                {Intent intent = new Intent(getApplicationContext(), FourteenActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
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
