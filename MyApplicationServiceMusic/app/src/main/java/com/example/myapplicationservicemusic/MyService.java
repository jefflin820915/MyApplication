package com.example.myapplicationservicemusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    //創建一個Mediaplayer類型的變數名為mediaPlayer
    private MediaPlayer mediaPlayer;

    //創建int類型常數(final修飾與static聯合使用)
    //ACTION_PLAY = 1;
    //ACTION_PAUSE = 2;
    //ACTION_SEEK = 3;
    public static final int ACTION_PLAY = 1;
    public static final int ACTION_PAUSE = 2;
    public static final int ACTION_SEEK = 3;

    //創建一個Timer給一個變數名為timer
    public Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
         return null;
    }

    //Service生命週期 onCreate() -> onStartCommand() -> Service running --service stopped-> onDestroy() -> service shutdown
    //重寫Service生命週期onCreate方法
    @Override
    public void onCreate() {
        super.onCreate();

        //給mediaPlayer變數 使用create()方法創建MediaPlayer
        mediaPlayer = MediaPlayer.create( this,R.raw.test1);

        //創建一個Intent,action值為fromService
        Intent intent = new Intent("fromService");
        //將數據添加到intent中(String name, Bundle value);  name為max, mediaPlay.getDuration();方法為獲取文件持續時間
        intent.putExtra( "max",mediaPlayer.getDuration() );

        //將intent廣播給BoradcastReceviver接收
        sendBroadcast( intent );

        //創建Timer實例對象賦予timer變數
        timer = new Timer(  );

        //使用schedule方法創建UpdateTask對象,
        //schedule方法:在指定的延遲後開始重複執行固定延遲的指定任務
        timer.schedule( new UpdateTask(),0,500 );
    }

    //更新事件類: UpdateTask類繼承TimerTask, 因TimerTask是抽象類所以須重寫TimerTask中的抽象方法run方法
    private class UpdateTask extends TimerTask{

        //重寫TimerTask中的抽象方法run方法
        @Override
        public void run() {

            //如果mediaPlayer不等於空並且mediaPlay正在撥放
            if(mediaPlayer != null&&mediaPlayer.isPlaying()){
                //就執行以下程序

                //創建一個Intent名為intent,action值為fromService
                Intent intent = new Intent( "fromService" );

                //將數據添加到intent中(String name, Bundle value);  name名為now, mediaPlayer.getCurrentPosition 獲取當前播放位置
                intent.putExtra( "now",mediaPlayer.getCurrentPosition() );

                //將intent廣播給BoradcastReceviver接收
                sendBroadcast( intent );
            }
        }
    }

    //重寫Service生命週期onStartCommand方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //給一個int變數名為action,getIntExtra方法從intent中查找action,沒有使用action的話返回預設值-1
        int action = intent.getIntExtra( "action",-1 );

        //switch分支語句, 以action值做判斷
        //分支3個動作, 分別為ACTION_PLAY,ACTION_PAUSE,ACTION_SEEK
        switch (action){
            //分支1 發生分支1 常數為ACTION_PLAY = 1的話 執行分支1的程序
            case ACTION_PLAY:
                //如果播放器沒有在播放的話 , 就讓播放器播放
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            //分支2 發生分支2常數為ACTION_PLAY = 2 的話, 執行分支2程序
            case ACTION_PAUSE:
                //如果播放器在撥放中的話,就讓播放器暫停
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            //分支3 發生分支3常數為ACTION_SEEK = 3 的話, 執行分支3程序
            case ACTION_SEEK:
                //創建一個int類型的變數名為where, getIntExtra方法從intent中查找where,沒有使用action的話返回預設值-1
                int where = intent.getIntExtra( "where",-1 );
                //如果變數where大於等於0的話
                if(where>=0){
                    //使用MediaPlayer裡的seekTo方法來尋求指定的時間位置
                    mediaPlayer.seekTo( where );
                }
                break;
        }
        //返回父類的onStartCommand( intent, flags, startId );
        return super.onStartCommand( intent, flags, startId );

    }

    //重寫Service生命週期onDestory方法
    @Override
    public void onDestroy() {
        super.onDestroy();

        //如果播放器不等於空值,就往下執行
        if (mediaPlayer != null){
            //如果播放器是播放中,往下執行
            if (mediaPlayer.isPlaying())

            //撥放器就停掉
            mediaPlayer.stop();

            //釋放Mediaplay的資源
            mediaPlayer.release();
            mediaPlayer = null;
        }

        //如果timer不等於null
        if(timer!=null){
            //終止timer
            timer.cancel();
            //釋放timer的資源
            timer.purge();
            timer = null;
        }
    }
}
