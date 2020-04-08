package com.example.myapplicationdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Dao類用於來操作數據庫的增刪改查
 */

public class Dao {

    private DatabaseHelper databaseHelper;

    public Dao(Context context) {

        //創建數據庫
        databaseHelper = new DatabaseHelper(context );

         }
         public void insert(){
             SQLiteDatabase db = databaseHelper.getWritableDatabase();

//             String sql = "insert into "+ Constants.TABLE_NAME + "(_id,name,age,salary,phone,address) values(?,?,?,?,?,?)";

//             db.execSQL( sql,new Object[]{1,"BillGates",60,1,110,"USA"} );

             ContentValues contentValues = new ContentValues(  );

            //添加數據
             contentValues.put( "_id",2 );
             contentValues.put( "name","larrypae" );
             contentValues.put( "age",20 );
             contentValues.put( "salary",2 );
             contentValues.put( "phone",1290 );
             contentValues.put( "address","USA" );

             db.insert( Constants.TABLE_NAME,null,contentValues );
             db.close();
    }
        public void delete(){
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

//            String sql = "delete from "+ Constants.TABLE_NAME + " where age = 60";
//
//            db.execSQL( sql);

            int delete = db.delete( Constants.TABLE_NAME, null, null );

            Log.v("brad", "delete result -->  " + delete);
            db.close();

        }
        public void update(){
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

//            String sql = "update "+ Constants.TABLE_NAME + " set salary = 2 where age = 60";
//
//            db.execSQL( sql);

            ContentValues contentValues = new ContentValues(  );
            contentValues.put( "phone",123456789 );

            db.update( Constants.TABLE_NAME,contentValues,null,null );
            db.close();

        }
        public void query(){
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

//            String sql = "select * from "+ Constants.TABLE_NAME;
//
//            Cursor cursor = db.rawQuery( sql, null );
//
//            while (cursor.moveToNext()){
//
//                int index = cursor.getColumnIndex( "name" );
//                String name = cursor.getString( index );
//                Log.v("brad","name ---> " + name );
//            }
//
//            cursor.close();

            Cursor cursor = db.query( false, Constants.TABLE_NAME, null, null, null, null, null, null, null, null );
            while (cursor.moveToNext()){
                int id = cursor.getInt( 0 );
                String name = cursor.getString( 1 );
                Log.v( "brad","name ---> " + name + "  id ---> " + id );

            }

            cursor.close();
            db.close();

        }


}
