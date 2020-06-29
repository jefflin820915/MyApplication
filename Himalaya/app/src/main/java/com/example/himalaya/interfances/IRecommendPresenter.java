package com.example.himalaya.interfances;

import com.example.himalaya.base.IBasePresenter;

public interface IRecommendPresenter extends IBasePresenter<IRecommendViewCallBack> {


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

}
