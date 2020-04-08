package com.example.myapplicationcontentprovider.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplicationcontentprovider.db.UserDataBaseHelper;
import com.example.myapplicationcontentprovider.pojo.User;
import com.example.myapplicationcontentprovider.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    private final UserDataBaseHelper mUserDataBaseHelper;

    public UserDaoImpl(Context context) {

        mUserDataBaseHelper = new UserDataBaseHelper( context );

    }

    @Override
    public long addUser(User user) {

        SQLiteDatabase db = mUserDataBaseHelper.getWritableDatabase();
        ContentValues values = user2Values( user );


        long result = db.insert( Constant.TABLE_NAME, null, values );

        db.close();
        return result;
    }

    @Override
    public int delUserById(int id) {

        SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();
        int result = db.delete( Constant.TABLE_NAME, Constant.FIELD_ID, new String[]{id + ""} );
        db.close();
        return result;
    }

    @Override
    public int updateUser(User user) {

        SQLiteDatabase db = mUserDataBaseHelper.getWritableDatabase();

        //根據ID來更新內容
        ContentValues values = user2Values( user );

        int result = db.update( Constant.TABLE_NAME, values, Constant.FIELD_ID, new String[]{
                user.getId() + ""} );


        db.close();
        return result;
    }

    @Override
    public User getUserById(int id) {
        SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();

        //可以用SQL語句
        String sql = "select * from " + Constant.TABLE_NAME + " where " + Constant.FIELD_ID + " = ?";
        Cursor cursor = db.rawQuery( sql, new String[]{id + ""} );

        User user = null;
        if (cursor.moveToNext()) {

            user = cusor2User( cursor );
        }

        db.close();
        return user;
    }

    @Override
    public List<User> listAllUser() {

        SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();
        Cursor cusor = db.query( Constant.TABLE_NAME, null, null, null, null, null, null );

        List<User> users = new ArrayList<>();

        while (cusor.moveToNext()) {
            User user = cusor2User( cusor );
            users.add( user );
        }

        db.close();
        return users;
    }


    private User cusor2User(Cursor cursor) {
        User user = new User();
        //
        int userId = cursor.getInt( cursor.getColumnIndex( Constant.FIELD_ID ) );
        user.setId( userId );
        //
        String userName = cursor.getString( cursor.getColumnIndex( Constant.FIELD_USER_NAME ) );
        user.setUserName( userName );

        String userSex = cursor.getString( cursor.getColumnIndex( Constant.FIELD_SEX ) );
        user.setSex( userSex );

        String userPassword = cursor.getString( cursor.getColumnIndex( Constant.FIELD_PASSWORD ) );
        user.setPassWord( userPassword );

        int userAge = cursor.getInt( cursor.getColumnIndex( Constant.FIELD_AGE ) );
        user.setAge( userAge );

        return user;
    }



    private ContentValues user2Values(User user) {
        ContentValues values = new ContentValues();
        values.put( Constant.FIELD_USER_NAME, user.getUserName() );
        values.put( Constant.FIELD_SEX, user.getSex() );
        values.put( Constant.FIELD_PASSWORD, user.getPassWord() );
        values.put( Constant.FIELD_AGE, user.getAge() );
        return values;
    }


}
