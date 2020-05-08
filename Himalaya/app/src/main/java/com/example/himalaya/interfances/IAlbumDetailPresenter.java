package com.example.himalaya.interfances;

public interface IAlbumDetailPresenter {

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


    /**
     * 註冊UI通知的接口
     *
     * @param detailViewCallBack
     */
    void registerViewCallBack(IAlbumDetailViewCallBack detailViewCallBack);

    /**
     * 刪除UI通知接口
     *
     * @param detailViewCallBack
     */
    void unregisterViewCallBack(IAlbumDetailViewCallBack detailViewCallBack);
}
