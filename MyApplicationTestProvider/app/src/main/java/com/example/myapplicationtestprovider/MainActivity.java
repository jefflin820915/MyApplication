package com.example.myapplicationtestprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

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

    }
