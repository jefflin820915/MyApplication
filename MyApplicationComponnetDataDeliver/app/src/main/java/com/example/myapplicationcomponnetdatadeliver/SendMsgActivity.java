package com.example.myapplicationcomponnetdatadeliver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendMsgActivity extends AppCompatActivity {

    private EditText receiverPhoneNumET;
    private EditText msgContentET;
    private Button sendMsgBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_msm );

        initView();

        Intent intent = getIntent();
        if (intent != null) {
            String targetNumKeyValue = intent.getStringExtra( "targetNumKey" );
            Log.v( "brad","number---> " + targetNumKeyValue );
            receiverPhoneNumET.setText( targetNumKeyValue );
            Uri data = intent.getData();
            Log.v("brad","data---> " +data);
            if (data != null) {
                String content = data.toString().replace( "msg:", "" );
                msgContentET.setText( content );
            }
        }
    }

    private void initView() {

        receiverPhoneNumET = findViewById( R.id.recever_phone_number_et );
        msgContentET = findViewById( R.id.msg_content_et );
        sendMsgBtn = findViewById( R.id.send_msg_btn );

    }
}
