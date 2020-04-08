package com.example.myapplicationdatabase;

import junit.framework.TestCase;

import androidx.test.platform.app.InstrumentationRegistry;

public class TestDatabase extends TestCase {

    public void testCreate() {
        //這裡測試創建數據庫

    }

    public void testInsert() {
        //測試插入數據

        Dao dao = new Dao(InstrumentationRegistry.getInstrumentation().getTargetContext());
        dao.insert();

    }

    public void testDelete() {
        //測試刪除數據

        Dao dao = new Dao( InstrumentationRegistry.getInstrumentation().getTargetContext() );
        dao.delete();
    }

    public void testUpdate() {
        //測試修改數據

        Dao dao = new Dao( InstrumentationRegistry.getInstrumentation().getTargetContext() );
        dao.update();

    }

    public void testQuery() {
        //測試查詢數據

        Dao dao = new Dao( InstrumentationRegistry.getInstrumentation().getTargetContext() );
        dao.query();
    }

}
