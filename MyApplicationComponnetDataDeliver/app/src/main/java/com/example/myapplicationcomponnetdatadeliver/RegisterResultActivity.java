package com.example.myapplicationcomponnetdatadeliver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.result );

        TextView result = findViewById( R.id.register_result );

        Intent intent = getIntent();

        if (intent != null) {
            String userNameValue = intent.getStringExtra( "userNameKey" );
            result.setText( userNameValue + "註冊成功" );
        }
    }
}
