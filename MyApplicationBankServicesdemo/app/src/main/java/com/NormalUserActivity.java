package com;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationbankservicesdemo.NormalUserAction;
import com.example.myapplicationbankservicesdemo.R;
import com.example.myapplicationbankservicesdemo.actions.impl.NormalUserAIDLActionImpl;
import com.example.myapplicationbankservicesdemo.actions.interfaces.INormalUserAction;

public class NormalUserActivity extends AppCompatActivity {

    private NormalUserConnection mNormalUserConnection;
    private boolean mIsBind;
    private NormalUserAction mNormalUserAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);

        doBindService();

    }

    /**
     * 綁定服務
     */
    private void doBindService() {

        Log.v("brad","doBindService...");

        Intent intent = new Intent();
        intent.setAction("com.ACTION_NORMAL_USER");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.example.myapplicationbankservicesdemo");
        mNormalUserConnection = new NormalUserConnection();
        mIsBind = bindService(intent, mNormalUserConnection, BIND_AUTO_CREATE);

    }

    private class NormalUserConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v("brad","onServiceConnected.." + name);
            mNormalUserAction = NormalUserAction.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v("brad","onServiceDisconnected" + name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mIsBind && mNormalUserConnection != null) {
            unbindService(mNormalUserConnection);
            Log.v("brad","unbind service...");
            mNormalUserConnection = null;
            mIsBind = false;
        }

    }

    public void saveMoneyClick(View view){

    Log.v("brad","saveMoneyClick.. ----> " );

        try {
            mNormalUserAction.safeMoney(10000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getMoneyClick(View view){

        Log.v("brad","getMoneyClick..");

        try {
            float money = mNormalUserAction.getMoney();

            Log.v("brad","get money is ---> " + money);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loanMoneyClick(View view){


    Log.v("brad","loanMoneyClick..");
}

}
