package com.example.himalaya.presenters;

import com.example.himalaya.interfances.IAlbumDetailPresenter;
import com.example.himalaya.interfances.IAlbumDetailViewCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailPresenter implements IAlbumDetailPresenter {

    private List<IAlbumDetailViewCallBack> mCallBacks = new ArrayList<>(  );

    private Album mTargetAlbum = null;

    private AlbumDetailPresenter() {

    }

    private static AlbumDetailPresenter sInstance = null;

    public static AlbumDetailPresenter getInstance() {

        if (sInstance == null) {
            synchronized (AlbumDetailPresenter.class){
                if (sInstance ==null) {
                    sInstance = new AlbumDetailPresenter();
                }
            }
        }
        return sInstance;
    }


    @Override
    public void pull2RefreshMore() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void getAlbumDetail(int albumId, int page) {

    }

    @Override
    public void registerViewCallBack(IAlbumDetailViewCallBack detailViewCallBack) {
        if (!mCallBacks.contains( detailViewCallBack )) {
            mCallBacks.add( detailViewCallBack );
            if (mTargetAlbum!=null) {
                detailViewCallBack.onAlbumLoaded( mTargetAlbum );
            }
        }
    }

    @Override
    public void unregisterViewCallBack(IAlbumDetailViewCallBack detailViewCallBack) {
        mCallBacks.remove( detailViewCallBack );
    }

    public void setTargetAlbum(Album targetAlbum){
        this.mTargetAlbum = targetAlbum;
    }


}
