package com.example.himalaya.base;

public interface IBasePresenter<T> {

    /**
     * 註冊UI的回調接口
     * @param t
     */

    void registerViewCallBack(T t);


    /**
     * 取消註冊
     * @param t
     */

    void unRegisterViewCallBack(T t);
}
