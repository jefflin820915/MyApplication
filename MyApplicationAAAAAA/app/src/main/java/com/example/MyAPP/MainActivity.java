package com.example.MyAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

    }

    //跳轉到第二頁
    public void nextTwoPage(View view) {
        Intent intent = new Intent();
        intent.setClass( this, SecondActivity.class );

        startActivity( intent );
    }
}
