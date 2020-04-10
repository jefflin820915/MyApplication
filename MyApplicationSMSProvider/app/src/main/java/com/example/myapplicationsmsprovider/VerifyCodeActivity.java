package com.example.myapplicationsmsprovider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText mPhoneNumEt;
    private Button mCountDownEt;
    private EditText mVerifyCodeEt;
    private Button mSubmitBtn;

    public static final int MATCH_CODE = 1;

    private static UriMatcher sUriMatcher = new UriMatcher( UriMatcher.NO_MATCH );

    static {
        sUriMatcher.addURI( "sms", "#", MATCH_CODE );

    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(60*1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //到計時後變化
            mCountDownEt.setEnabled( false );
            mCountDownEt.setText( String.format( "重新獲取(%d)",millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            //結束時變化
            mCountDownEt.setEnabled( true );
            mCountDownEt.setText( String.format( "獲取驗證碼") );

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_verify_code );

        initView();
        initEvent();
        //註冊簡訊內容提供者的觀察者
        Uri uri = Uri.parse( "content://sms/" );
        getContentResolver().registerContentObserver( uri, true, new ContentObserver( new Handler() ) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {

                Log.v( "brad", "selfChange ----> " + selfChange );

                if (sUriMatcher.match( uri )==MATCH_CODE) {

                    Log.v( "brad", "uri ---> " + uri );
                    Cursor query = getContentResolver().query( uri,new String[]{"body"}, null, null, null, null );
                    if (query.moveToNext()) {
                        String body = query.getString( 0 );
                        Log.v( "brad", " body -----> " + body );
                        handlerBody(body);
                    }
                query.close();
                }
            }
        } );
    }

    private void handlerBody(String body) {
        if (!TextUtils.isEmpty( body ) && body.startsWith( "[prtic]" )) {
            Pattern p = Pattern.compile( "(?<![0-9])([0-9]{4})(?![0-9])" );
            Matcher matcher = p.matcher( body );
            boolean contain = matcher.find();
            if (contain) {
                String group = matcher.group();
                Log.v( "brad"," verifyCode ----> " + group);
                mVerifyCodeEt.setText( group);
                mVerifyCodeEt.setFocusable( true );
            }

        }
    }

    private void initEvent() {

        mCountDownEt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneTrim = mPhoneNumEt.getText().toString().trim();

                if (TextUtils.isEmpty( phoneTrim )) {

                    Toast.makeText( VerifyCodeActivity.this, "號碼不能為空", Toast.LENGTH_SHORT ).show();
                    return;
                }

                //向服務器請求發送驗證碼到手機
                mCountDownTimer.start();


            }
        } );

        mSubmitBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //檢查內容
                String phoneTrim = mPhoneNumEt.getText().toString().trim();
                String verifyCodeTrim = mVerifyCodeEt.getText().toString().trim();
                if (TextUtils.isEmpty( phoneTrim ) || TextUtils.isEmpty( verifyCodeTrim )) {

                    Toast.makeText( VerifyCodeActivity.this, "號碼與驗證碼不得為空", Toast.LENGTH_SHORT ).show();
                    return;
                }
                //向服務提交
            }
        } );
    }




    private void initView() {

        mPhoneNumEt = findViewById( R.id.phone_num_et );
        mCountDownEt = findViewById( R.id.count_down_et );
        mVerifyCodeEt = findViewById( R.id.verify_code_et );
        mSubmitBtn = findViewById( R.id.submit_btn );
    }
}
