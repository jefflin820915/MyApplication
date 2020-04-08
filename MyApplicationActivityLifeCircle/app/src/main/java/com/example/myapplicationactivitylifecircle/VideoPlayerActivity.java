package com.example.myapplicationactivitylifecircle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 以後開發影片撥放器有個場景,
 * 當我們撥放精采電影的時候來電話了
 *
 */
public class VideoPlayerActivity extends AppCompatActivity {

    private TextView mCurrentPlayStatus;
    private Button mPlayerControl;

    //是不是因為生命週期的變化主動停止的
    private boolean isStopAtmin = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player );

        initView();

        initListener();
    }

    private boolean isPlayer = false;

    private void initListener() {
        mPlayerControl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlayer) {
                    //如果當前狀態是撥放的那我們就去停止撥放
                    stop();
                }else {
                    //如果當前狀態是停止的我們就去撥放
                    play();
                }
            }
        } );
    }

    private void play(){

        Log.v( "brad","撥放電影" );
        mCurrentPlayStatus.setText( "正在撥放電影....." );
        mPlayerControl.setText( "暫停" );
        isPlayer = true;
    }

    private void stop(){

        Log.v( "brad","停止電影" );
        mCurrentPlayStatus.setText( "停止電影撥放....." );
        mPlayerControl.setText( "撥放" );
        isPlayer = false;
    }


    private void initView() {
        mCurrentPlayStatus = findViewById( R.id.current_play_status );
        mPlayerControl = findViewById( R.id.player_control );
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.v( "brad","onStart...開始撥放..." );
        if (isStopAtmin && !isPlayer) {
            play();
            isStopAtmin = false;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        Log.v("brad","onStop....暫停撥放..");
        if (isPlayer) {
            //如果當前是撥放的就把影片停止
            stop();
            isStopAtmin = true;
        }
    }
}
