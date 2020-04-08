package com.example.myapplicationservicedemo;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.myapplicationservicedemo.Service.PlayerService;
import com.example.myapplicationservicedemo.interfaces.IPlayerControl;
import com.example.myapplicationservicedemo.interfaces.IPlayerViewControl;
import com.example.myapplicationservicedemo.presenter.PlayerPresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.example.myapplicationservicedemo.interfaces.IPlayerControl.PLAY_STATE_PAUSE;
import static com.example.myapplicationservicedemo.interfaces.IPlayerControl.PLAY_STATE_PLAY;
import static com.example.myapplicationservicedemo.interfaces.IPlayerControl.PLAY_STATE_STOP;

public class PlayerActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private Button mPlayOrPause;
    private Button mClose;
    private PlayerServiceConnection mPlayerServiceConnection;
    private IPlayerControl mIPlayerControl;
    private boolean isUserTouchProgressBar = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player );

        ReadPermission();

        initView();

        //設置相關事件
        initEvent();

        //開啟撥放服務
        initService();

        //綁定服務
        initBindService();
    }

    private void ReadPermission() {

        if (ContextCompat.checkSelfPermission( PlayerActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions( PlayerActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1 );
        }

    }

    /**
     * 開啟撥放的服務
     */
    private void initService() {

        Log.v( "brad", "initService...." );
        Intent intent = new Intent();
        intent.setClass( this, PlayerService.class );

        startService( intent );

    }

    /**
     * 綁定服務
     */
    private void initBindService() {
        Log.v( "brad", "initBindService..." );

        Intent intent = new Intent();
        intent.setClass( this, PlayerService.class );

        if (mPlayerServiceConnection == null) {

            mPlayerServiceConnection = new PlayerServiceConnection();

        }

        bindService( intent, mPlayerServiceConnection, BIND_AUTO_CREATE );
    }

    private class PlayerServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.v( "brad", "onServiceConnected ---> " + service );

            mIPlayerControl = (IPlayerControl) service;

            mIPlayerControl.registerViewController( mIplayerViewControl );
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.v( "brad", "onServiceDisconnected..." );

            mIPlayerControl = null;
        }
    }

    /**
     * 初始化控件事件
     */
    private void initEvent() {

        mSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //進度條發生改變

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                //手觸摸上去推動

                isUserTouchProgressBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int touchProgress = seekBar.getProgress();

                Log.v( "brad", "touchProgress ---> " + touchProgress );

                //停止推動
                if (mIPlayerControl != null) {

                    mIPlayerControl.SeekTo( touchProgress );
                }
                    isUserTouchProgressBar = false;
            }
        } );


        mPlayOrPause.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //撥放或暫停
                if (mIPlayerControl != null) {

                    mIPlayerControl.PlayOrPause();
                }
            }
        } );


        mClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //關閉按鈕被點擊
                if (mIPlayerControl != null) {

                    mIPlayerControl.Stop();
                }

            }
        } );


    }

    /**
     * 找到控件
     */
    private void initView() {

        mSeekBar = findViewById( R.id.seek_bar );
        mPlayOrPause = findViewById( R.id.play_or_pause_btn );
        mClose = findViewById( R.id.close_btn );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v( "brad", "onDestroy...." );

        if (mPlayerServiceConnection != null) {

            //釋放資源
            mIPlayerControl.unregisterViewController();
            unbindService( mPlayerServiceConnection );

        }

    }

    private IPlayerViewControl mIplayerViewControl = new IPlayerViewControl() {
        @Override
        public void onPlayStateChange(int state) {

            //我們要根據撥放狀態來修改UI
            switch (state) {
                case PLAY_STATE_PLAY:

                    //撥放中的話,要修改按鈕顯示成暫停
                    mPlayOrPause.setText( "PAUSE" );
                    break;

                case PLAY_STATE_PAUSE:
                case PLAY_STATE_STOP:
                    mPlayOrPause.setText( "Play" );
                    break;
            }

        }

        @Override
        public void onSeekChange(final int seek) {
            Log.v( "brad","seek change" + seek );
            Log.v( "brad","current thread --> "  + Thread.currentThread().getName() );

            //改變撥放進度,有一個條件,當用戶的手觸摸到進度條時,就不更新
            //從上面Log得知,這不是主線程,所以不可以用於更新UI
            //為什麼更新進度不會崩潰呢
            //因為在Android裡面有兩個控件可以用子線程去更新
            //一個是progressBar,一個是surfaceView.

            runOnUiThread( new Runnable() {
                @Override
                public void run() {

                    if (!isUserTouchProgressBar) {
                        mSeekBar.setProgress( seek );
                    }
                }
            } );


        }
    };


}
