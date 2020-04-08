package com.example.myapplicationactivityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 結果碼: resultCode
 * <p/>
 * 結果碼的作用: 用於區分結果,一般來說,一個數字表示一種結果
 * <p/>
 * 就好像我們的例子中
 * 2表示成功, 3表示失敗
 * <p/>
 * Activity裡面有一個常數,用於表示成功的是-1,失敗的是:0
 *
 */


public class PayActivity extends AppCompatActivity {


    private EditText minputBox;
    private Button mstartPayBtn;
    private Button mstopPayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pay );

        initView();

        initListener();
    }

    private void initView() {
        minputBox = findViewById( R.id.pay_input_box );
        mstartPayBtn = findViewById( R.id.start_pay_btn );
        mstopPayBtn = findViewById( R.id.stop_pay_btn );
    }

    private void initListener() {
        mstopPayBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleStop();
            }
        } );

        mstartPayBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handlePay();


            }
        } );

    }

    private void handleStop() {

        Intent intent = new Intent(  );
        intent.putExtra( "resultContent","儲值失敗" );

        setResult( 3, intent);
        finish();


    }

    private void handlePay() {
        String payNum = minputBox.getText().toString().trim();
        if (TextUtils.isEmpty( payNum )) {
            Toast.makeText( PayActivity.this, "請輸入儲值金額", Toast.LENGTH_SHORT ).show();
            return;
        }

        //進行網路訪問,進行儲值

        //setResult方法有兩個重載的方法,一個只有resultCode的,另一個還有Intent,
        Intent intent = new Intent();
        intent.putExtra( "resultContent", "儲值成功" );
        setResult( 2, intent );
        finish();
    }
}
