package com.example.myapplicationapppppppp;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<ItemBean> data;
    SearchView searchView;
    ImageButton imageButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        recyclerView = findViewById( R.id.recyler_view );

        searchView = findViewById( R.id.search_view );

        searchfun();

        initData();

        showList();
    }

    private void searchfun() {
        searchView.setIconifiedByDefault( false );
        searchView.setImeOptions( 2 );
        searchView.setQueryHint( "Enter keyword" );


        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );

    }

    private void showList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );

        recyclerView.setLayoutManager( linearLayoutManager );


        adapter = new RecyclerAdapter( data );
        recyclerView.setAdapter( adapter );

        initClick();

    }

    private void initClick() {
        adapter.setOnItemClickListener( new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass( MainActivity.this, YoutubeActivity.class );
                startActivity( intent );
            }
        } );

    }


    private void initData() {

        data = new ArrayList<>();

        for (int i = 0; i < Dataes.icons.length; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.icon = Dataes.icons[i];
            itemBean.title = "第" + i + "條";
            itemBean.title2 = "第" + i + "個";

            data.add( itemBean );
        }


    }

    public void img_bnt1(View view) {

        imageButton = findViewById( R.id.img_bnt1 );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent();
                intent.setClass( MainActivity.this, ImgButton1Page.class );
                startActivity( intent );
            }
        } );

    }
}

