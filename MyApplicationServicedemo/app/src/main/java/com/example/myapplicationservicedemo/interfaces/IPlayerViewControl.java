package com.example.myapplicationservicedemo.interfaces;

public interface IPlayerViewControl {

    /**
     * 撥放狀態的通知
     *
     * @param state
     */
    void onPlayStateChange(int state);

    /**
     * 撥放進度的改變
     *
     * @param seek
     */
    void onSeekChange(int seek);

}
