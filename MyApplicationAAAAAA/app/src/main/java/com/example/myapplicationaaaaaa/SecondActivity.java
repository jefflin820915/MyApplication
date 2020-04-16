package com.example.myapplicationaaaaaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecondActivityAdapter mSecAdapter;
    private List<HashMap<String, String>> mList;
    private HashMap<String, String> mHashMap;
    private JSONObject mJsonObject;
    private Toolbar mSecPageToolBar;
    private InputStream mIs;
    private BufferedReader mBr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sec );

        initView();

        setAdapter();

        loadJson();

        SecondPageToolBar();

    }

    private void SecondPageToolBar() {
        //ToolBar返回上一頁按鈕
        setSupportActionBar( mSecPageToolBar );
        //監聽點擊事件
        mSecPageToolBar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit( 0 );
            }
        } );
    }


    private void setAdapter() {
        //創建Adapter
        //設置Adapter
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 4 );
        gridLayoutManager.setOrientation( GridLayoutManager.VERTICAL );
        mRecyclerView.setLayoutManager( gridLayoutManager );
        mSecAdapter = new SecondActivityAdapter( this );
        mRecyclerView.setAdapter( mSecAdapter );
    }

    private void showList() {
        //更新UI
        mSecAdapter.notifyDataSetChanged();

    }

    private void initView() {
        //初始化元件
        mRecyclerView = findViewById( R.id.recycler_view );
        mSecPageToolBar = findViewById( R.id.sec_page_tool_bar );
    }

    private void loadJson() {

        //設置URL
        final String loadData = "http://jsonplaceholder.typicode.com/photos";

        //使用Thread + HttpURLConnection接API
        new Thread( new Runnable() {

            @Override
            public void run() {

                //創建ArrayList對象
                mList = new ArrayList<>();

                //連接API
                try {
                    URL apiUrl = new URL( loadData );
                    HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                    connection.setConnectTimeout( 100000 );
                    connection.setRequestMethod( "GET" );
                    connection.connect();
                    mIs = connection.getInputStream();
                    mBr = new BufferedReader( new InputStreamReader( mIs ) );
                    String line = mBr.readLine();
                    StringBuffer json = new StringBuffer();
                    while (line != null) {
                        json.append( line );
                        line = mBr.readLine();
                    }


                    Log.v( "jeff", "" + json );

                    JSONArray jsonArray = new JSONArray( String.valueOf( json ) );

                    for (int i = 0; i < jsonArray.length(); i++) {

                        mJsonObject = jsonArray.getJSONObject( i );
                        String id = mJsonObject.getString( "id" );
                        String title = mJsonObject.getString( "title" );
                        String url = mJsonObject.getString( "url" );
                        String thumbnailUrl = mJsonObject.getString( "thumbnailUrl" );

                        mHashMap = new HashMap<>();
                        mHashMap.put( "id", id );
                        mHashMap.put( "title", title );
                        mHashMap.put( "url", url );
                        mHashMap.put( "thumbnailUrl", thumbnailUrl );

                        mList.add( mHashMap );
                    }

                    //更新UI
                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            showList();
                            mSecAdapter.addList( mList );
                        }
                    } );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (mIs != null) {

                            mIs.close();
                        }
                        if (mBr != null) {

                            mBr.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } ).start();
    }
}


