package com.example.myapplicationactivitylifecircle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_first );
        Log.v("brad","onCreate...");
    }

    /**
     * onStart已經可見,但沒有焦點,也就是沒有獲取到焦點,不可以進行操作
     */

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("brad","onStart...");

    }


    /**
     * onResume可見,並且獲取到焦點,也是說可以操作,可以進行交互
     */

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("brad","onResume...");

    }

    /**
     * onPause暫停的意思,這個方法其實是失去了焦點,不可以操作
     */

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("brad","onPause...");

    }

    /**
     * onStop已經不可見,
     */

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("brad","onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("brad","onDestroy...");

    }

    public void skip2Sec(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity( intent );
    }
}
