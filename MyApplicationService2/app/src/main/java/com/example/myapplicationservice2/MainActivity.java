package com.example.myapplicationservice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView msg;
    private MyService myService;
    private boolean mBound;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentname, IBinder iBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder)iBinder;
            myService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        msg = findViewById( R.id.msg );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent( this,MyService.class );
        bindService( intent, mConnection, Context.BIND_AUTO_CREATE );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound){
            unbindService( mConnection );
            mBound = false;
        }
    }

    public void test1(View view) {
        if(mBound && myService!= null){
            int lottery = myService.doSome();
            msg.setText( "" + lottery );
        }
    }
}
