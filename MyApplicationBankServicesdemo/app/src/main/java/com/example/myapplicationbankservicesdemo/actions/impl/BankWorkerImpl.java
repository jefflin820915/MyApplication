package com.example.myapplicationbankservicesdemo.actions.impl;

import android.os.Binder;
import android.util.Log;

import com.example.myapplicationbankservicesdemo.actions.interfaces.IBankWorkerAction;

public class BankWorkerImpl extends Binder implements IBankWorkerAction {
    @Override
    public void checkUserCredit() {

        Log.v("brad","checkUserCredit --> 790");
    }

    @Override
    public void FreezeUserAccount() {

        Log.v("brad","FreezeUser --> 0 ");
    }

    @Override
    public void saveMoney(float money) {

        Log.v("brad"," saveMoney --> " + money);
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
