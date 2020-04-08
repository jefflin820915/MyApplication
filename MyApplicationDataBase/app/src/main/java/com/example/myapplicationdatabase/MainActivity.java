package com.example.myapplicationdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        /*
            創建數據庫
            1.寫一個類去繼承SqliteOpenhelper
            2.實現裡面的方法, 創建構造方法
                參數數據:
                 * @ context   上下文
                 * @ name      數據庫名稱
                 * @ factory   游標工廠
                 * @ version   版本號碼
            3.創建個子類對象,再調用getReadableDatabase()/getWriteableDatabase()方法,即可創建數據庫

            SQL: 創建數據庫: create database 數據庫名稱 [character set 編碼]

         */

        DatabaseHelper databaseHelper = new DatabaseHelper( this );
        databaseHelper.getWritableDatabase();

    }

}
