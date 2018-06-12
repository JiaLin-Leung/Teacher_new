package com.tbkt.teacher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.tbkt.teacher.Login.LoginActivity;

/**
 * @Author: LiuShiQi 刘士奇
 * @Date: 2018/6/11
 * @Description:
 */
public class WelcomeActivity extends Activity {

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_welcome);
        new Handler() {
            public void handleMessage(android.os.Message msg) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.sendEmptyMessageDelayed(0, 3000);
    }



}
