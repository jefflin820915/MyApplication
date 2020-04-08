package com;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.myapplicationbankservicesdemo.actions.impl.BankBossImpl;
import com.example.myapplicationbankservicesdemo.actions.impl.BankWorkerImpl;
import com.example.myapplicationbankservicesdemo.actions.impl.NormalUserAIDLActionImpl;
import com.example.myapplicationbankservicesdemo.actions.impl.NormalUserImpl;

public class BankService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {

            if ("com.ACTION_NORMAL_USER".equals(action)) {
                return new NormalUserAIDLActionImpl();
            }else if ("com.ACTION_BANK_WORKER".equals(action)){
                return new BankWorkerImpl();
            }else if ("com.ACTION_BANK_BOSS".equals(action)){
                return new BankBossImpl();
            }
                return null;
        }

        return null;
    }
}
