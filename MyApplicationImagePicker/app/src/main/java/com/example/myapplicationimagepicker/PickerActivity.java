package com.example.myapplicationimagepicker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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


import com.example.myapplicationimagepicker.adapter.ImageListAdapter;
import com.example.myapplicationimagepicker.domain.ImageItem;
import com.example.myapplicationimagepicker.utils.PickerConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PickerActivity extends AppCompatActivity implements ImageListAdapter.OnItemSelectedChangeListener {

    private static final int LOADER_ID = 1;

    private List<ImageItem> mImageItems = new ArrayList<>();
    private ImageListAdapter mImageListAdapter;
    private TextView mFinishView;
    private PickerConfig mPickerConfig;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_picker );

        initLoaderManager();

        initView();

        initEvent();

        initConfig();

        
    }

    private void initConfig() {

        mPickerConfig = PickerConfig.getInstance();
        int maxSelectedCount = mPickerConfig.getMaxSelectedCount();
        mImageListAdapter.setMAXSelectedCount( maxSelectedCount );



    }

    private void initEvent() {
        mImageListAdapter.setOnItemSelectedChangeListener( this );
        mFinishView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //獲取到所選擇的數據
                List<ImageItem> result = mImageListAdapter.getmSelectedItems();

                //通知其他地方
                PickerConfig.OnImageSelectedFinishedListener imageSelectedFinishedListener = mPickerConfig.getmImageSelectedFinishedListener();
                if (imageSelectedFinishedListener!=null) {
                    imageSelectedFinishedListener.onSelectedFinished( result );
                }

                //結束介面
                finish();

            }
        } );
    }

    private void initView() {

        mFinishView = this.findViewById( R.id.finish_tv );
        RecyclerView listView = findViewById( R.id.image_list_view );

        listView.setLayoutManager( new GridLayoutManager( this,3 ) );

        //設置Adapter
        mImageListAdapter = new ImageListAdapter();
        listView.setAdapter(mImageListAdapter);


    }

    private void initLoaderManager() {

        mImageItems.clear();
        LoaderManager loaderManager = LoaderManager.getInstance( this );
        loaderManager.initLoader( LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
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

                            String path = cursor.getString( 0 );
                            String title = cursor.getString( 1 );
                            long data = cursor.getLong( 2 );
                            ImageItem imageItem = new ImageItem( path, title, data );
                            mImageItems.add( imageItem );

                        }

                    cursor.close();
                    for (ImageItem mImageItem : mImageItems) {
                        Log.v( "jeff","image --> " + mImageItem );
                    }

                    mImageListAdapter.setData(mImageItems);

                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        } );
    }

    @Override
    public void onItemSelectedChange(List<ImageItem> selectedItems) {

        //所選擇的數據發生變化
        mFinishView.setText( "("+selectedItems.size()+"/"+mImageListAdapter.getMAXSelectedCount()+")完成" );

    }
}
