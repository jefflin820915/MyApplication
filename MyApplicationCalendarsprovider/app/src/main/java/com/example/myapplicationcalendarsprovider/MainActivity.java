package com.example.myapplicationcalendarsprovider;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            checkCalendar();
        }

        //queryCalendars();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkCalendar() {

        int writeCalendarPermission = ContextCompat.checkSelfPermission( MainActivity.this, Manifest.permission.WRITE_CALENDAR );
        int readCalendarPermission = ContextCompat.checkSelfPermission( MainActivity.this, Manifest.permission.READ_CALENDAR );
        if (writeCalendarPermission != PackageManager.PERMISSION_GRANTED && readCalendarPermission != PackageManager.PERMISSION_GRANTED) {
            //去獲取權限
            //做個提示,用戶點擊確定以後再去調用請求權限
            //如果點擊不再提示,就不去獲取,如果不能使用,根據PM的交互去寫
            requestPermissions( new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, PERMISSION_REQUEST_CODE );

        } else {
            //有權限
            Log.v( "brad", "get permission" );

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );

        if (requestCode == PERMISSION_REQUEST_CODE) {

            //判斷結果
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.v( "brad", "permissions....." );
                //有權限

            } else {

                Log.v( "brad", "no permissoions...." );
                //沒權限
                finish();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void queryCalendars() {
        ContentResolver contentResolver = getContentResolver();

        //Uri uri = Uri.parse( "content://" + "com.android.calendar/" + "calendars");

        Uri uri = CalendarContract.Calendars.CONTENT_URI;


        Cursor query = contentResolver.query( uri, null, null, null, null );

        String[] columnNames = query.getColumnNames();

        while (query.moveToNext()) {

            Log.v( "brad", "======================" );

            for (String columnName : columnNames) {

                Log.v( "brad", columnName + "=====" + query.getString( query.getColumnIndex( columnName ) ) );
            }

            Log.v( "brad", "======================" );
        }
    }

    //向日曆裡添加提醒事件
    @SuppressLint("MissingPermission")
    public void addAlertEvent(View view) {

        //_id=====1
        long calID = 1;
        Calendar beginTime = Calendar.getInstance();
        //年,月(從0開始),日,時,分
        beginTime.set( 2019, 10, 11, 0, 0 );
        long beginTimeMills = beginTime.getTimeInMillis();

        //結束時間
        Calendar endTime = Calendar.getInstance();

        //結束的時間
        endTime.set( 2019, 10, 11, 23, 59 );

        long endTimeInMillis = endTime.getTimeInMillis();

        //事件內容

        String timeZone = TimeZone.getDefault().getID();
        Log.v( "brad", "timeZone" + timeZone );
        ContentValues eventValues = new ContentValues(  );
        eventValues.put( CalendarContract.Events.DTSTART,beginTimeMills );
        eventValues.put( CalendarContract.Events.DTEND,endTimeInMillis );
        eventValues.put( CalendarContract.Events.CALENDAR_ID,calID );
        eventValues.put( CalendarContract.Events.EVENT_TIMEZONE,timeZone );
        eventValues.put( CalendarContract.Events.TITLE,"雙11搶購" );
        eventValues.put( CalendarContract.Events.DESCRIPTION,"買爆啦" );
        eventValues.put( CalendarContract.Events.EVENT_LOCATION,"taiwan" );

        //插入數據
        Uri eventUri = CalendarContract.Events.CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();
        Uri resultUri = contentResolver.insert( eventUri, eventValues );
        Log.v( "brad","result ---> " + resultUri );
        String eventId = resultUri.getLastPathSegment();
        Log.v( "brad","eventId ---->" + eventId );

        Uri remindersUri = CalendarContract.Reminders.CONTENT_URI;
        ContentValues reminderValues = new ContentValues(  );
        reminderValues.put( CalendarContract.Reminders.EVENT_ID, eventId);
        reminderValues.put( CalendarContract.Reminders.MINUTES, 15 );
        reminderValues.put( CalendarContract.Reminders.METHOD,CalendarContract.Reminders.METHOD_ALERT);

        contentResolver.insert( remindersUri, reminderValues);



    }
}
