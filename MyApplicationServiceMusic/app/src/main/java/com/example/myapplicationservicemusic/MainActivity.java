package com.example.myapplicationservicemusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    //創建Button類型的變數名為playPause
    private Button playPause;
    //創建boolean類型的變數名為isPlaying
    private boolean isPlaying;
    //創建SeekBar類型的變數名為seekBar
    private SeekBar seekBar;
    //創建MyReceiver類型的變數名為myReceiver
    private MyReceiver myReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Button類型的變數playPause賦予ButtonId
        playPause = findViewById( R.id.playOrPause );

        //boolean類型的變數isPlaying賦予值false
        isPlaying =  false;

        //給Button類型的變數playPause button賦予文字
        playPause.setText( "PLAY" );

        //SeekBar類型的變數seekBar賦予SeekBarId
        seekBar = findViewById( R.id.seekBar );

        //seekBar點擊事件
        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            //滑動seekBar方法
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //如果user去滑動seekBar
                if(fromUser){
                    Intent intent = new Intent( MainActivity.this,MyService.class );
                    intent.putExtra( "action",MyService.ACTION_SEEK );
                    intent.putExtra( "where",progress );
                    startService( intent );
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
    //重寫Activity生命週期裡onStart()方法
    @Override
    protected void onStart() {
        super.onStart();

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
        unregisterReceiver( myReceiver );
    }

    //id為playPause的Button點擊事件
    public void playOrPause(View view) {
        // isPlaying = false
        // isPlaying = !isPlaying;(true)
        isPlaying = !isPlaying;
        //如果在不是在撥放中,執行以下程序
        if(!isPlaying){
            //按鈕的文字顯示為"PLAY"
            playPause.setText( "PLAY" );
        //如果在撥放中,則執行以下程序
        }else{
            //按鈕的文字顯示為"PAUSE"
            playPause.setText( "PAUSE" );
        }

        //創建實例對象Intent賦予給Intent類型的intent變數,由Intent方法從this(MainActivity)跳到MyService類
        Intent intent = new Intent( this,MyService.class );

        //數據添加到intent中,name名為now, 有沒有在播放,有的話,傳送播放的指令否則傳送暫停的指令
        intent.putExtra( "action",isPlaying?MyService.ACTION_PLAY:MyService.ACTION_PAUSE );

        //請求啟動給定(intent)的應用程序服務
        startService( intent );

    }

    //停止按鈕的點擊事件
    public void stopPlay(View view) {

        //沒有在播放中
        isPlaying = false;

        //playPause按鈕文字顯示為"PLAY"
        playPause.setText( "PLAY" );

        //創建實例對象Intent賦予給Intent類型的intent變數,由Intent方法從this(MainActivity)跳到MyService類
        Intent intent = new Intent( this,MyService.class );

        //請求停止給定(intent)的應用程序服務
        stopService( intent );
    }

    //接收廣播的類: 來接收廣播,因繼承抽象類BroadcastReceiver
    private class MyReceiver extends BroadcastReceiver{
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
