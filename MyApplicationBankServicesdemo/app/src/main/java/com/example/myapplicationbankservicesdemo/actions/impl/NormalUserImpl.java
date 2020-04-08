package com.example.myapplicationbankservicesdemo.actions.impl;

import android.os.Binder;
import android.util.Log;

import com.example.myapplicationbankservicesdemo.actions.interfaces.INormalUserAction;

public class NormalUserImpl extends Binder implements INormalUserAction {
    @Override
    public void saveMoney(float money) {

        Log.v("brad","saveMoney ---> " + money);
    }

    @Override
    public float getMoney() {

        Log.v("brad","getMoney --> 100.00");
        return 100.00f;
    }

    @Override
    public float loanMoney() {

        Log.v("brad","loanMoney --> 100.00");
        return 100.00f;
    }
}
