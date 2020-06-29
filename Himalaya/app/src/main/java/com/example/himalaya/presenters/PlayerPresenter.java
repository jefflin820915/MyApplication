package com.example.himalaya.presenters;

import com.example.himalaya.base.BaseApplication;
import com.example.himalaya.interfances.IPlayerCallBack;
import com.example.himalaya.interfances.IPlayerPresenter;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.util.List;

public class PlayerPresenter implements IPlayerPresenter {

    private XmPlayerManager mPlayerManager;

    private PlayerPresenter(){

        mPlayerManager = XmPlayerManager.getInstance(BaseApplication.getAppContext());
    }

    private static PlayerPresenter sPlayerPresenter;

    public static PlayerPresenter getPlayerPresenter(){
        if (sPlayerPresenter == null) {
            synchronized (PlayerPresenter.class){
                if (sPlayerPresenter == null) {
                    sPlayerPresenter = new PlayerPresenter();
                }
            }
        }
        return sPlayerPresenter;
    }

    private boolean isPlayListSet = false;

    public void setPlayList(List<Track> list,int playIndex){

        if (mPlayerManager !=null) {
            mPlayerManager.setPlayList(list,playIndex);
            isPlayListSet = true;
        }else{
            LogUtil.v("jeff","mPlayerManager is null");
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

    }

    @Override
    public void stop() {

    }

    @Override
    public void playPre() {

    }

    @Override
    public void playNext() {

    }

    @Override
    public void switchPlayMode(XmPlayListControl.PlayMode mode) {

    }

    @Override
    public void getPlayList() {

    }

    @Override
    public void playIndex(int index) {

    }

    @Override
    public void SeekTo(int progress) {

    }

    @Override
    public void registerViewCallBack(IPlayerCallBack iPlayerCallBack) {

    }

    @Override
    public void unRegisterViewCallBack(IPlayerCallBack iPlayerCallBack) {

    }
}
