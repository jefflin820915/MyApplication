package com.example.himalaya.presenters;

import android.util.Log;

import com.example.himalaya.base.BaseApplication;
import com.example.himalaya.interfances.IPlayerCallBack;
import com.example.himalaya.interfances.IPlayerPresenter;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener;
import com.ximalaya.ting.android.opensdk.player.constants.PlayerConstants;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;

import java.util.ArrayList;
import java.util.List;

public class PlayerPresenter implements IPlayerPresenter, IXmAdsStatusListener, IXmPlayerStatusListener {


    private List<IPlayerCallBack> mIPlayerCallbacks = new ArrayList<>();


    private XmPlayerManager mPlayerManager;
    private Track mCurrentTrack;
    private int mCurrentIndex = 0;

    private PlayerPresenter() {

        mPlayerManager = XmPlayerManager.getInstance(BaseApplication.getAppContext());
        //廣告相關的接口
        mPlayerManager.addAdsStatusListener(this);

        //註冊播放器狀態相關的接口
        mPlayerManager.addPlayerStatusListener(this);

    }

    private static PlayerPresenter sPlayerPresenter;

    public static PlayerPresenter getPlayerPresenter() {
        if (sPlayerPresenter == null) {
            synchronized (PlayerPresenter.class) {
                if (sPlayerPresenter == null) {
                    sPlayerPresenter = new PlayerPresenter();
                }
            }
        }
        return sPlayerPresenter;
    }

    private boolean isPlayListSet = false;

    public void setPlayList(List<Track> list, int playIndex) {

        if (mPlayerManager != null) {
            mPlayerManager.setPlayList(list, playIndex);
            isPlayListSet = true;
            mCurrentTrack = list.get(playIndex);
            mCurrentIndex = playIndex;


        } else {
            LogUtil.v("jeff", "mPlayerManager is null");
        }
    }


    @Override
    public void play() {

        if (isPlayListSet) {
            mPlayerManager.play();
        }
    }

