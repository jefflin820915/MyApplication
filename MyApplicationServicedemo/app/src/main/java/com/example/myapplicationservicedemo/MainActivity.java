package com.example.myapplicationservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.myapplicationservicedemo.Service.FirstService;

public class MainActivity extends AppCompatActivity {

    private boolean mIsServiceBind;
    private FirstService.InnerBinder mRemoteBindder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Log.v( "brad","onCreate.." );

    }

    /**
     * 開啟服務
     */

    public void startServiceClick(View view) {

        Intent intent = new Intent(  );
        intent.setClass( this, FirstService.class );

        startService( intent );

    }

    /**
     * @param view
     *
     *停止服務
     */
    public void stopServiceClick(View view) {

        Intent intent = new Intent(  );
        intent.setClass( this,FirstService.class );

        stopService( intent );

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v( "brad","onDestroy..." );

    }

    public void callServiceMethod(View view){

        Log.v( "brad","call service inner method" );

        mRemoteBindder.callServiceInnerMethod();

    }


    /**
     *
     * @param view
     * 綁定服務
     */
    public void bindService(View view) {

        Intent intent = new Intent(  );
        intent.setClass( this,FirstService.class );
        mIsServiceBind = bindService( intent, serviceConnection, BIND_AUTO_CREATE );

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v( "brad","onServiceConnect..." );
            mRemoteBindder = (FirstService.InnerBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.v( "brad","onSeerviceDisconnect" );
            mRemoteBindder = null;
        }
    };

    /**
     *
     * @param view
     * 解榜服務
     */
    public void unbindService(View view) {

        if (serviceConnection != null && mIsServiceBind) {

            unbindService( serviceConnection );
        }
    }
}
