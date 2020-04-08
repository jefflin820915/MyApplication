package com.example.myapplicationcomponnetdatadeliver;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText muserNameET;
    private EditText mpassWordET;
    private Button mregisterBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        initView();

        //給按鈕註冊一個點擊的監聽事件
        initListener();
    }

    private void initListener() {

        mregisterBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //處理註冊
                handleRegister();
            }
        } );
    }

    private void handleRegister() {
        String userName = muserNameET.getText().toString().trim();

        //這個方法理面有兩個判斷
        //1.判空, 2.判這個字符串的長度
        if (TextUtils.isEmpty( userName )) {
            Toast.makeText( this, "帳號不能為空...", Toast.LENGTH_SHORT ).show();
            return;
        }

        String passwordText = mpassWordET.getText().toString().trim();
        if (TextUtils.isEmpty( passwordText )) {
            Toast.makeText( this,"密碼不能為空...",Toast.LENGTH_SHORT ).show();
            return;
        }
        //可以註冊了...

        Intent intent = new Intent(this,RegisterResultActivity.class  );
        intent.putExtra( "userNameKey", userName);
        intent.putExtra( "passwordKey", passwordText );

        startActivity( intent );

        //結束當前介面
        this.finish();
    }

    private void initView() {
        muserNameET = findViewById( R.id.user_name_et );
        mpassWordET = findViewById( R.id.password_et );
        mregisterBtn = findViewById( R.id.register_btn );
    }
}
