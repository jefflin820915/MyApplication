package com.example.myapplicationimagepicker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

//
// _id====1807
// _data====/storage/emulated/0/DCIM/Camera/VID_20200415_182216.mp4
// _size====55031517
// format====12299
// parent====36
// date_added====1586946161
// date_modified====1586946160
// mime_type====video/mp4
// title====VID_20200415_182216
// description====null
// _display_name====VID_20200415_182216.mp4
// picasa_id====null
// orientation====null
// latitude====25.0058
// longitude====121.484
// datetaken====1586946136942
// mini_thumb_magic====null
// bucket_id====-1739773001
// bucket_display_name====Camera
// isprivate====null
// title_key====null
// artist_id====null
// album_id====null
// composer====null
// track====null
// year====null
// is_ringtone====null
// is_music====null
// is_alarm====null
// is_notification====null
// is_podcast====null
// album_artist====null
// duration====21738
// bookmark====null
// artist====null
// album====null
// resolution====1920x1080
// tags====null
// category====null
// language====null
// mini_thumb_data====null
// name====null
// media_type====3
// old_id====null
// storage_id====null
// is_drm====null
// width====null
// height====null
// title_resource_uri====null
// ============


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;


public class PickerActivity extends AppCompatActivity {

    private static final int LOADER_ID = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_picker );


//        ContentResolver contentResolver = getContentResolver();
//        //Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        Uri imageUri = MediaStore.Files.getContentUri( "external" );
//        Cursor query = contentResolver.query( imageUri, null, null, null, null );
//        String[] columnNames = query.getColumnNames();new
//        while (query.moveToNext()) {
//            Log.v("jeff","=============");
//            for (String columnName : columnNames) {
//
//                Log.v( "jeff", columnName + "====" + query.getString( query.getColumnIndex( columnName ) ) );
//            }
//
//            Log.v( "jeff","============" );
//        }
//        query.close();


        initLoaderManager();
    }

    private void initLoaderManager() {

        LoaderManager loadManager = LoaderManager.getInstance( this );
        loadManager.initLoader( LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

                if (id == LOADER_ID) {

                    return new CursorLoader( PickerActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{"_data", "_display_name", "date_added"}, null, null, "date_added DESC" );
                }

                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String[] columnNames = cursor.getColumnNames();

                        while (cursor.moveToNext()) {
                            Log.v( "jeff", "=============" );

                            for (String columnName : columnNames) {

                                Log.v( "jeff", columnName + "====" + cursor.getString( cursor.getColumnIndex( columnName ) ) );
                            }

                            Log.v( "jeff", "============" );
                        }
                    }
                    cursor.close();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        } );
    }
}
