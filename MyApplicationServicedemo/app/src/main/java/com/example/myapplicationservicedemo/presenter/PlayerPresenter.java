package com.example.myapplicationservicedemo.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.util.Log;

import com.example.myapplicationservicedemo.interfaces.IPlayerControl;
import com.example.myapplicationservicedemo.interfaces.IPlayerViewControl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerPresenter extends Binder  implements IPlayerControl {
    private IPlayerViewControl mViewController;
    private int mCurrentState = PLAY_STATE_STOP;
    private MediaPlayer mMediaPlayer;
    private Timer mTimer;
    private SeekTimeTask mTimeTask;

    @Override
    public void registerViewController(IPlayerViewControl viewController) {

        this.mViewController = viewController;
    }

    @Override
    public void unregisterViewController() {

        mViewController = null;
    }

    @Override
    public void PlayOrPause() {

        Log.v("brad","PlayOrPause...");
        if (mCurrentState ==PLAY_STATE_STOP) {
            //創建撥放器
            initPlayer();
            //設置數據源
            try {
                mMediaPlayer.setDataSource("/sdcard/PCR.mp3");
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                mCurrentState = PLAY_STATE_PLAY;
                startTimer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (mCurrentState == PLAY_STATE_PLAY){
            //如果當前的狀態是撥放的那麼就暫停
            if (mMediaPlayer != null) {
                 mMediaPlayer.pause();
                 mCurrentState = PLAY_STATE_PAUSE;
                 stopTimer();
            }
        }else if (mCurrentState == PLAY_STATE_PAUSE){
            //如果當前的狀態是暫停的, 那麼就繼續撥放
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                mCurrentState = PLAY_STATE_PLAY;
                stopTimer();
            }
        }
        //通知UI更新介面
        if (mViewController != null) {

            mViewController.onPlayStateChange( mCurrentState );
        }

    }

    /**
     * 初始化撥放器
     */
    private void initPlayer() {
        if (mMediaPlayer ==null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType( AudioManager.STREAM_MUSIC );

        }

    }

    @Override
    public void Stop() {
        Log.v("brad","Stop...");
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mCurrentState = PLAY_STATE_STOP;
            stopTimer();

            //更新撥放狀態
            if (mViewController !=null) {
                mViewController.onPlayStateChange( mCurrentState );
            }

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void SeekTo(int seek) {
        Log.v("brad","SeekTo..." + seek);
        //0~100之間
        //需要做個轉換,得到的一個seek其實是一個百分比
        if (mMediaPlayer!=null) {
        int tarSeek = (int) (seek * 1.0f/100 * mMediaPlayer.getDuration());

        mMediaPlayer.seekTo( tarSeek );
        }
    }

    /**
     * 開啟一個timerTask
     */
    private void startTimer(){

        if (mTimer==null) {
            mTimer = new Timer(  );

        }

        if (mTimeTask==null) {
        mTimeTask = new SeekTimeTask();

        }

        mTimer.schedule( mTimeTask,0,500 );

    }


    private void stopTimer(){

        if (mTimeTask!=null) {
            mTimeTask.cancel();
            mTimeTask = null;
        }

        if (mTimer!=null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    private class SeekTimeTask extends TimerTask{

        @Override
        public void run() {

            //獲取當前的撥放進度
            if (mMediaPlayer!=null && mViewController!=null ) {
                int currentPosition = mMediaPlayer.getCurrentPosition();
                //Log.v( "brad","play position"  + currentPosition);
                int curPosition = (int) (currentPosition * 1.0f / mMediaPlayer.getDuration() *100);
                mViewController.onSeekChange( curPosition );
            }
        }
    }
}
