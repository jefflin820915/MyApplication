package com.example.himalaya.interfances;

public interface IRecommendPresenter {


    /**
     * 獲取推薦內容
     */
    void getRecommendList();


    /**
     * 下拉刷新更多內容
     */
    void pull2RefreshMore();


    /**
     * 上接刷新內容
     */
    void loadMore();

    /**
     * 這個方法用於註冊UI的回調
     *
     * @param callBack
     */
    void registerViewCallBack(IRecommendViewCallBack callBack);

    /**
     * 取消UI的回調註冊
     *
     * @param callBack
     */
    void unRegisterViewCallBack(IRecommendViewCallBack callBack);
}
