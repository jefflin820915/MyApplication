package com.example.myapplicationaaaaaa;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ThirdActivity extends AppCompatActivity {

    private static final String ITEM_DATA = "ItemData";
    HashMap<String, Object> jsonArray = new HashMap<>();

    private TextView mThirdIdView;
    private TextView mThirdTitleView;
    private ImageView mThirdImgView;
    private Toolbar mThirdPageToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_third );

        initView();

        getItemData();

        ThirdPageToolBar();


    }

    private void ThirdPageToolBar() {

        setSupportActionBar( mThirdPageToolBar );
        mThirdPageToolBar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void initView() {

        mThirdIdView = findViewById( R.id.third_id_text_view );
        mThirdTitleView = findViewById( R.id.third_title_text_view );
        mThirdImgView = findViewById( R.id.third_img_view );
        mThirdPageToolBar = findViewById( R.id.third_page_tool_bar );
    }

    private void getItemData() {

        jsonArray = (HashMap<String, Object>) getIntent().getSerializableExtra( ITEM_DATA );
        mThirdIdView.setText( "id: " + jsonArray.get( "id" ).toString() );
        mThirdTitleView.setText( "title: " + jsonArray.get( "title" ).toString() );
        String url = jsonArray.get( "url" ).toString();

        new LoadTAPIImage( mThirdImgView ).execute( url );


        Log.v( "jeff", "url---> " + url );
        Log.v( "jeff", "jsonArray -->" + jsonArray );
    }


}