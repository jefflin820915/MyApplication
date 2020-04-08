package com.example.myapplicationservicemusic2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    //創建實例對象LocalBinder賦予給IBinder類型名為mBinder的變數
    private IBinder mBinder = new LocalBinder();

    //創建一個Mediaplayer類型的變數名為mediaPlayer
    private MediaPlayer mediaPlayer;

    //創建一個Timer給一個變數名為timer
    private Timer timer;

    //IBinder不要直接實現,所以創Binder類來擴展,把Service傳出去
    public class LocalBinder extends Binder{

        //Myservice類型的getService方法
        MyService getService(){

            //把Myservice.this值傳回去
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        //建立綁定,傳回mBinder值.
        return mBinder;
    }

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
     timer.schedule(new UpdateTask(), 0,500 );
    }


   //更新事件類: UpdateTask類繼承TimerTask, 因TimerTask是抽象類所以須重寫TimerTask中的抽象方法run方法
    private class UpdateTask extends TimerTask{

       //重寫TimerTask中的抽象方法run方法
        @Override
        public void run() {

            //如果mediaPlayer不等於空並且mediaPlay正在撥放
            //就執行以下程序
            if(mediaPlayer != null&&mediaPlayer.isPlaying()) {

                //創建一個Intent名為intent,action值為fromService
                Intent intent = new Intent( "fromService" );

                //將數據添加到intent中(String name, Bundle value);  name名為now, mediaPlayer.getCurrentPosition 獲取當前播放位置
                intent.putExtra( "now", mediaPlayer.getCurrentPosition() );

                //將intent廣播給BoradcastReceviver接收
                sendBroadcast( intent );
            }
        }
    }
    public void playMusic(){
        if(mediaPlayer != null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }

    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
    public void stopMusic(){
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }
    public void setPosition(int position){
        mediaPlayer.seekTo( position );
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
