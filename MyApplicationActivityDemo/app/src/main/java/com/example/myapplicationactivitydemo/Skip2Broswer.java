package com.example.myapplicationactivitydemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Skip2Broswer extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_skip );
    }

    /**
     * 這個方法可以在點擊按鈕的時候執行
     * @param view  這個是我們點擊的Button
     *              Component = com.google.android.apps.chrome.Main
     *              //其實就是插件的名稱,也就是ComponentName = 包名/類的全路徑名稱,類前面的包名,也就是這個類所在的包跟包名是一樣的
     *              //就可以省略成.    com.android.chrome
     *              //這是完整的寫法   com.google.android.apps.chrome.Main
     *
     *              //以下這種方式是顯示意圖跳轉到瀏覽器介面
     */

    public void skip2BroswerVisbale(View view){
        Log.v("brad","skip2Broswer");

        Intent intent = new Intent(  );

        //第一種寫法
//        intent.setClassName("com.android.chrome","com.google.android.apps.chrome.Main");

        //第二種寫法
        ComponentName componentName = new ComponentName("com.android.chrome","com.google.android.apps.chrome.Main" );
        intent.setComponent( componentName );



        startActivity( intent );

    }

    /**
     *
     * @param view
     *
     * 下面通過隱式意圖來跳轉到瀏覽器介面
     * 步驟:
     *      1.創建Intent對象
     *      2.給intent對象設置Action值,設置他的category值,如果是5.1以上的系統需要設置包名.
     *      3.startActivity來跳轉到另外一個介面
     */

    public void skip2BroswerInvisbale(View view) {

        Intent intent = new Intent(  );
        intent.setAction( "android.intent.action.SEARCH" );
        intent.addCategory( "android.intent.category.DEFAULT" );
        intent.setPackage( "com.android.browser" );

        startActivity( intent );

    }
}
