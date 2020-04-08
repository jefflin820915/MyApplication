package com.example.myapplicationservicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.myapplicationservicedemo.presenter.PlayerPresenter;

import androidx.annotation.Nullable;

public class PlayerService extends Service {

    private PlayerPresenter mPlayerPresenter;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v( "brad","onCreate.." );
        if (mPlayerPresenter == null) {

            mPlayerPresenter = new PlayerPresenter();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.v( "brad","onBind.." );
        return mPlayerPresenter;
    }

    @Override
    public void onDestroy() {

        Log.v( "brad","onDestroy" );
        super.onDestroy();

        mPlayerPresenter = null;
    }
}
