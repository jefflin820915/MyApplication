package com.example.himalaya.presenters;

import android.util.Log;

import com.example.himalaya.interfances.IAlbumDetailPresenter;
import com.example.himalaya.interfances.IAlbumDetailViewCallBack;
import com.example.himalaya.utils.Constants;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumDetailPresenter implements IAlbumDetailPresenter {

    private List<IAlbumDetailViewCallBack> mCallBacks = new ArrayList<>();

    private Album mTargetAlbum = null;

    private AlbumDetailPresenter() {

    }

    private static AlbumDetailPresenter sInstance = null;

    public static AlbumDetailPresenter getInstance() {

        if (sInstance == null) {
            synchronized (AlbumDetailPresenter.class) {
                if (sInstance == null) {
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
        //去根據頁碼和album獲取列表
        Map<String, String> map = new HashMap<>();
        map.put( DTransferConstants.ALBUM_ID, albumId + "" );
        map.put( DTransferConstants.SORT, "asc" );
        map.put( DTransferConstants.PAGE, page + "" );
        map.put( DTransferConstants.PAGE_SIZE, Constants.COUNT_DEFAULT + "" );
        CommonRequest.getTracks( map, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(TrackList trackList) {

                LogUtil.v( "jeff", "current Thread --> " + Thread.currentThread().getName() );

                if (trackList != null) {
                    List<Track> tracks = trackList.getTracks();
                    LogUtil.v( "jeff", "tracks size --> " + tracks.size() );
                    handlerAlbumDetailResult( tracks );

                }
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                LogUtil.v( "jeff", "errorCode --> " + errorCode );
                LogUtil.v( "jeff", "errorMsg --> " + errorMsg );
                handlerError( errorCode, errorMsg );
            }
        } );
    }


    /**
     * 如果是發生錯誤,那麼就通知UI
     * @param errorCode
     * @param errorMsg
     */
    private void handlerError(int errorCode, String errorMsg) {
        for (IAlbumDetailViewCallBack mCallBack : mCallBacks) {
            mCallBack.onNetworkError( errorCode, errorMsg );
        }
    }

    private void handlerAlbumDetailResult(List<Track> tracks) {

        for (IAlbumDetailViewCallBack mCallBack : mCallBacks) {
            mCallBack.onDetailListLoaded( tracks );
        }
    }

    @Override
    public void registerViewCallBack(IAlbumDetailViewCallBack detailViewCallBack) {
        if (!mCallBacks.contains( detailViewCallBack )) {
            mCallBacks.add( detailViewCallBack );
            if (mTargetAlbum != null) {
                detailViewCallBack.onAlbumLoaded( mTargetAlbum );
            }
        }
    }

    @Override
    public void unregisterViewCallBack(IAlbumDetailViewCallBack detailViewCallBack) {
        mCallBacks.remove( detailViewCallBack );
    }

    public void setTargetAlbum(Album targetAlbum) {
        this.mTargetAlbum = targetAlbum;
    }


}
