package com.example.myapplicationbroadcastdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendBroadcastActivity extends AppCompatActivity {

    private EditText mBroadcastInputBox;
    private MessageReceiver mMessageReceiver;
    private AppStateChangeReceiver mAppStateChangeReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_send );

        initView();
        registerSendBroadvastMag();
        registerPackage();
    }

    private void registerPackage() {
        IntentFilter intentFilter = new IntentFilter(  );

        intentFilter.addAction( Intent.ACTION_PACKAGE_ADDED );
        intentFilter.addAction( Intent.ACTION_PACKAGE_REMOVED );

        mAppStateChangeReceiver = new AppStateChangeReceiver();

        this.registerReceiver( mAppStateChangeReceiver,intentFilter );
    }

    private void registerSendBroadvastMag() {

        IntentFilter intentFilter = new IntentFilter(  );
        intentFilter.addAction( Constents.ACTION_SEND_MSG);

        mMessageReceiver = new MessageReceiver();

        this.registerReceiver(mMessageReceiver,intentFilter);

    }

    private void initView() {
        mBroadcastInputBox = findViewById( R.id.send_broadcast_mag_input_box );

    }

    /**
     * @param view
     *
     * 點擊完這個方法被調用
     */
    public void sendBroadcastMsg(View view) {
        //被調用後,去發廣播
        String content = mBroadcastInputBox.getText().toString();
        Intent intent = new Intent(  );
        intent.setAction( Constents.ACTION_SEND_MSG );
        intent.putExtra( Constents.KEY_CONTENT,content );

        //發射廣播
        sendBroadcast( intent );


    }
}
