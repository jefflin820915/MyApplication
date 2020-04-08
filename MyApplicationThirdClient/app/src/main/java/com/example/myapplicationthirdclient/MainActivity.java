package com.example.myapplicationthirdclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationalipay.PayResult;
import com.example.myapplicationalipay.ThirdPartPayAction;

public class MainActivity extends AppCompatActivity {

    private TextView mCountTv;
    private Button mBuyPoint;
    private AlipayConnect mAlipayConnect;
    private boolean mIsBind;
    private ThirdPartPayAction mThirdPartPayAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //綁定支付寶的服務,在現實開發中是由支付寶的SDK完成的

        binAlipayService();

        //找到控件
        initView();

        setListener();
    }


    /**
     * 綁定支付寶的實付
     *
     */
    private void binAlipayService() {

        Intent intent = new Intent();
        intent.setAction("com.example.myapplicationalipay.THIRD_PART_PAY");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.example.myapplicationalipay");


        mAlipayConnect = new AlipayConnect();
        mIsBind = bindService(intent, mAlipayConnect, BIND_AUTO_CREATE);

    }

    private class AlipayConnect implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //綁定
        Log.v("brad","onServiceConnected..." + service);
            mThirdPartPayAction = ThirdPartPayAction.Stub.asInterface(service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.v("brad","onServiceDisconnected..." + name);

        }
    }


    private void setListener() {
        mBuyPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //進行儲值

                try {
                    if (mThirdPartPayAction != null) {

                        mThirdPartPayAction.requestPay("儲值100",100.00f,new PaycallBack());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private class PaycallBack extends PayResult.Stub{

        @Override
        public void onPaySuccess() {
            //支付成功,修改UI上的內容
            //實際上是去修改資料庫,其實,支付寶是通過回調的URL地址,通知我們服務器
            mCountTv.setText("100");
            Toast.makeText(MainActivity.this,"儲值成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPayFail(int errorCode, String msg) {
            Log.v("brad","error code is ---> " + errorCode + "error msg --> " + msg);
            Toast.makeText(MainActivity.this,"儲值失敗",Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 初始化控件
     */

    private void initView() {

        mCountTv = findViewById(R.id.count_tv);
        mBuyPoint = findViewById(R.id.buy_point);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mIsBind && mAlipayConnect!=null) {
            Log.v("brad","unbindService...");
            unbindService(mAlipayConnect);
            mAlipayConnect = null;
            mIsBind = false;
        }

    }
}
