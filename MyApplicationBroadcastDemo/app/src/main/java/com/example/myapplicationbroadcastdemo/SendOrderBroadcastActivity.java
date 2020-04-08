package com.example.myapplicationbroadcastdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendOrderBroadcastActivity extends AppCompatActivity {

    private HightLevelReceiver mHightLevelReceiver;
    private DefaultLevelReceiver mDefaultLevelReceiver;
    private LowLevelReceiver mLowLevelReceiver;
    public static final String PERMISSION = "com.example.myapplicationbroadcastdemo.ORDER_PERMISSION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_send_order_broadcast );

        registerLowBroadcast();

        registerDefaultBroadcast();

        registerHighOrderBroadcast();

    }

    private void registerLowBroadcast() {

        IntentFilter intentFilter = new IntentFilter(  );
        intentFilter.addAction( Constents.ACTION_SEND_ORDER_BROADCAST );
        intentFilter.setPriority( -1000 );

        mLowLevelReceiver = new LowLevelReceiver();

        this.registerReceiver( mLowLevelReceiver,intentFilter );
    }

    private void registerDefaultBroadcast() {

        IntentFilter intentFilter = new IntentFilter(  );
        intentFilter.addAction( Constents.ACTION_SEND_ORDER_BROADCAST );
        intentFilter.setPriority( 0 );


        mDefaultLevelReceiver = new DefaultLevelReceiver();

        this.registerReceiver( mDefaultLevelReceiver,intentFilter );

    }


    private void registerHighOrderBroadcast() {

        IntentFilter intentFilter = new IntentFilter(  );
        intentFilter.addAction(Constents.ACTION_SEND_ORDER_BROADCAST  );
        intentFilter.setPriority( 1000 );

        mHightLevelReceiver = new HightLevelReceiver();

        this.registerReceiver( mHightLevelReceiver,intentFilter );
    }


    /**
     *
     * @param view
     *
     * 這個表示我們發送按鈕被點擊了
     */
    public void sendOrderBroadcast(View view) {

        Intent intent = new Intent(  );
        intent.setAction( Constents.ACTION_SEND_ORDER_BROADCAST );

        Bundle bundle = new Bundle(  );
        bundle.putCharSequence( "content","我是被發送的廣播...." );

        sendOrderedBroadcast(intent,null,null,null, Activity.RESULT_OK,null,bundle );

    }

}
