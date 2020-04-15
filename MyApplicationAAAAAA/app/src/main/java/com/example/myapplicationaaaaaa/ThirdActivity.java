package com.example.myapplicationaaaaaa;

import android.os.Bundle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private static final String ITEM_DATA = "ItemData";
    HashMap<String, Object> jsonArray = new HashMap<>();

    private TextView mThirdIdView;
    private TextView mThirdTitleView;
    private ImageView mThirdImgView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_third );

        initView();

        getItemData();

    }

    private void initView() {

        mThirdIdView = findViewById( R.id.third_id_text_view );
        mThirdTitleView = findViewById( R.id.third_title_text_view );
        mThirdImgView = findViewById( R.id.third_img_view );

    }

    private void getItemData() {

        jsonArray = (HashMap<String, Object>) getIntent().getSerializableExtra( ITEM_DATA );
        mThirdIdView.setText( "id: " + jsonArray.get( "id" ).toString() );
        mThirdTitleView.setText( "Title: " + jsonArray.get( "title" ).toString() );

        Log.v( "jeff", "jsonArray -->" + jsonArray );
    }



}