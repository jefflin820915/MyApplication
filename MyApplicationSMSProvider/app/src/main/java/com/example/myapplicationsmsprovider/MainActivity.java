package com.example.myapplicationsmsprovider;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

@RequiresApi(api = Build.VERSION_CODES.M)

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        checkSmsReadPermission();
    }

    private void checkSmsReadPermission() {

        int permissionResult = checkSelfPermission( Manifest.permission.READ_SMS );

        if (permissionResult!= PackageManager.PERMISSION_GRANTED) {
            //請求權限

            requestPermissions( new String[]{Manifest.permission.READ_SMS},PERMISSION_REQUEST_CODE );

        }else {


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );

        if (requestCode==PERMISSION_REQUEST_CODE) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Log.v( "brad","grantResults --> " + grantResults );
            }
        }else {
            finish();
        }

    }

    public void getSmsContent(View view) {

        ContentResolver cr = getContentResolver();
        Uri uri = Uri.parse("content://sms/");

        Cursor cursor = cr.query( uri, null, null, null, null );
        String[] columnNames = cursor.getColumnNames();

        while (cursor.moveToNext()) {

            for (String columnName : columnNames) {
                Log.v( "brad", columnName+ " ========> " + cursor.getString( cursor.getColumnIndex( columnName )));
            }
         }

    }

    public void toVerifyCodePage(View view) {

        Intent intent = new Intent(  );
        intent.setClass( this,VerifyCodeActivity.class );
        startActivity( intent );
    }
}
