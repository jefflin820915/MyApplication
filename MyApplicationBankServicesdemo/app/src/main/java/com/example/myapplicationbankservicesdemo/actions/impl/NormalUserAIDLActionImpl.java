package com.example.myapplicationbankservicesdemo.actions.impl;

import android.os.RemoteException;
import android.util.Log;

import com.example.myapplicationbankservicesdemo.NormalUserAction;

public class NormalUserAIDLActionImpl extends NormalUserAction.Stub {

    @Override
    public void safeMoney(float money) throws RemoteException {

        Log.v("brad","normal user save money ---> " + money);
    }

    @Override
    public float getMoney() {
        return 10000;
    }

}
