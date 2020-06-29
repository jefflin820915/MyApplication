package com.example.himalaya.interfances;

import com.example.himalaya.base.IBasePresenter;

public interface IAlbumDetailPresenter extends IBasePresenter<IAlbumDetailViewCallBack> {

    /**
     * 下拉刷新更多內容
     */
    void pull2RefreshMore();


    /**
     * 上接刷新內容
     */
    void loadMore();


    /**
     * 獲取專輯詳情
     *
     * @param albumId
     * @param page
     */
    void getAlbumDetail(int albumId, int page);

}
