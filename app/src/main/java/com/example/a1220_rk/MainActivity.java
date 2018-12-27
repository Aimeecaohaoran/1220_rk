package com.example.a1220_rk;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private int time=5;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                if (time>0){
                    time--;
                    textView.setText(time+"S");
                    handler.sendEmptyMessageDelayed(0,1000);

                }else {
                    handler.removeCallbacksAndMessages(null);
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始胡控件
        textView=findViewById(R.id.text_time);
        handler.sendEmptyMessageDelayed(0,1000);

    }

}
