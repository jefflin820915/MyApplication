package com.example.myapplicationnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //創建ConnectivityManager類型名為CM的變數
    private ConnectivityManager CM;

    ////創建MyNewworkReceiver類型名為myNewworkReceiver的變數
    private MyNewworkReceiver myNewworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //ConnectivityManager回答網路連接狀態的類
        //調用getSystemService( Context.CONNECTIVITY_SERVICE );來實例ConnectivityManager類
        CM = (ConnectivityManager)getSystemService( Context.CONNECTIVITY_SERVICE );

        //創建MyNewworkReceiver實例對象賦予myNewworkReceiver變數
        myNewworkReceiver = new MyNewworkReceiver();

        //創建IntentFilter實例對象賦予給IntentFilter類型的變數,名為intentFilter(網路發生的變化)
        IntentFilter intentFilter = new IntentFilter( ConnectivityManager.CONNECTIVITY_ACTION );

        //註冊BroadcastReceiver以主要的活動中運行,
        //接收器將在主應用程序中與任何匹配過的過濾器廣播intent一起調用
        registerReceiver( myNewworkReceiver,intentFilter );
    }

    //在Activity結束前去解除Receiver
    //重寫finish方法
    @Override
    public void finish() {

        //unregisterReceiver();方法: 取消註冊以前註冊的BroadcastReceiver
        unregisterReceiver( myNewworkReceiver );

        super.finish();

    }

    //連接網路狀態類
    private boolean isConnectNetwork(){

     //getActiveNetworkInfo();方法: 返回有關當前活動默認數據網路的詳細訊息
     //創建NetworkInfo類型名為info的變數
       NetworkInfo info =  CM.getActiveNetworkInfo();

       //創建boolean類型名為isConnected變數,確認網路是否連上的詳細訊息
        //info != null && info.isConnectedOrConnecting();
        //info變數不等於空值或著調用isConnectedOrConnecting();方法來指示網路連接是否存在或著建立
        boolean isConnected = info != null && info.isConnectedOrConnecting();

        //返回值isConnected
        return isConnected;
    }

    public void test1(View view) {
        Log.v("brad","isConnected = " + isConnectNetwork());
    }

    private boolean isConnectWifi(){
        NetworkInfo info = CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info.isConnected();
    }

    private class MyNewworkReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("brad","isConnected = " + isConnectNetwork());
        }
    }
}
