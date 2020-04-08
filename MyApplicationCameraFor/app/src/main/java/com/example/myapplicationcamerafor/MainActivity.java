package com.example.myapplicationcamerafor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CAMERA = 111;


    private ImageView mresultContainer;
    private ImageButton mtakePhto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        perMission();

        initView();

        initListener();
    }

    private void perMission() {
        if(ContextCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    private void initListener() {

        mtakePhto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在這邊拍照的按鈕被點擊以後跳轉到相機介面
                //startActivityForResult();

                Intent intent = new Intent(  );

                intent.setAction( "android.media.action.IMAGE_CAPTURE" );
                intent.addCategory( Intent.CATEGORY_DEFAULT );

                startActivityForResult( intent, REQUEST_CODE );
            }
        } );
    }

    private void initView() {

        mresultContainer = findViewById( R.id.phto_result );
        mtakePhto = findViewById( R.id.take_phto );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == REQUEST_CODE) {
            if (resultCode== Activity.RESULT_OK && data != null) {
                //說明是成功返回

                Bitmap result = data.getParcelableExtra( "data" );
                if (result != null) {
                    mresultContainer.setImageBitmap( result );
                }

            } else if (resultCode==Activity.RESULT_CANCELED){
                    //取消或失敗
                    Toast.makeText( this,"您取消了拍照",Toast.LENGTH_SHORT ).show();
                }
            }
        }
    }