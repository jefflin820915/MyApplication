package com.example.myapplicationbankservicesdemo.actions.interfaces;

public interface IBankBossAction extends IBankWorkerAction {

    //修改金額
    void modifyUserAccountMoney(float money);

}
