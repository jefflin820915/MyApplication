package com.example.myapplicationtestprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContentResolverCompat;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String FIELD_USER_NAME = "userName";

    public static final String FIELD_PASSWORD = "password";

    public static final String FIELD_SEX = "sex";

    public static final String FIELD_AGE = "age";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ContentResolver contentResolver = getContentResolver();

        Uri uri = Uri.parse("content://com.example.myapplicationcontentprovider");

        contentResolver.registerContentObserver( uri, true, new ContentObserver(new Handler(  ) ) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange( selfChange );

                    Log.v( "brad","用戶數據變化" );

                    //獲取內容
            }
        } );
    }

    /**
     *  點擊的時候去獲取
     *
     */
    public void getUserRemote(View view) {

        ContentResolver contentResolver = getContentResolver();

        Uri uri = Uri.parse("content://com.example.myapplicationcontentprovider");
        Cursor cursor = contentResolver.query( uri, null,"userName=?",new String[]{"ceddy"}, null );
//      Cursor cursor = contentResolver.query( uri, null,null,null, null );

        String[] columnNames = cursor.getColumnNames();
        for (String columnName : columnNames) {
            Log.v( "brad","comoumName ---> " + columnName );

        }

        while (cursor.moveToNext()) {

                Log.v( "brad","==============================" );
            for (String columnName : columnNames) {

                String value = cursor.getString( cursor.getColumnIndex( columnName ) );

                Log.v( "brad",columnName + "-----" + value);



            }
                Log.v( "brad","==============================" );
        }
        cursor.close();

    }

    public void addUser(View view) {

        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse( "content://com.example.myapplicationcontentprovider" );

        ContentValues values = new ContentValues(  );

        values.put( FIELD_USER_NAME,"sherry");
        values.put( FIELD_PASSWORD,"12345");
        values.put( FIELD_SEX,"male");
        values.put( FIELD_AGE,"24");



        contentResolver.insert( uri,values);
    }
}
