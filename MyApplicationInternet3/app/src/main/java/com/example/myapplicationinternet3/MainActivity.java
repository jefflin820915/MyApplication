package com.example.myapplicationinternet3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private GetResultListAdpter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();

    }

    private void initView() {
    RecyclerView recyclerView = this. findViewById( R.id.result_list );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));
        adpter = new GetResultListAdpter();
        recyclerView.setAdapter( adpter );
    }


    public void loadjson(View view) {

        new Thread( new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL( "http://192.168.9.79:9102/get/text" );
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout( 10000 );
                    connection.setRequestMethod( "GET" );
                    connection.setRequestProperty( "Accept-Language","zh-TW,zh;q=0.9" );
                    connection.setRequestProperty( "Accept","*/*" );
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                        for (Map.Entry<String, List<String>> entry : entries) {
                            Log.v("brad",entry.getKey() + " == " + entry.getValue());
                        }

                        InputStream inputStream = connection.getInputStream();
                        BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ) );
                        String json = br.readLine();
                        //Log.v("brad","line -> " + line);
                        Gson gson = new Gson();
                        getTestItem getTestItem = gson.fromJson( json, getTestItem.class );
                        updateUI(getTestItem);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        } ).start();
   }

    private void updateUI(final getTestItem getTestItem) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                adpter.setData(getTestItem);
            }
        } );

    }
}
