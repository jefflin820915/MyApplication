package com.example.myapplicationservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;
    private  int i;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException( "Not yet implemented" );
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       timer = new Timer(  );
       timer.schedule( new MyTask(),0,1000 );
    }

    private class MyTask extends TimerTask{
        @Override
        public void run() {
            Log.v( "brad","i = " + i++ );
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int rand = intent.getIntExtra( "i",-1 );
        i = rand;
        return super.onStartCommand( intent, flags, startId );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
