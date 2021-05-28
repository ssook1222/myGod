package com.guroome.appdev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RealOneActivity extends AppCompatActivity {

    private Button nextbt;
    private String[] menuStr = {"모카라떼", "녹차", "스콘"};
    private TextView menu;
    private int num = (int)(Math.random()*3);
    private boolean finalCheck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e(getClass().getName(),"액트 1에서는 "+String.valueOf(RealTwoActivity.countNum_now));
        SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
        SharedPreferences.Editor totalEditor = totalSave.edit();
        totalEditor.putInt("total", 0);
        totalEditor.commit();

        SharedPreferences textSave = getSharedPreferences ("textDB",0);
        SharedPreferences.Editor txtEditor = textSave.edit();
        txtEditor.putString("txt1","");
        txtEditor.putString("txt2","");
        txtEditor.putString("txt3","");
        txtEditor.commit();

        //
        SharedPreferences returnSave = getSharedPreferences ("rtDB",0);
        SharedPreferences.Editor returnEditor = returnSave.edit();
        returnEditor.putBoolean("return", false);
        returnEditor.commit();

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_real_one);

        menu=(TextView)findViewById(R.id.menu);
        menu.setText(menuStr[num]);

        nextbt=(Button)findViewById(R.id.play);
        nextbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //시작 버튼 누를 때 DB에 현재 부여한 메뉴 번호 저장
                if(num==0){
                    SharedPreferences saveData = getSharedPreferences ("menuDB",0); //메뉴 DB에
                    SharedPreferences.Editor editor = saveData.edit();
                    editor.putInt("num",1); //메뉴 번호 저장
                    editor.commit();
                }

                if(num==1){
                    SharedPreferences saveData = getSharedPreferences ("menuDB",0);
                    SharedPreferences.Editor editor = saveData.edit();
                    editor.putInt("num",2);
                    editor.commit();
                }

                if(num==2){
                    SharedPreferences saveData = getSharedPreferences ("menuDB",0);
                    SharedPreferences.Editor editor = saveData.edit();
                    editor.putInt("num",3);
                    editor.commit();
                }

                if(RealTwoActivity.countNum_now==0)
                {Intent intent = new Intent(getApplicationContext(), RealTwoActivity.class);

                /*if(RealTwoActivity.countNum_now>0){
                    SharedPreferences returnSave = getSharedPreferences ("rtDB",0);
                    SharedPreferences.Editor returnEditor = returnSave.edit();
                    returnEditor.putBoolean("return", true);
                    returnEditor.commit();
                }

                SharedPreferences rtPrf=getSharedPreferences("rtDB",0);
                boolean rt=rtPrf.getBoolean("return",true);
                Log.e(getClass().getName(),"액트1에서는 "+String.valueOf(rt));*/

                startActivity(intent);
                overridePendingTransition(0, 0);}

                else if(RealTwoActivity.countNum_now!=0){
                    Toast.makeText(getApplicationContext(), "카페 키오스크 실습 로딩 중입니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
