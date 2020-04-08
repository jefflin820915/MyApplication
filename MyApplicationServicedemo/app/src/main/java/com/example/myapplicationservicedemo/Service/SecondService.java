package com.example.myapplicationservicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplicationservicedemo.interfaces.ICommunication;

import androidx.annotation.Nullable;

public class SecondService extends Service {

    public class InnerBinder extends Binder implements ICommunication {

        @Override
        public void callServiceInnerMethod() {

            serviceInnerMethod();
        }
    }


    /**
     * 服務被創建
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        Log.v( "brad","onCreate...");

    }


    /**
     * 服務被綁定
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

    Log.v( "brad","onBind..." );

        InnerBinder innerBinder = new InnerBinder();

        return innerBinder;
    }


    /**
     * 服務被啟動
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v( "brad","onStartCommand..." );

        return super.onStartCommand( intent, flags, startId );
    }


    /**
     * 服務解綁
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {

        Log.v( "brad","onUnBind..." );


        return super.onUnbind( intent );
    }


    /**
     * 服務銷毀
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.v( "brad","onDestroy..." );


    }

    /**
     * 服務內部方法
     */
    private void serviceInnerMethod(){

        Log.v( "brad","serviceInnerMethod" );
        Toast.makeText( this,"服務內部方法被調用",Toast.LENGTH_SHORT ).show();
    }

}
