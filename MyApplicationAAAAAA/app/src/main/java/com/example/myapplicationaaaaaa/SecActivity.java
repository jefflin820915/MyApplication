package com.example.myapplicationaaaaaa;

import android.os.Bundle;
import android.util.Log;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecActivityAdapter mSecAdapter;
    ArrayList<HashMap<String,String>> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sec );

        initView();

        loadJson();

        showList();

    }


    private void showList() {


                GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 4 );

                gridLayoutManager.setOrientation( GridLayoutManager.HORIZONTAL );

                mRecyclerView.setLayoutManager( gridLayoutManager );

                mSecAdapter = new SecActivityAdapter();

                mRecyclerView.setAdapter( mSecAdapter );

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
                            String albumId = jsonObject.getString( "albumId" );
                            String id = jsonObject.getString( "id" );
                            String title = jsonObject.getString( "title" );
                            String url = jsonObject.getString( "url" );
                            String thumbnailUrl = jsonObject.getString( "thumbnailUrl" );


                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put( "id", id );
                            hashMap.put( "title", title );
                            hashMap.put( "url", url );
                            hashMap.put( "thumbnailUrl", thumbnailUrl );

                            data.add( hashMap );
                        }
                    Log.v( "brad", "loadData" + data );

                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                showList();
                                mSecAdapter.notifyDataSetChanged();

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


