package com.example.myapplicationbroadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 為什麼要監聽安裝與卸載,主要是收集訊息
 *
 */

public class AppStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (Intent.ACTION_PACKAGE_ADDED.equals( action )) {

            //應用安裝的
            Log.v( "brad","應用安裝了, 他的相關訊息---> " + intent.getData() );

        }else if (Intent.ACTION_PACKAGE_REMOVED.equals( action )){

            //這個是應用卸載
            Log.v( "brad","應用卸載了, 他的相關訊息---> " + intent.getData() );

        }
    }
}
