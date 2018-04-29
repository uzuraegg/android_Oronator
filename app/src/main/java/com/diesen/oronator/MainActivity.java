package com.diesen.oronator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final Handler handler = new Handler();

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private Button button_return;

    private LinearLayout white_box;
    private TextView view_num;
    private TextView view_question;

    private String[] array_questions;

    private TableLayout table_buttons;
    private TableRow row1;
    private TableRow row2;

    private int count_q = 0;

    private ImageView rcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcc = new ImageView(this);
        // 画像の縦横サイズをimageViewのサイズとして設定
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(400, 400);
        rcc.setLayoutParams(layoutParams);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        rcc.setImageResource(R.drawable.logo);

        array_questions = getResources().getStringArray(R.array.questions);

        button1 = findViewById(R.id.Button1);
        button2 = findViewById(R.id.Button2);
        button3 = findViewById(R.id.Button3);
        button4 = findViewById(R.id.Button4);
        button5 = findViewById(R.id.Button5);

        button_return = new Button(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        table_buttons = findViewById(R.id.tableLayout);
        row1 = findViewById(R.id.tableRow1);
        row2 = findViewById(R.id.tableRow2);

        white_box = findViewById(R.id.white);
        view_num = findViewById(R.id.text_num);
        view_question = findViewById(R.id.text_question);

        view_num.setText("質問その" + (count_q+1));
        view_question.setText(array_questions[count_q]);
    }

    @Override
    public void onClick(View v) {
        // イベントが発生したViewのIDで処理を分岐
        switch (v.getId()) {

            // 「たぶんそう」or「はい」
            case R.id.Button1:
            case R.id.Button3:
                result();
                break;

            // 「多分違う」or「分からない」or「いいえ」
            case R.id.Button2:
            case R.id.Button4:
            case R.id.Button5:
                count_q++;
                if(count_q >= 6){
                    result();
                    break;
                }
                view_num.setText("質問その" + (count_q+1));
                view_question.setText(array_questions[count_q]);
                break;



        }

    }

    public void result(){
        final TextView text_rcc = new TextView(this);

        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);

        text_rcc.setText("立命館コンピュータクラブ");
        text_rcc.setTextSize(32);
        text_rcc.setGravity(Gravity.CENTER_HORIZONTAL);
        text_rcc.setTextColor(Color.BLACK);

        //フェードインアニメーションの準備する(1,0)フェードアウト、(0,1)フェードイン
        AlphaAnimation feedout = new AlphaAnimation( 1, 0 );

        //表示時間を指定
        feedout.setDuration( 2000 );

        //実行
        white_box.startAnimation( feedout );

        // 3秒後に処理を実行する
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view_num.setTextSize(20);
                view_num.setText("あなたが入るべきサークルは");
                white_box.removeView(view_question);
                white_box.addView(text_rcc);
                white_box.addView(rcc);
                table_buttons.removeAllViews();
            }
        }, 1900);


    }
}
