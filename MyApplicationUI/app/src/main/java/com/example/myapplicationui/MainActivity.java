package com.example.myapplicationui;

import androidx.appcompat.app.AppCompatActivity;

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
        Log.v("brad","test1");
    }


    public void test2(View view) {
        Log.v( "brad","test2" ) ;
    }

    public void test3(View view){
        Log.v( "brad","test3" );
    }
}
