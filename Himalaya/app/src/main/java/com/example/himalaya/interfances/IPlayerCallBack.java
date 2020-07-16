package com.example.himalaya.interfances;

import android.os.Trace;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.util.List;

public interface IPlayerCallBack {

    /**
     * 開始播放
     */

    void onPlayStart();

    /**
     * 播放暫停
     */

    void onPlayPause();

    /**
     * 播放停止
     */

    void onPlayStop();

    /**
     * 播放錯誤
     */

    void onPlayError();


    /**
     * 下一首播放
     */

    void onNextPlay(Track track);

    /**
     * 上一首播放
     */

    void onPrePlay(Track track);

    /**
     * 播放列表數據加載完成
     *
     * @param list 播放器列表數據
     */

    void onListLoad(List<Track> list);

    /**
     * 播放模式改變
     *
     * @param playMode
     */

    void onPlayModeChange(XmPlayListControl.PlayMode playMode);

    /**
     * 進度條改變
     *
     * @param currentProgress
     * @param total
     */

    void onProgressChange(int currentProgress, int total);

    /**
     * 廣告正在加載
     */

    void onAdLoading();


    /**
     * 廣告結束
     */

    void onAdFinished();


    /**
     * 更新當前節目
     * @param track 節目
     *
     */

    void onTrackUpdate(Track track,int playIndex);


}

