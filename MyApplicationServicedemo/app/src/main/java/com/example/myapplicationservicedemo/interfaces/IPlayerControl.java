package com.example.myapplicationservicedemo.interfaces;

public interface IPlayerControl {

    //撥放狀態
    //撥放
    int PLAY_STATE_PLAY = 1;
    int PLAY_STATE_PAUSE = 2;
    int PLAY_STATE_STOP = 3;


    /**
     * 把UI的控制接口設置給邏輯層
     *
     * @param viewController
     */
    void registerViewController(IPlayerViewControl viewController);

    /**
     * 取消接口的註冊
     */
    void unregisterViewController();

    /**
     * 撥放音樂
     */
    void PlayOrPause();

    /**
     * 停止撥放
     */
    void Stop();

    /**
     * 設置撥放進度
     *
     * @param seek 撥放進度
     */
    void SeekTo(int seek);

}
