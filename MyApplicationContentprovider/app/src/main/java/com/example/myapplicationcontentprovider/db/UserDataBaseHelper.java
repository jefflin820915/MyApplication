package com.example.myapplicationcontentprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplicationcontentprovider.utils.Constant;

import androidx.annotation.Nullable;

public class UserDataBaseHelper extends SQLiteOpenHelper {
    public UserDataBaseHelper(@Nullable Context context) {
        super( context, Constant.DB_NAME,null,Constant.DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //創建數據庫
        String createSql = "create table " +
                Constant.TABLE_NAME +
                "(" + Constant.FIELD_ID + " integer primary key autoincrement,"+Constant.FIELD_USER_NAME+
                " varchar(30),"+Constant.FIELD_PASSWORD+
                " varchar(32), "+Constant.FIELD_SEX+
                " varchar(5),"+Constant.FIELD_AGE+" integer)";

        db.execSQL( createSql );
        Log.v( "brad","onCreate..." );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
