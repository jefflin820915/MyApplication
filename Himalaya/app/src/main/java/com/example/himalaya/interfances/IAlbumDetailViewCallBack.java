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
     * 把album傳給UI
     *
     * @param album
     */
    void onAlbumLoaded(Album album);
}
