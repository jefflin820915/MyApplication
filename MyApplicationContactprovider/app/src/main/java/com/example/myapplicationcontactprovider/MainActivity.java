package com.example.myapplicationcontactprovider;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );



        //getUserInfo( );
        checkContactPermission();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkContactPermission() {

        int readContactPermission = checkSelfPermission( Manifest.permission.READ_CONTACTS);

        if (readContactPermission!= PackageManager.PERMISSION_GRANTED) {

            //沒有權限,需要請求權限
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_CODE );

        }else{
            //有權限
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );

        if (requestCode==PERMISSION_CODE) {

            if (grantResults.length==1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }

        }else {
                finish();
        }

    }

    private void getUserInfo() {
        ContentResolver cr = getContentResolver();

        //我需要Uri
        Uri rawConstants = Uri.parse("content://" + ContactsContract.AUTHORITY + "/raw_contacts" );


        Cursor rawContactCursor = cr.query( rawConstants,new String[]{"_id","display_name"}, null, null, null );


        List<UserInfo> userInfos = new ArrayList<>(  );

        while (rawContactCursor.moveToNext()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId( rawContactCursor.getString( rawContactCursor.getColumnIndex( "_id" )));
            userInfo.setDisplayName(rawContactCursor.getString( rawContactCursor.getColumnIndex( "display_name" )));
            userInfos.add( userInfo );
        }

        rawContactCursor.close();

        Uri phoneUri = Uri.parse("content://" + ContactsContract.AUTHORITY + "/data/phones" );

        for (UserInfo userInfo : userInfos) {

            //查詢手機號碼
            Cursor phoneCursor = cr.query( phoneUri,new String[]{"data1"}, "raw_contact_id=?", new String[]{userInfo.getId()}, null );
            if (phoneCursor.moveToNext()) {
                userInfo.setPhoneNum( phoneCursor.getString( 0 ).replace( "-","" ) );
            }
            phoneCursor.close();
            Log.v( "brad","userInfo ---> "+ userInfo );
        }
    }

    public void getContactInfo(View view) {
        getUserInfo();


    }
}
