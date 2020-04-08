package com.example.myapplicationactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 第二個頁面,通過顯示意圖來跳轉頁面
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView info = findViewById( R.id.info );

        Intent intent = getIntent();
        String account = intent.getStringExtra( "account" );
        String password = intent.getStringExtra( "password" );

        Log.v( "brad","account---> " + account + "   password---> " + password  );

        info.setText( "您的帳號:"+account+ ",密碼是:"+ password );

    }
}
