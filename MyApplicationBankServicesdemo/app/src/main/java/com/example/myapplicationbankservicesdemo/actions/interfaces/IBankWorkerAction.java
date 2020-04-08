package com.example.myapplicationbankservicesdemo.actions.interfaces;

public interface IBankWorkerAction extends INormalUserAction{

    //查詢用戶信用
    void checkUserCredit();

    //凍結用戶
    void FreezeUserAccount();
}
