package com.example.myapplicationbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class HightLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.v("brad", "high action ---> " + intent.getAction());

        //終止往下傳達
        //abortBroadcast();


        Bundle resultExtras = getResultExtras( true );
        String content = resultExtras.getCharSequence( "content" ).toString();
        Log.v( "brad","content ---> " + content  );

        //修改內容
        resultExtras.putCharSequence( "content","我是被修改的內容...." );
        setResultExtras( resultExtras );

    }
}
