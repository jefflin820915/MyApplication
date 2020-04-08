package com.example.myapplicationapppppppp;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ImgButton1Page extends AppCompatActivity {

    List<ItemBean> data;
    ImgButton1PageAdapter adapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.imgbutton1page );

        recyclerView = findViewById( R.id.recyler_view_bnt1 );

        imgButton1Page();

        initData();

        showList();
    }

    private void showList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 2 );

        gridLayoutManager.setOrientation( GridLayoutManager.VERTICAL );

        recyclerView.setLayoutManager( gridLayoutManager );

        adapter = new ImgButton1PageAdapter( data );

        recyclerView.setAdapter( adapter );


    }

    private void initData() {


        data = new ArrayList<>();

        for (int i = 0; i < Dataes.bnt1Icons.length; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.icon = Dataes.bnt1Icons[i];
            itemBean.title = "第" + i + "條";

            data.add( itemBean );
        }


    }

    private void imgButton1Page() {

        Toolbar toolbar = findViewById( R.id.tool_bar1 );
        setSupportActionBar( toolbar );

        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled( true );
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
    }


}
