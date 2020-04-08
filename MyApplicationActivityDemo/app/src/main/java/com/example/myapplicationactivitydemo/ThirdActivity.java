package com.example.myapplicationactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 第三個頁面,通過隱式意圖來跳轉過來.
 */

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second);

        TextView info = findViewById( R.id.info );

        Intent intent = getIntent();
        String account = intent.getStringExtra( "account" );
        String password = intent.getStringExtra( "password" );

        Log.v( "brad","account---> " + account + "   password---> " + password  );

        info.setText( "您的帳號:"+account+ ", 密碼是:"+ password );
    }
}
