package com.example.myapplicationactivitylaunchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
    }


    public void openFirst(View view) {

        //打開第一個Activity
        startActivity( new Intent( this,FirstActivity.class ) );


    }

    public void openSecond(View view) {

        //打開第二個Activity
        startActivity( new Intent( this,SecondActivity.class ) );
    }


}
