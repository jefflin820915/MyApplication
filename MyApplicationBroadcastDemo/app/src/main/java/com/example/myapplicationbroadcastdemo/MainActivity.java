package com.example.myapplicationbroadcastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mBetteryLevel;
    private BatteryLevelReciver mBatteryLevelReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //
        initView();
        regissterBatteryReciver();

    }

    private void initView() {

        mBetteryLevel = findViewById( R.id.bettery_level );
    }

    private void regissterBatteryReciver() {
        //第二步:
        //收聽的頻道: 電量變化
        IntentFilter intentFilter = new IntentFilter();

        //第三步:
        //設置頻道
        intentFilter.addAction( Intent.ACTION_BATTERY_CHANGED );

        //
        intentFilter.addAction( Intent.ACTION_POWER_CONNECTED );
        intentFilter.addAction( Intent.ACTION_POWER_DISCONNECTED );

        //第四步:
        mBatteryLevelReciver = new BatteryLevelReciver();

        //第五步:
        //這種方式是動態註冊
        //註冊廣播
        this.registerReceiver( mBatteryLevelReciver, intentFilter );
    }

    /**
     * 第一步,就是創建一個廣播接收者,繼承自己BroadcastReceiver
     */

    private class BatteryLevelReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (Intent.ACTION_BATTERY_CHANGED.equals( action )) {

                Log.v( "brad", "收到電量變化的廣播----- > action----> " + action );

                int currentLevel = intent.getIntExtra( BatteryManager.EXTRA_LEVEL, 0 );

                if (mBetteryLevel != null) {
                    mBetteryLevel.setText( "當前電量 : " + currentLevel );

                }

                int maxLevel = intent.getIntExtra( BatteryManager.EXTRA_SCALE, 0 );

                //拿到當前的電量以後再除以最大值
                float percent = currentLevel * 1.0f / maxLevel * 100;
                Log.v( "brad", "當前電量百分比: " + percent + "%" );

            }else if (Intent.ACTION_POWER_CONNECTED.equals( action )){

                Log.v( "brad","USB connect "  );

            }else if (Intent.ACTION_POWER_DISCONNECTED.equals( action )){

                Log.v( "brad","USB disconnect " );
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消廣播註冊,否則會導致內存瀉
        if (mBatteryLevelReciver != null) {
            this.unregisterReceiver( mBatteryLevelReciver );
        }


    }
}
