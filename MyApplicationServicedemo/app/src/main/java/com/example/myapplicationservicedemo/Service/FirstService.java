package com.example.myapplicationservicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class FirstService extends Service {

    public class InnerBinder extends Binder{
        public void callServiceInnerMethod(){

            sayHello();
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.v( "brad","onBind..." );

        return new InnerBinder();


    }



    @Override
    public void onCreate() {
        super.onCreate();

        Log.v( "brad","onCreate..." );

    }

//    OnStart方法已過時.
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart( intent, startId );
//
//        Log.v( "brad","onStart..." );
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v( "brad","onStartCommand..." );

        return super.onStartCommand( intent, flags, startId );

    }


    @Override
    public boolean onUnbind(Intent intent) {

        Log.v( "brad","onUnBind..." );
        return super.onUnbind( intent );

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart( intent, startId );
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.v( "brad","onDestroy..." );
    }

    private void sayHello(){

        Toast.makeText( this,"Hello!",Toast.LENGTH_SHORT ).show();
    }


}

