package com.example.myapplicationbroadcastpermissiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String ORDER_BROADCAST = "com.example.myapplicationbroadcastdemo.ORDER_BROADCAST";
    public static final String RECEIVER_ORDER_BROADCAST = "com.example.myapplicationbroadcastpermissiondemo.WHO_CAN_SEND_TO_ME";
    private OrderBroadcastReceiver mOrderBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        registerOrderBroadcast();


    }

    private void registerOrderBroadcast() {

        IntentFilter intentFilter = new IntentFilter(  );
        intentFilter.addAction( ORDER_BROADCAST );

        mOrderBroadcastReceiver = new OrderBroadcastReceiver();
        registerReceiver( mOrderBroadcastReceiver,intentFilter,RECEIVER_ORDER_BROADCAST,null );

    }

}
