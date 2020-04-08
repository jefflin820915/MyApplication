package com.example.myapplicationbankservicesdemo.actions.interfaces;

public interface INormalUserAction {

    //存錢
    void saveMoney(float money);

    //取錢
    float getMoney();

    //貸款
    float loanMoney();

}
