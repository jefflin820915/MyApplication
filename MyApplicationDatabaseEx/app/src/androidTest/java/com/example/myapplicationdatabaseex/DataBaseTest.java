package com.example.myapplicationdatabaseex;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import junit.framework.TestCase;

import androidx.test.platform.app.InstrumentationRegistry;


public class DataBaseTest extends TestCase {

    /**
     * 數據庫事務
     *         db.beginTransaction();        db.endTransaction();
     *          1.安全性
     *              情景:
     *                 公司每個月發薪,
     *                 過程:
     *                      公司財務1000000 , 那要-12000
     *                      你的帳戶需增減12000
     *
     *          2.高效性
     *              對比耗時
     *
     *              使用普通的形式向數據添加3000筆數據,
     *              Time ---> 10576
     *              開數據庫, 插入數據庫,關閉數據庫(耗時較多)
     *
     *              使用開啟事務的形式添加3000筆數據,
     *              Time ---> 484
     *              數據存到內存,然後一次寫入數據庫裡.
     *
     */

    public void testDataBase(){

        //測試數據庫創建

        DataBaseHelper dataBaseHelper = new DataBaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        dataBaseHelper.getReadableDatabase();


    }

    public void testinsert(){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        long start = System.currentTimeMillis();

        db.beginTransaction();
        for (int i = 0; i < 3000; i++) {

            db.execSQL( "insert into account values(1,'company',1000000)" );
            db.execSQL( "insert into account values(2,'my_count',0)" );

        }
        db.endTransaction();

        Log.v("brad","Time ---> " + (System.currentTimeMillis() - start));

        db.close();
    }

    public void testupdate(){

        DataBaseHelper dataBaseHelper = new DataBaseHelper( InstrumentationRegistry.getInstrumentation().getTargetContext());
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        db.beginTransaction();

        try{

            //在這裡面轉帳, 其中這個公司的帳號-12000, 個人帳號+12000
            db.execSQL( "update account set money = 1000000-12000 where name = 'company'");

            //在這裡我們讓他發生異常,假造災難,讓公司扣錢,我的帳戶沒有進帳
            //int i = 10/0;

            db.execSQL( "update account set money = 12000 where name = 'my_count'");

            db.setTransactionSuccessful();

        }catch (Exception e){
            //處理異常
            throw  new RuntimeException( "停電了" );
        }finally {
            db.endTransaction();
            db.close();
        }
    }
}

