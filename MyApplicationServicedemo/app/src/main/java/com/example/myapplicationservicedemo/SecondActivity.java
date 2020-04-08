package com.example.myapplicationservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.myapplicationservicedemo.Service.SecondService;
import com.example.myapplicationservicedemo.interfaces.ICommunication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 服務的開啟方式有兩種:
 *
 *  1. startService()開啟服務--->stopService()來停止服務
 *  優點:服務可以長期於後台運行
 *  缺點:不能夠進行通訊
 *  //
 *  生命週期:
 *  最基本的生命週期
 *  onCreate()--->onStartCommand()--->onDestroy();
 *
 *  多次啟動服務的生命週期
 *  如果服務已經啟動了,那麼就不會再走onCreate方法了.除非執行了onDestroy();
 *  onCreate()--->onStartCommand()--.....-->onStartCommand()--->onDestroy();
 *
 *
 *  2. bindService()綁定服務,如果服務沒有啟動,自動啟動---->unBindService解榜服務
 *  優點:可以進行通訊
 *  缺點:不能長期於後台運行,如果不解榜則會發生洩漏leak,如果解榜了服務將停止運行.
 *  //生命週期
 *  onCreate();--->onBind();--->onUnBind();--->onDestroy();
 *  //混合開啟服務的生命週期:
 *  1. 開啟服務,然後去綁定服務,如果不取消綁定,那麼就無法停止服務
 *  2. 開啟服務以後是綁-解綁服務,服務不會被停止,只能通過stopService()來停止服務
 *  //
 *  推薦的混合開啟服務方式:
 *  1. 開啟服務-->為了確保服務可以長期於後台運行
 *  2. 綁定服務-->為了可以進行通訊
 *  3. 調用服務內部的方法-->該幹嘛就幹嘛,比如說,控制音樂的撥放,暫停,停止,快進...
 *  4. 退出Activity,要記得解綁服務
 *  5. 如果不使用服務了,要讓服務停止,那麼就調用stopService.
 *
 */


public class SecondActivity extends AppCompatActivity {

    private boolean mIsBind;
    private ICommunication mCommunication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second);


    }

    /**
     *
     * @param view
     * 開啟服務
     *
     */

    public void startServiceClick(View view) {

        //開啟服務
        Intent intent = new Intent(  );
        intent.setClass( this, SecondService.class );
        startService( intent );
    }


    /**
     * 停止服務
     * @param view
     */
    public void stopServiceClick(View view) {

        //停止服務
        Intent intent = new Intent(  );
        intent.setClass( this,SecondService.class );
        stopService( intent );
    }

    /**
     * 綁定服務
     * @param view
     */
    public void bindService(View view) {
        //綁定服務

        Intent intent = new Intent(  );
        intent.setClass( this,SecondService.class );

        //如果服務已經啟動,則不會再啟動服務,如果服務沒有啟動,則啟動服務.
        mIsBind = bindService( intent, mConnect, BIND_AUTO_CREATE );
    }

    private ServiceConnection mConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //服務綁定了
            mCommunication = (ICommunication) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            //服務解綁了
            mCommunication = null;
        }
    };


    /**
     * 解榜服務
     * @param view
     */
    public void unbindService(View view) {

        //解綁服務
        if (mIsBind && mConnect != null) {

            unbindService( mConnect );
            //
        }
    }

    /**
     *調用服務內部方法
     * @param view
     */
    public void callServiceMethod(View view) {

        //調用服務內部方法
        if (mCommunication!=null) {

            mCommunication.callServiceInnerMethod();
        }
    }
}
