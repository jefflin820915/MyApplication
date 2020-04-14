package com.example.myapplicationaaaaaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class SecActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecActivityAdapter mSecAdapter;
    private List<HashMap<String, String>> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sec );

        initView();

        setAdapter();

        loadJson();

        listener();
    }

    private void listener() {
        mSecAdapter.setOnItemClick( new SecActivityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(  );
                intent.setClass( SecActivity.this,ThirdActivity.class );
                intent.putExtra( "id",mList.get( position ).toString() );
                //TODO:
                startActivity( intent );
            }
        } );

    }

    private void setAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 4 );
        gridLayoutManager.setOrientation( GridLayoutManager.VERTICAL );
        mRecyclerView.setLayoutManager( gridLayoutManager );
        mSecAdapter = new SecActivityAdapter( this );
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

                    Log.v( "brad", "" + json );

                    JSONArray jsonArray = new JSONArray( String.valueOf( json ) );

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject( i );
                        String id = jsonObject.getString( "id" );
                        String title = jsonObject.getString( "title" );
                        String thumbnailUrl = jsonObject.getString( "thumbnailUrl" );

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put( "id", id );
                        hashMap.put( "title", title );
                        hashMap.put( "thumbnailUrl", thumbnailUrl );


                        mList.add( hashMap );
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


