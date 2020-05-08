package com.example.himalaya.interfances;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

public interface IRecommendViewCallBack {

    /**
     * 獲取推薦內容的結果
     *
     * @param result
     */
    void onRecommendListLoaded(List<Album> result);

    /**
     * 網路錯誤
     */
    void onNetworkError();

    /**
     * 數據為空
     */
    void onEmpty();

    /**
     * 正在加載
     */
    void onLoading();
}
