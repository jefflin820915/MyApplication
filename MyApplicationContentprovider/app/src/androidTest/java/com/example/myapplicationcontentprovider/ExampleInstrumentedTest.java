package com.example.myapplicationcontentprovider;

import android.content.Context;
import android.util.Log;

import com.example.myapplicationcontentprovider.dao.IUserDao;
import com.example.myapplicationcontentprovider.dao.UserDaoImpl;
import com.example.myapplicationcontentprovider.pojo.User;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private IUserDao mDao;

    @Before
    public void testPrepare(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDao = new UserDaoImpl( appContext );

    }



    @Test
    public void testAddUser(){

        User user = new User();
        user.setSex( "female" );
        user.setAge( 16 );
        user.setPassWord( "1234578" );
        user.setUserName( "ceddy" );

        long result = mDao.addUser( user );
        Log.v("brad","add user result --> " + result);

        assertNotEquals( -1,result );

    }

    @Test
    public void testListALLUser(){

        List<User> users = mDao.listAllUser();
        Log.v( "brad","user.size ---> " + users.size() );

        for (User user : users) {
            Log.v( "brad","user --> " + user );
        }

    }

    @Test
    public void getUserId(){

        User getUserById = mDao.getUserById( 1 );
        Log.v( "brad","User --> " + getUserById );

    }

    @Test
    public void delUserById(){

        mDao.delUserById( 2);
        Log.v( "brad", "del user finsh " );

    }



}
