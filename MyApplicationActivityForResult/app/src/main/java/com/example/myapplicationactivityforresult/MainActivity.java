package com.example.myapplicationactivityforresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 透過這個案例來數據回傳.
 * <p>
 * 點擊儲值按鈕,跳轉到第二個介面
 * 第二個介面進行儲值,儲值完成以後告訴第一個介面結果: 包括儲值成功,儲值失敗.
 */

public class MainActivity extends AppCompatActivity {

    private Button mreCharge;
    private TextView mpayResult;
    private static final int PAY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initView();

        initListener();
    }

    private void initListener() {
        mreCharge.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //設置一個點擊事件,跳轉到第二個介面,進行儲值

                Intent intent = new Intent( MainActivity.this, PayActivity.class );

                //startActivity( intent );

                //第一步,使用startAcitivityforResult代替原來的startActivity
                startActivityForResult( intent, 1 );

            }
        } );
    }

    private void initView() {
        mreCharge = findViewById( R.id.recharge );
        mpayResult = findViewById( R.id.pay_result );
    }


    /**
     * 返回結果會在這邊回調
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        String resultContent = null;

        if (requestCode == PAY_REQUEST_CODE) {
            if (resultCode== 2) {
                //儲值成功
                resultContent = data.getStringExtra( "resultContent" );

            }else if (resultCode == 3){
                //儲值失敗
                resultContent = data.getStringExtra( "resultContent" );
            }

            mpayResult.setText( resultContent );
        }

    }
}
