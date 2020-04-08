package com.example.myapplicationposttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


    }

    public void postQuest(View view) {

        new Thread( new Runnable() {
            @Override
            public void run() {


                OutputStream outputStream = null;
                InputStream inputStream = null;

                try {

                    URL url = new URL( "http://192.168.9.79:9102/post/comment" );
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod( "POST" );
                    urlConnection.setConnectTimeout( 10000 );
                    urlConnection.setRequestProperty( "Content-Type", "application/json;charset-UTF-8" );
                    urlConnection.setRequestProperty( "Accept-Language", "zh-TW,zh;q=0.9" );
                    urlConnection.setRequestProperty( "Accept", "application/json, test/plain, */*" );

                    CommentItem commentItem = new CommentItem( "12321312", "mycomment" );

                    Gson gson = new Gson();
                    String jsonStr = gson.toJson( commentItem );
                    urlConnection.setRequestProperty( "Content-Length", String.valueOf( jsonStr.length() ) );

                    //連接
                    urlConnection.connect();

                    //數據給到服務器

                    outputStream = urlConnection.getOutputStream();
                    byte[] bytes = jsonStr.getBytes( "UTF-8" );

                    outputStream.write( bytes );

                    outputStream.flush();

                    //拿結果
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == urlConnection.HTTP_OK) {

                        inputStream = urlConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
                        String readLine = bufferedReader.readLine();
                        Log.v( "brad", "result--->" + readLine );

                    }

                } catch (Exception e) {

                    if(outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } ).start();


    }
}
