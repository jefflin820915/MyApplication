package com.example.myapplicationactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *  為什麼有兩種意圖方式來實現跳轉:
 *
 *  顯示意圖: 一般用於來應用內的組件跳轉.
 *  隱式意圖: 一般用於應用之間的跳轉,第三方運用的跳轉.
 *
 */

public class MainActivity extends AppCompatActivity {

    private EditText maccount;
    private EditText mpassword;
    private Button mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initView();

        initListen();

    }

    private void initListen() {
        mlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //這裡面也就是登入按鈕被點擊
                Log.v("brad","login...click");
                handleLogin();


            }
        } );

    }

    private void handleLogin() {
        String accountText = maccount.getText().toString();
        if (TextUtils.isEmpty( accountText )) {
            Toast.makeText( this,"輸入的帳號為空",Toast.LENGTH_SHORT ).show();
            return;
        }

        String passwordText = mpassword.getText().toString().trim();
        if (TextUtils.isEmpty( passwordText )) {
            Toast.makeText( this,"輸入的密碼為空",Toast.LENGTH_SHORT ).show();
            return;
        }
        //有密碼有帳號就把數據傳到另一個介面
        //先要創建一個意圖對象,然後通過startActivity來跳轉
        //這部分是使用意圖跳轉到另一個Activity

        Intent intent = new Intent( );

        String packageName = this.getPackageName();
        String targetActivityName = SecondActivity.class.getName();

        intent.setClassName(packageName,targetActivityName);

        Log.v( "brad","package--> " + packageName + "name---> " + targetActivityName );


        intent.putExtra("account",accountText );
        intent.putExtra( "password",passwordText );
        startActivity( intent );

        //下面使用隱式意圖的方式來啟動另一個Activity
//        Intent intent = new Intent(  );
//        intent.setAction( "com.example.LOGIN_INFO" );
//        intent.addCategory( intent.CATEGORY_DEFAULT );
//        intent.putExtra("account",accountText );
//        intent.putExtra( "password",passwordText );
//        startActivity( intent );

    }

    private void initView() {

        maccount = findViewById( R.id.account );
        mpassword = findViewById( R.id.password );
        mlogin = findViewById( R.id.login );


    }

    public void browserpage(View view) {
        Intent intent = new Intent(  );
        intent.setClass( this,Skip2Broswer.class );
        startActivity( intent );
    }
}
