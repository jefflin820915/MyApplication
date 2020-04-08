package com.example.myapplicationdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    /**
     *
     * @ context   上下文
     * @ name      數據庫名稱
     * @ factory   游標工廠
     * @ version   版本號碼
     */


    public DatabaseHelper(@Nullable Context context) {
        super( context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE );
    }


    /**
     *  Called when the database is created for the first time.
     *  第一次創建數據庫的時候被調用
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        //創建時的回調
        Log.v("brad","創建數據庫.........");
        //創建字段
        //sql create table table_name(_id integer,name varchar,age integer,salary integer);
        String sql = "create table " + Constants.TABLE_NAME + "(_id integer,name varchar,age integer,salary integer,phone integer)";
        db.execSQL( sql );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升級數據庫時的回調
        Log.v("brad","升級數據庫.........");

        String sql;

        switch (oldVersion){
            case 1:
                //添加address和phone字段
                sql = "alter table " + Constants.TABLE_NAME + " add address varchar";
                db.execSQL( sql );
                break;
            case 2:


               break;
        }

    }
}
