package com.example.myapplicationbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 第一步,創建一個類,並且繼承自BroadcastReceiver
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.v( "brad", "action---->" + action );
        Log.v( "brad", "開機完成...." );

        Toast.makeText( context, "收到開機完成之廣播", Toast.LENGTH_SHORT ).show();

    }
}
