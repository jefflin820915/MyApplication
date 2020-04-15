package com.example.myapplicationaaaaaa;

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

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecondActivityAdapter mSecAdapter;
    private List<HashMap<String, String>> mList;
    private HashMap<String, String> mHashMap;
    private JSONObject mJsonObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sec );

        initView();

        setAdapter();

        loadJson();

    }



    private void setAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 4 );
        gridLayoutManager.setOrientation( GridLayoutManager.VERTICAL );
        mRecyclerView.setLayoutManager( gridLayoutManager );
        mSecAdapter = new SecondActivityAdapter( this );
        mRecyclerView.setAdapter( mSecAdapter );
    }

    private void showList() {

        mSecAdapter.notifyDataSetChanged();

    }

    private void initView() {

        mRecyclerView = findViewById( R.id.recycler_view );
    }

    private void loadJson() {

        final String loadData = "http://jsonplaceholder.typicode.com/photos";

        new Thread( new Runnable() {

            @Override
            public void run() {

                mList = new ArrayList<>();

                try {
                    URL apiUrl = new URL( loadData );
                    HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                    connection.setConnectTimeout( 100000 );
                    connection.setRequestMethod( "GET" );
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
                    String line = br.readLine();
                    StringBuffer json = new StringBuffer();
                    while (line != null) {
                        json.append( line );
                        line = br.readLine();
                    }
                    is.close();
                    br.close();

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
                        mHashMap.put( "url",url );
                        mHashMap.put( "thumbnailUrl", thumbnailUrl );

                        mList.add( mHashMap );
                    }

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
                }
            }
        } ).start();
    }
}


