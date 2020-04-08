package com.example.myapplicationnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private NotificationManager nm;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        nm = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );


    }

    public void test1(View view){

        builder = new NotificationCompat.Builder(this,"default");
        builder.setContentTitle( "重要通知" );
        builder.setContentText( "通知內容" );
        builder.setSmallIcon( R.drawable.ic_stat_name );
        builder.build();

        nm.notify( 1,builder.build());
    }

    public void test2(View view){

        builder = new NotificationCompat.Builder(this);
    }

    public void test3(View view){

        builder = new NotificationCompat.Builder(this);
    }



}
