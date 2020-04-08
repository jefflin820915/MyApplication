package com.example.myapplicationservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void test1(View view) {
        Intent intent = new Intent( this,MyService.class );
        intent.putExtra( "i",(int)(Math.random()*49+1) );
        startService( intent );

    }

    public void test2(View view) {
        Intent intent = new Intent( this, MyService.class );
        stopService( intent );

    }
}
