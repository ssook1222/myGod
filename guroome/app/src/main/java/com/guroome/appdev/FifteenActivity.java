package com.guroome.appdev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class FifteenActivity extends AppCompatActivity {

    private Button takeOut;
    private Button here;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fifteen);

        takeOut=(Button)findViewById(R.id.takeOut);
        here=(Button)findViewById(R.id.here);

        takeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SixteenActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
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
