package com.example.himalaya.interfances;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

public interface IRecommendViewCallBack {

    /**
     * 獲取推薦內容的結果
     * @param result
     */
    void onRecommendListLoaded(List<Album> result);

    /**
     * 加載更多
     * @param result
     */
    void onLoadMore(List<Album> result);


    /**
     * 下接加載更多的結果
     * @param result
     */
    void onRefreshMore(List<Album> result);
}
