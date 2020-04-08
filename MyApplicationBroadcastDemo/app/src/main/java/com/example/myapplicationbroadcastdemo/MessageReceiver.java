package com.example.myapplicationbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.v("brad","action ----> " + action);
        String content = intent.getStringExtra( Constents.KEY_CONTENT );
        Log.v( "brad","content ---> " + content );
    }
}
