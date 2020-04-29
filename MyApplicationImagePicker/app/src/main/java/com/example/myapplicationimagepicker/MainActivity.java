package com.example.myapplicationimagepicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplicationimagepicker.adapter.ResultImageAdapter;
import com.example.myapplicationimagepicker.domain.ImageItem;
import com.example.myapplicationimagepicker.utils.PickerConfig;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)


public class MainActivity extends AppCompatActivity implements PickerConfig.OnImageSelectedFinishedListener {


    private static final int PERSSION_REQUEST_CODE = 1;

    public static final int MAX_SELECTED_COUNT = 9;
    private RecyclerView mResultList;
    private ResultImageAdapter mResultImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initView();

        checkPermission();

        initPickerConfig();

    }

    private void initView() {
        mResultList = findViewById( R.id.result_list );
        mResultImageAdapter = new ResultImageAdapter();
        mResultList.setAdapter( mResultImageAdapter );

    }

    private void initPickerConfig() {
        PickerConfig pickerconfig = PickerConfig.getInstance();
        pickerconfig.setMaxSelectedCount( MAX_SELECTED_COUNT );
        pickerconfig.setOnImageSelectedFinishedListener( this );

    }

    private void checkPermission() {

        int readExternalPermission = checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE );
        if (readExternalPermission != PackageManager.PERMISSION_GRANTED) {
            //沒有權限
            requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERSSION_REQUEST_CODE );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );

        if (requestCode == PERSSION_REQUEST_CODE) {
            if (grantResults.length==0&&grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                //有權限

            }else{
                //沒有權限
                //交互處裡

            }
        }


    }

    public void pickImages(View view) {
        //打開另外一個介面
        startActivity( new Intent( this,PickerActivity.class ));

    }

    @Override
    public void onSelectedFinished(List<ImageItem> result) {

        Log.v( "jeff","onSelectedFinished --> " + result );

        //數據回來,所選擇的圖片列表回來了
        for (ImageItem imageItem : result) {

            Log.v("jeff", "Item ---> " + imageItem);
        }

        int     horizontalCount;
        if (result.size() < 3) {
            horizontalCount = result.size();
        }else {

            horizontalCount = 3;
        }
            mResultList.setLayoutManager( new GridLayoutManager( this,horizontalCount ) );
            mResultImageAdapter.setData(result,horizontalCount);

    }
}
