package com.example.myapplicationservicemusic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private Button playPause;
    private SeekBar seekBar;
    private MyService myService;
    private boolean isBound;
    private boolean isPlaying;
    private MyReceiver myReceiver;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder)iBinder;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        playPause = findViewById( R.id.playOrPause );
        isPlaying =  false;
        playPause.setText( "PLAY" );

        seekBar = findViewById( R.id.seekBar );

        //seekBar點擊事件
        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            //滑動seekBar方法
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //如果user去滑動seekBar
                if(fromUser){

                    //調用myService的setPosition方法把位置的值傳回去
                    myService.setPosition( progress );
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );
    }

    @Override
    protected void onStart() {
        super.onStart();    
        Intent intent = new Intent( this,MyService.class );
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE );

        //創建Myreceiver對象賦予myReceiver變數
        myReceiver = new MyReceiver();

        //創建IntentFilter實例對象賦予給IntentFilter類型的變數,名為intentFilter(String action)
        IntentFilter intentFilter = new IntentFilter( "fromService" );

        //註冊BroadcastReceiver以主要的活動中運行,
        //接收器將在主應用程序中與任何匹配過的過濾器廣播intent一起調用
        registerReceiver( myReceiver,intentFilter );
    }

    @Override
    protected void onStop() {
        super.onStop();
     if (isBound){
         unbindService( mConnection );
         isBound = false;
     }
     unregisterReceiver( myReceiver );
    }

    public void playOrPause(View view) {
        isPlaying = !isPlaying;
        if(!isPlaying){
            playPause.setText( "PLAY" );
            myService.pauseMusic();
        }else{
            playPause.setText( "PAUSE" );
            myService.playMusic();
        }

    }

    public void stopPlay(View view) {
        isPlaying = false;
        playPause.setText( "PLAY" );
        myService.stopMusic();
        myService.setPosition( 0 );
    }


    //接收廣播的類: 來接收廣播,因繼承抽象類BroadcastReceiver
    private class MyReceiver extends BroadcastReceiver {
        //所以必重寫BroadcastReceiver類裡的抽象onReceive(Context context, Intent intent)方法
        @Override
        public void onReceive(Context context, Intent intent) {

            //創建int類型變數名為max,使用getIntExtra方法從intent中查找max,沒有使用max的話返回預設值-1
            int max = intent.getIntExtra( "max",-1 );

            //如果變數max大於等於0
            if (max >= 0){

                //使用setMax方法來設置seekBar範圍為0...max
                seekBar.setMax( max );
            }
            //創建int類型變數名為now,使用getIntExtra方法從intent中查找now,沒有使用max的話返回預設值-1
            int now = intent.getIntExtra( "now",-1 );

            //如果now變數大於等於0
            if (now >= 0){

                //使用setProgress方法來設置seekBar範圍.
                //setProgress方法:將當前進度設置為設定值
                seekBar.setProgress( now );
            }
        }
    }
}
