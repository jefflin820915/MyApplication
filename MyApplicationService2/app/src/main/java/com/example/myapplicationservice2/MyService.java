package com.example.myapplicationservice2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private  final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException( "Not yet implemented" );
        return mBinder;

    }

    public int doSome(){
        Log.v("brad","Service: doSome...");
        return (int)(Math.random()*49+1);
    }
}
