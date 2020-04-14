package com.example.myapplicationaaaaaa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecActivityAdapter mSecAdapter;
    List<JsonData> arrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> arrayListtwo = new ArrayList<>();

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

                            arrayListtwo.add( hashMap );
                        }
                    Log.v( "brad", "loadData" +arrayListtwo );

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

//class AsyncJson extends AsyncTask {
//
//    String json_url = "http://jsonplaceholder.typicode.com/photos";
//
//    List<JsonData> arrayList = new ArrayList<>();
//
//    private StringBuffer mJson;
//
//
//    @Override
//    protected Object doInBackground(Object[] objects) {
//
//        try {
//            URL apiUrl = new URL( json_url );
//            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
//            connection.setConnectTimeout( 100000 );
//            connection.setRequestMethod( "GET" );
//            connection.connect();
//            InputStream is = connection.getInputStream();
//            BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
//            String line = br.readLine();
//            mJson = new StringBuffer();
//            while (line != null) {
//                mJson.append( line );
//                line = br.readLine();
//            }
//            Log.v( "brad", "" + mJson );
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Object o) {
//        super.onPostExecute( o );
//
//        JSONArray jsonArray = null;
//        try {
//            jsonArray = new JSONArray( String.valueOf( mJson ) );
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                JSONObject jsonObject = jsonArray.getJSONObject( i );
//                JsonData jsonData = new JsonData();
//                jsonData.albumId = jsonObject.getString( "albumId" );
//                jsonData.id = jsonObject.getString( "id" );
//                jsonData.title = jsonObject.getString( "title" );
//                jsonData.url = jsonObject.getString( "url" );
//                jsonData.thumbnailUrl = jsonObject.getString( "thumbnailUrl" );
//                arrayList.add( jsonData );
//            }
//            Log.v( "brad", "loadData" + arrayList );
//
//            SecActivityAdapter secAdaper = new SecActivityAdapter(this,arrayList  );
//
//
//
//        } catch (JSONException ex) {
//            ex.printStackTrace();
//        }
//
// }
//
//}



