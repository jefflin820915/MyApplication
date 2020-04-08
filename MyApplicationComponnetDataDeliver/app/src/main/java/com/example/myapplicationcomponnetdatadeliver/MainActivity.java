package com.example.myapplicationcomponnetdatadeliver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 *   第一個,實現基本數據類型的傳遞
 *   第二個,把引用數據類型,也就是把一個對象傳到另外一個介面
 *
 */

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        callPermission();

    }

    private void callPermission() {

        if (ContextCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE );
        }
    }


    /**
     *  @param view
     *
     *  這個方法用於跳轉到第二個介面
     */
    public void skip2sec(View view) {

        //創建意圖對象
        Intent intent = new Intent( this, SecActivity.class );
        intent.putExtra( "intKey", 100 );
        intent.putExtra( "booleanKey", true );
        //.....


        startActivity( intent );

    }

    /**
     * @param view
     * 我們把一個對象傳給第二個介面
     */
    public void deliverObject(View view) {

        /**
         * 步驟: 1.和前面一樣先實現介面的跳轉
         *      2.創建對象,所創建的對像要實現Parcelable接口
         *      3.把這個對象以putExtra的方式扔進去,並且需要一個key
         *      4.在第二介面,透過key來獲取可傳遞的對像
         */

        Intent intent = new Intent( this, SecActivity.class );

        User user = new User();
        user.setName( "TrilGates" );
        user.setAge( 25 );
        user.setTall( 168.9f );


        Bitmap bitmap = null;
        intent.putExtra( "bitMapKey", bitmap );
        intent.putExtra( "stringKey", "String Value" );
        intent.putExtra( "UserKey", user );

        startActivity( intent );

    }

    public void skip2Login(View view) {

        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );
    }

    /**
     * @param view
     * 打電話給10086
     */
    public void call(View view) {

        Intent intent = new Intent( Intent.ACTION_CALL, Uri.parse( "tel:10086" ) );

        //intent.setAction( "android.intent.action.CALL" );
//        intent.setAction( intent.ACTION_CALL );
//        intent.addCategory( "android.intent.category.DEFAULT" );
//
//        Uri uri = Uri.parse( "tel:10086" );
//        intent.setData( uri );

        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity( intent );
    }

    public void sendMsg(View view) {

        Intent intent = new Intent(  );

        intent.setAction( "com.example.myapplicationcomponnetdatadeliver.SEND.MSG" );
        intent.addCategory( intent.CATEGORY_DEFAULT );


        intent.putExtra( "targetNumKey","10086" );
        intent.setData( Uri.parse( "msg:幫我查詢一下電話費" ) );
        //scheme:後面的內容...

        startActivity( intent );

    }
}
