package com.guroome.appdev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RealTwoActivity extends AppCompatActivity {
    private Button coffee;
    private Button pay;
    private Button tea;
    private Button smoo;
    private Button a;
    private Button bottle;
    private Button bake;
    private Button cof_1;
    private Button cof_2;
    private Button cof_3;
    private Button cof_4;
    private Button graynext_r;
    private Button alldelete;

    //불러와야 할 값
    public String saveText1="";
    public String saveText2="";
    public String saveText3="";

    private boolean cof1=false;
    private boolean cof2=false;
    private boolean cof3=false;
    private boolean cof4=false;

    private boolean checkIt=false;

    private TextView cost;
    private TextView menuText1;
    private TextView menuText2;
    private TextView menuText3;
    public int costSum=0; //금액 합계
    private int[] menuCost={2000,1000,2000,1500};
    private String[] menuName={"에스프레소", "아메리카노", "모카라떼", "카페라떼"};

    public CountDownTimer countDownTimer;
    public static long countNum_now=0;
    private long countNum=90000;
    final private int countDownInterval=1000;
    public static boolean rt=false;

    public void countDownTimer(){ //카운트 수
        SharedPreferences rtPrf=getSharedPreferences("rtDB",0);
        rt=rtPrf.getBoolean("return",false);
        Log.e(getClass().getName(),"액트2에서는 "+String.valueOf(rt));
        //한 바퀴 돌고 왔을 때
            countDownTimer = new CountDownTimer(countNum, countDownInterval) { //새로 생성하고
                @Override
                public void onTick(long sec) { //1초마다 계속 줄어듦
                    countNum_now = sec / 1000; //현재 카운트 다운 숫자 -> 종료까지 남은 시간
                    Log.e(getClass().getName(), "Act2 " + String.valueOf(countNum_now));
                }

                public void onFinish() { //시간 다 된 경우 여기서의 실패는 그냥 실패임.
                    Intent checkIntent = getIntent();
                    boolean checkIt = checkIntent.getBooleanExtra("check", false);
                    if (countNum_now == 0 && checkIt == false && rt == false) {
                        Toast.makeText(getApplicationContext(), "주문에 실패 하였습니다.", Toast.LENGTH_SHORT).show();//실패 로그 뜨게 함.
                        Intent intent = new Intent(getApplicationContext(), FourthActivity.class); //첫 액티비티로 이동
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }
            }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_real_two);

        //load code
        SharedPreferences TotalPreferences=getSharedPreferences("totalDB",0);
        costSum=TotalPreferences.getInt("total",0);

        if(checkIt==true){
            Log.e(getClass().getName(),String.valueOf(countNum));
        }

        menuText1=(TextView)findViewById(R.id.menuText1);
        menuText2=(TextView)findViewById(R.id.menuText2);
        menuText3=(TextView)findViewById(R.id.menuText3);

        cost=(TextView)findViewById(R.id.cost);
        cost.setText(Integer.toString(costSum));

        SharedPreferences TxtPreferences=getSharedPreferences("textDB",0);
        saveText1=TxtPreferences.getString("txt1","");
        saveText2=TxtPreferences.getString("txt2","");
        saveText3=TxtPreferences.getString("txt3","");

        if(saveText1!=null){menuText1.setText(saveText1); }
        if(saveText2!=null){menuText2.setText(saveText2); }
        if(saveText3!=null){menuText3.setText(saveText3); }

        countDownTimer();

        cost=(TextView)findViewById(R.id.cost);
        cost.setText(Integer.toString(costSum));

        menuText1=(TextView)findViewById(R.id.menuText1);
        menuText2=(TextView)findViewById(R.id.menuText2);
        menuText3=(TextView)findViewById(R.id.menuText3);

        pay=(Button)findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),paymentActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        coffee=(Button)findViewById(R.id.coffee);
        tea=(Button)findViewById(R.id.tea);
        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //값 초기화 후 일단 저장
                    SharedPreferences costSave = getSharedPreferences ("costDB",0);
                    SharedPreferences.Editor costEditor = costSave.edit();
                    costEditor.putInt("cof1", 0);
                    costEditor.putInt("cof2", 0);
                    costEditor.putInt("cof3", 0);
                    costEditor.putInt("cof4", 0);
                    costEditor.commit();

                    SharedPreferences nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    SharedPreferences.Editor nameEditor = nameSave.edit();
                    nameEditor.putString("cof1","");
                    nameEditor.putString("cof2","");
                    nameEditor.putString("cof3","");
                    nameEditor.putString("cof4","");
                    nameEditor.commit();

                    //가격 저장
                    if(cof1==true)
                    {   costSave = getSharedPreferences ("costDB",0);
                        costEditor = costSave.edit();
                        costEditor.putInt("cof1", menuCost[0]); //가격 저장
                        costEditor.commit();

                        nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                        nameEditor = nameSave.edit();
                        nameEditor.putString("cof1",menuName[0]);
                        nameEditor.commit();

                    }
                    else if(cof2==true){
                        costSave = getSharedPreferences ("costDB",0);
                        costEditor = costSave.edit();
                        costEditor.putInt("cof2", menuCost[1]); //가격 저장
                        costEditor.commit();

                        nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                        nameEditor = nameSave.edit();
                        nameEditor.putString("cof2",menuName[1]);
                        nameEditor.commit();
                    }
                    else if(cof3==true){
                        costSave = getSharedPreferences ("costDB",0);
                        costEditor = costSave.edit();
                        costEditor.putInt("cof3", menuCost[2]); //가격 저장
                        costEditor.commit();

                        nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                        nameEditor = nameSave.edit();
                        nameEditor.putString("cof3",menuName[2]);
                    nameEditor.commit();
                }
                else if(cof4==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof4", menuCost[3]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof4",menuName[3]);
                    nameEditor.commit();
                }

                // 총 가격 저장
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum); //총 가격 저장
                totalEditor.commit();

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1",menuText1.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt2",menuText2.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt3",menuText3.getText().toString());
                txtEditor.commit();

                Intent intent = new Intent(getApplicationContext(), RealFourActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        smoo=(Button)findViewById(R.id.smoo);
        smoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //값 초기화 후 일단 저장
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof1", 0);
                costEditor.putInt("cof2", 0);
                costEditor.putInt("cof3", 0);
                costEditor.putInt("cof4", 0);
                costEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                SharedPreferences.Editor nameEditor = nameSave.edit();
                nameEditor.putString("cof1","");
                nameEditor.putString("cof2","");
                nameEditor.putString("cof3","");
                nameEditor.putString("cof4","");
                nameEditor.commit();

                //가격 저장
                if(cof1==true)
                {   costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof1", menuCost[0]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof1",menuName[0]);
                    nameEditor.commit();

                }
                else if(cof2==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof2", menuCost[1]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof2",menuName[1]);
                    nameEditor.commit();
                }
                else if(cof3==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof3", menuCost[2]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof3",menuName[2]);
                    nameEditor.commit();
                }
                else if(cof4==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof4", menuCost[3]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof4",menuName[3]);
                    nameEditor.commit();
                }

                // 총 가격 저장
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum); //총 가격 저장
                totalEditor.commit();

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1",menuText1.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt2",menuText2.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt3",menuText3.getText().toString());
                txtEditor.commit();

                Intent intent = new Intent(getApplicationContext(), RealSixActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        a=(Button)findViewById(R.id.ade);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //값 초기화 후 일단 저장
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof1", 0);
                costEditor.putInt("cof2", 0);
                costEditor.putInt("cof3", 0);
                costEditor.putInt("cof4", 0);
                costEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                SharedPreferences.Editor nameEditor = nameSave.edit();
                nameEditor.putString("cof1","");
                nameEditor.putString("cof2","");
                nameEditor.putString("cof3","");
                nameEditor.putString("cof4","");
                nameEditor.commit();

                //가격 저장
                if(cof1==true)
                {   costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof1", menuCost[0]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof1",menuName[0]);
                    nameEditor.commit();

                }
                else if(cof2==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof2", menuCost[1]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof2",menuName[1]);
                    nameEditor.commit();
                }
                else if(cof3==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof3", menuCost[2]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof3",menuName[2]);
                    nameEditor.commit();
                }
                else if(cof4==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof4", menuCost[3]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof4",menuName[3]);
                    nameEditor.commit();
                }

                // 총 가격 저장
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum); //총 가격 저장
                totalEditor.commit();

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1",menuText1.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt2",menuText2.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt3",menuText3.getText().toString());
                txtEditor.commit();

                Intent intent = new Intent(getApplicationContext(), RealSevenActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        bottle=(Button)findViewById(R.id.bottle);
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //값 초기화 후 일단 저장
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof1", 0);
                costEditor.putInt("cof2", 0);
                costEditor.putInt("cof3", 0);
                costEditor.putInt("cof4", 0);
                costEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                SharedPreferences.Editor nameEditor = nameSave.edit();
                nameEditor.putString("cof1","");
                nameEditor.putString("cof2","");
                nameEditor.putString("cof3","");
                nameEditor.putString("cof4","");
                nameEditor.commit();

                //가격 저장
                if(cof1==true)
                {   costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof1", menuCost[0]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof1",menuName[0]);
                    nameEditor.commit();

                }
                else if(cof2==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof2", menuCost[1]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof2",menuName[1]);
                    nameEditor.commit();
                }
                else if(cof3==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof3", menuCost[2]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof3",menuName[2]);
                    nameEditor.commit();
                }
                else if(cof4==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof4", menuCost[3]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof4",menuName[3]);
                    nameEditor.commit();
                }

                // 총 가격 저장
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum); //총 가격 저장
                totalEditor.commit();

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1",menuText1.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt2",menuText2.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt3",menuText3.getText().toString());
                txtEditor.commit();

                Intent intent = new Intent(getApplicationContext(), RealEightActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        bake=(Button)findViewById(R.id.bakery);
        bake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //값 초기화 후 일단 저장
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof1", 0);
                costEditor.putInt("cof2", 0);
                costEditor.putInt("cof3", 0);
                costEditor.putInt("cof4", 0);
                costEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                SharedPreferences.Editor nameEditor = nameSave.edit();
                nameEditor.putString("cof1","");
                nameEditor.putString("cof2","");
                nameEditor.putString("cof3","");
                nameEditor.putString("cof4","");
                nameEditor.commit();

                //가격 저장
                if(cof1==true)
                {   costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof1", menuCost[0]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof1",menuName[0]);
                    nameEditor.commit();

                }
                else if(cof2==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof2", menuCost[1]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof2",menuName[1]);
                    nameEditor.commit();
                }
                else if(cof3==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof3", menuCost[2]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof3",menuName[2]);
                    nameEditor.commit();
                }
                else if(cof4==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof4", menuCost[3]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof4",menuName[3]);
                    nameEditor.commit();
                }

                // 총 가격 저장
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum); //총 가격 저장
                totalEditor.commit();

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1",menuText1.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt2",menuText2.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt3",menuText3.getText().toString());
                txtEditor.commit();

                Intent intent = new Intent(getApplicationContext(), RealTenActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });


        graynext_r=(Button)findViewById(R.id.graynext_r);
        graynext_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //값 초기화 후 일단 저장
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof1", 0);
                costEditor.putInt("cof2", 0);
                costEditor.putInt("cof3", 0);
                costEditor.putInt("cof4", 0);
                costEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                SharedPreferences.Editor nameEditor = nameSave.edit();
                nameEditor.putString("cof1","");
                nameEditor.putString("cof2","");
                nameEditor.putString("cof3","");
                nameEditor.putString("cof4","");
                nameEditor.commit();

                //가격 저장
                if(cof1==true)
                {   costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof1", menuCost[0]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof1",menuName[0]);
                    nameEditor.commit();
                }
                else if(cof2==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof2", menuCost[1]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof2",menuName[1]);
                    nameEditor.commit();
                }
                else if(cof3==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof3", menuCost[2]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof3",menuName[2]);
                    nameEditor.commit();
                }
                else if(cof4==true){
                    costSave = getSharedPreferences ("costDB",0);
                    costEditor = costSave.edit();
                    costEditor.putInt("cof4", menuCost[3]); //가격 저장
                    costEditor.commit();

                    nameSave = getSharedPreferences ("nameDB",0); //초 DB에
                    nameEditor = nameSave.edit();
                    nameEditor.putString("cof4",menuName[3]);
                    nameEditor.commit();
                }

                // 총 가격 저장
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum); //총 가격 저장
                totalEditor.commit();

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1",menuText1.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt2",menuText2.getText().toString());
                txtEditor.commit();

                textSave = getSharedPreferences ("textDB",0);
                txtEditor = textSave.edit();
                txtEditor.putString("txt3",menuText3.getText().toString());
                txtEditor.commit();

                Intent intent = new Intent(getApplicationContext(), RealThreeActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        alldelete=(Button)findViewById(R.id.alldelete);
        alldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costSum=0;
                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", 0);
                totalEditor.commit();
                cost.setText(Integer.toString(0));

                //장바구니
                SharedPreferences textSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor txtEditor = textSave.edit();
                txtEditor.putString("txt1","");
                Log.e(getClass().getName(),"allDel "+saveText1);
                txtEditor.putString("txt2","");
                txtEditor.putString("txt3","");
                txtEditor.commit();
                menuText1.setText("");
                menuText2.setText("");
                menuText3.setText("");

                saveText1=textSave.getString("txt1","");
                saveText2=textSave.getString("txt2","");
                saveText3=textSave.getString("txt3","");

                SharedPreferences nameSave = getSharedPreferences ("nameDB",0);
                SharedPreferences.Editor nameEditor = nameSave.edit();
                nameEditor.putString("cof1","");
                nameEditor.putString("cof2","");
                nameEditor.putString("cof3","");
                nameEditor.putString("cof4","");

                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = nameSave.edit();
                costEditor.putInt("cof1",0);
                costEditor.putInt("cof2",0);
                costEditor.putInt("cof3",0);
                costEditor.putInt("cof4",0);
            }
        });

     //메뉴 버튼 눌렀을 때도 저장 해줘야 함. + setText 필요

        cof_1=(Button)findViewById(R.id.cof_1);
        cof_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 가격 세팅
                cof1=true;
                costSum = costSum + menuCost[0];
                cost = (TextView) findViewById(R.id.cost);
                cost.setText(Integer.toString(costSum));

                //텍스트가 들어가 있지 않은 경우에만 세팅
                if (menuText1.getText().toString().length() == 0) {
                    menuText1.setText(menuName[0]);
                } else if (menuText1.getText().toString().length() != 0 && menuText2.getText().toString().length() == 0) {
                    menuText2.setText(menuName[0]);
                } else if (menuText1.getText().toString().length() != 0 && menuText2.getText().toString().length() != 0 && menuText3.getText().toString().length() == 0) {
                    menuText3.setText(menuName[0]);
                } else //장바구니가 꽉 찬 경우
                {
                    Toast.makeText(getApplicationContext(), "장바구니가 꽉 찼습니다.", Toast.LENGTH_SHORT).show();
                }

                //save
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof1", menuCost[0]);
                costEditor.commit();

                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum);
                totalEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor nameEditor = nameSave.edit();
                if(saveText1=="")
                {
                nameEditor.putString("txt1",menuName[0]);
                nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2=="")
                {
                nameEditor.putString("txt2",menuName[0]);
                nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2!=""&&saveText3=="")
                {
                 nameEditor.putString("txt3",menuName[0]);
                 nameEditor.commit();
                }

                Intent intent = new Intent(getApplicationContext(),SelectOneActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        cof_2=(Button)findViewById(R.id.cof_2);
        cof_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cof2=true;
                costSum = costSum + menuCost[1];

                // 가격 세팅
                cost=(TextView)findViewById(R.id.cost);
                cost.setText(Integer.toString(costSum));

                //텍스트가 들어가 있지 않은 경우에만 세팅
                if(menuText1.getText().toString().length()==0){
                    menuText1.setText(menuName[1]);
                }
                else if(menuText1.getText().toString().length()!=0&&menuText2.getText().toString().length()==0){
                    menuText2.setText(menuName[1]);
                }
                else if(menuText1.getText().toString().length()!=0&&menuText2.getText().toString().length()!=0&&menuText3.getText().toString().length()==0){
                    menuText3.setText(menuName[1]);
                }
                else //장바구니가 꽉 찬 경우
                {
                    Toast.makeText(getApplicationContext(), "장바구니가 꽉 찼습니다.", Toast.LENGTH_SHORT).show();
                }

                //save
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof2", menuCost[1]);
                costEditor.commit();

                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum);
                totalEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor nameEditor = nameSave.edit();
                if(saveText1=="")
                {
                    nameEditor.putString("txt1",menuName[1]);
                    nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2=="")
                {
                    nameEditor.putString("txt2",menuName[1]);
                    nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2!=""&&saveText3=="")
                {
                    nameEditor.putString("txt3",menuName[1]);
                    nameEditor.commit();
                }

                Intent intent = new Intent(getApplicationContext(),SelectTwoActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        cof_3=(Button)findViewById(R.id.cof_3);
        cof_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cof3=true;
                costSum = costSum + menuCost[2];

                // 가격 세팅
                cost=(TextView)findViewById(R.id.cost);
                cost.setText(Integer.toString(costSum));

                //텍스트가 들어가 있지 않은 경우에만 세팅
                if(menuText1.getText().toString().length()==0){
                    menuText1.setText(menuName[2]);
                }
                else if(menuText1.getText().toString().length()!=0&&menuText2.getText().toString().length()==0){
                    menuText2.setText(menuName[2]);
                }
                else if(menuText1.getText().toString().length()!=0&&menuText2.getText().toString().length()!=0&&menuText3.getText().toString().length()==0){
                    menuText3.setText(menuName[2]);
                }
                else //장바구니가 꽉 찬 경우
                {
                    Toast.makeText(getApplicationContext(), "장바구니가 꽉 찼습니다.", Toast.LENGTH_SHORT).show();
                }

                //save
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof3", menuCost[2]);
                costEditor.commit();

                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum);
                totalEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor nameEditor = nameSave.edit();
                if(saveText1=="")
                {
                    nameEditor.putString("txt1",menuName[2]);
                    nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2=="")
                {
                    nameEditor.putString("txt2",menuName[2]);
                    nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2!=""&&saveText3=="")
                {
                    nameEditor.putString("txt3",menuName[2]);
                    nameEditor.commit();
                }

                Intent intent = new Intent(getApplicationContext(),SelectThreeActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        cof_4=(Button)findViewById(R.id.cof_4);
        cof_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cof4=true;
                costSum = costSum + menuCost[3];
                // 가격 세팅
                cost=(TextView)findViewById(R.id.cost);
                cost.setText(Integer.toString(costSum));

                //텍스트가 들어가 있지 않은 경우에만 세팅
                if(menuText1.getText().toString().length()==0){
                    menuText1.setText(menuName[3]);
                }
                else if(menuText1.getText().toString().length()!=0&&menuText2.getText().toString().length()==0){
                    menuText2.setText(menuName[3]);
                }
                else if(menuText1.getText().toString().length()!=0&&menuText2.getText().toString().length()!=0&&menuText3.getText().toString().length()==0){
                    menuText3.setText(menuName[3]);
                }
                else //장바구니가 꽉 찬 경우
                {
                    Toast.makeText(getApplicationContext(), "장바구니가 꽉 찼습니다.", Toast.LENGTH_SHORT).show();
                }

                //save
                SharedPreferences costSave = getSharedPreferences ("costDB",0);
                SharedPreferences.Editor costEditor = costSave.edit();
                costEditor.putInt("cof4", menuCost[3]);
                costEditor.commit();

                SharedPreferences totalSave = getSharedPreferences ("totalDB",0);
                SharedPreferences.Editor totalEditor = totalSave.edit();
                totalEditor.putInt("total", costSum);
                totalEditor.commit();

                SharedPreferences nameSave = getSharedPreferences ("textDB",0);
                SharedPreferences.Editor nameEditor = nameSave.edit();
                if(saveText1=="")
                {
                    nameEditor.putString("txt1",menuName[3]);
                    nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2=="")
                {
                    nameEditor.putString("txt2",menuName[3]);
                    nameEditor.commit();
                }
                else if(saveText1!=""&&saveText2!=""&&saveText3=="")
                {
                    nameEditor.putString("txt3",menuName[3]);
                    nameEditor.commit();
                }

                Intent intent = new Intent(getApplicationContext(),SelectFourActivity.class);
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
