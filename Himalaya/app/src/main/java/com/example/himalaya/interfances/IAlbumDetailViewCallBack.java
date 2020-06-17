package com.example.himalaya.interfances;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IAlbumDetailViewCallBack {

    /**
     * 專輯詳情內容加載
     *
     * @param tracks
     */
    void onDetailListLoaded(List<Track> tracks);


    /**
     * 請求發生錯誤,顯示網路異常狀態
     */
    void onNetworkError(int errorCode, String errorMsg);




    /**
     * 把album傳給UI
     *
     * @param album
     */
    void onAlbumLoaded(Album album);
}