    @Override
    public void pause() {

        if (mPlayerManager != null) {

            mPlayerManager.pause();

        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void playPre() {
        //播放上一首
        if (mPlayerManager != null) {
            mPlayerManager.playPre();
        }
    }

    @Override
    public void playNext() {
        //播放下一首
        if (mPlayerManager != null) {
            mPlayerManager.playNext();
        }
    }

    @Override
    public void switchPlayMode(XmPlayListControl.PlayMode mode) {
        if (mPlayerManager != null) {

            mPlayerManager.setPlayMode(mode);

        }
    }

    @Override
    public void getPlayList() {

        if (mPlayerManager != null) {

            List<Track> playList = mPlayerManager.getPlayList();
            for (IPlayerCallBack mIPlayerCallback : mIPlayerCallbacks) {
                mIPlayerCallback.onListLoad(playList);
            }
        }
    }

    @Override
    public void playIndex(int index) {
        //切換播放器到第index的位置進行播放
        if (mPlayerManager != null) {

            mPlayerManager.play(index);

        }
    }

    @Override
    public void SeekTo(int progress) {

        //更新播放器的進度
        mPlayerManager.seekTo(progress);
    }

    @Override
    public boolean isPlay() {
        //返回當前是否正在播放

        return mPlayerManager.isPlaying();
    }

    @Override
    public void registerViewCallBack(IPlayerCallBack iPlayerCallBack) {

        iPlayerCallBack.onTrackUpdate(mCurrentTrack, mCurrentIndex);

        if (!mIPlayerCallbacks.contains(iPlayerCallBack)) {

            mIPlayerCallbacks.add(iPlayerCallBack);
        }
    }

    @Override
    public void unRegisterViewCallBack(IPlayerCallBack iPlayerCallBack) {

        mIPlayerCallbacks.remove(iPlayerCallBack);
    }


    //====================================== 廣告相關的回調方法 start ===============================
    @Override
    public void onStartGetAdsInfo() {

        LogUtil.v("jeff", "onStartGetAdsInfo");

    }

    @Override
    public void onGetAdsInfo(AdvertisList advertisList) {

        LogUtil.v("jeff", "onGetAdsInfo");

    }

    @Override
    public void onAdsStartBuffering() {

        LogUtil.v("jeff", "onAdsStartBuffering");

    }

    @Override
    public void onAdsStopBuffering() {

        LogUtil.v("jeff", "onAdsStopBuffering");

    }

    @Override
    public void onStartPlayAds(Advertis advertis, int i) {

        LogUtil.v("jeff", "onStartPlayAds");

    }

    @Override
    public void onCompletePlayAds() {

        LogUtil.v("jeff", "onCompletePlayAds");

    }

    @Override
    public void onError(int what, int extra) {

        LogUtil.v("jeff", "onError what = " + what + "onError extra = " + extra);

    }
    //====================================== 廣告相關的回調方法 end ===============================

    //

    //=====================================播放器相關的回調方法 start============================
    @Override
    public void onPlayStart() {

        LogUtil.v("jeff", "onPlayStart   ");

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {
            iPlayerCallback.onPlayStart();
        }
    }

    @Override
    public void onPlayPause() {

        LogUtil.v("jeff", "onPlayPause");

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {
            iPlayerCallback.onPlayPause();
        }

    }

    @Override
    public void onPlayStop() {

        LogUtil.v("jeff", "onPlayStop");

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {
            iPlayerCallback.onPlayStop();
        }

    }

    @Override
    public void onSoundPlayComplete() {

        LogUtil.v("jeff", "onSoundPlayComplete");

    }

    @Override
    public void onSoundPrepared() {

        LogUtil.v("jeff", "onSoundPrepared");
        LogUtil.v("jeff","status --> " + mPlayerManager.getPlayerStatus());

        if (mPlayerManager.getPlayerStatus()== PlayerConstants.STATE_PREPARED) {
            //播放器準備完了,可以播放
            mPlayerManager.play();
        }
    }

    @Override
    public void onSoundSwitch(PlayableModel lastModel, PlayableModel curModel1) {

        LogUtil.v("jeff", "onSoundSwitch...");

        if (lastModel != null) {

            LogUtil.v("jeff", "lastModel..." + lastModel.getKind());

        }
        LogUtil.v("jeff", "curModel1..." + curModel1.getKind());

        //curModel代表的是是當前播放的內容
        //如果通過getKind方法來獲取他是甚麼類型的
        //track表示的是track類型

        //第一種寫法: 不推薦
        // if ("track".equals(curModel1.getKind())) {
        //     Track currentTrack = (Track) curModel1;
        //     LogUtil.v("jeff","title...." +  currentTrack.getTrackTitle());
        // }

        //第二種寫法
        mCurrentIndex = mPlayerManager.getCurrentIndex();
        if (curModel1 instanceof Track) {
            Track currentTrack = (Track) curModel1;
            mCurrentTrack = currentTrack;

            LogUtil.v("jeff", "title..." + mCurrentTrack);
            //更新UI
            for (IPlayerCallBack mIPlayerCallback : mIPlayerCallbacks) {
                mIPlayerCallback.onTrackUpdate(mCurrentTrack, mCurrentIndex);
            }
        }

    }

    @Override
    public void onBufferingStart() {

        LogUtil.v("jeff", "onBufferingStart");

    }

    @Override
    public void onBufferingStop() {

        LogUtil.v("jeff", "onBufferingStop");

    }

    @Override
    public void onBufferProgress(int progress) {

        LogUtil.v("jeff", "onBufferProgress " + progress);

    }

    @Override
    public void onPlayProgress(int currentPos, int duration) {

        //單位是毫秒
        //LogUtil.v("jeff","  currentPos --> " + currentPos + "  duration -->" + duration);

        for (IPlayerCallBack iPlayerCallback : mIPlayerCallbacks) {

            iPlayerCallback.onProgressChange(currentPos, duration);
        }
    }

    @Override
    public boolean onError(XmPlayerException e) {

        LogUtil.v("jeff", "onError e ---> " + e);

        return false;
    }
    //=====================================播放器相關的回調方法 end============================


}
