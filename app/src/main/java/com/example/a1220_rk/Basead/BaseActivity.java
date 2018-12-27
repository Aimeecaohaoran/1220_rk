package com.example.a1220_rk.Basead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        //初始化控件
        initView();
        //设置数据
        getNews();
    }
    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void getNews();
}
