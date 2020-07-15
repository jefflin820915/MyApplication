package com.example.himalaya.interfances;

import com.example.himalaya.base.IBasePresenter;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

public interface IPlayerPresenter extends IBasePresenter<IPlayerCallBack> {

    /**
     * 播放
     */

    void play();

    /**
     * 暫停
     */

    void pause();

    /**
     * 停止
     */

    void stop();

    /**
     * 上一首
     */

    void playPre();

    /**
     * 下一首
     */
    void playNext();

    /**
     * 切換播放模式
     *
     * @param mode
     */

    void switchPlayMode(XmPlayListControl.PlayMode mode);

    /**
     * 獲取播放列表
     */

    void getPlayList();

    /**
     * 根據節目的位置進行播放
     *
     * @param index 節目在列表中的位置
     */

    void playIndex(int index);


    /**
     * 切換播放進度
     *
     * @param progress
     */

    void SeekTo(int progress);


    /**
     * 判斷播放器是否在播放
     *
     * @return
     */
    boolean isPlay();

}
