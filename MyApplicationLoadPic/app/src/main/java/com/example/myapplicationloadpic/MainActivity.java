package com.example.myapplicationloadpic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void loadPic(View view) {

        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL( "https://cdn2.ettoday.net/images/3793/3793735.jpg" );
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout( 10000 );
                    connection.setRequestMethod( "GET" );
                    connection.setRequestProperty( "Accept-Language","zh-TW,zh;q=0.9" );
                    connection.setRequestProperty( "Accept","*/*" );
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        //轉成Bitmap
                        final Bitmap bitmap = BitmapFactory.decodeStream( inputStream );
                        //更新UI
                        runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                ImageView imageView = findViewById(R.id.load_img );
                                imageView.setImageBitmap( bitmap );
                            }
                        } );
                    }

                }catch (Exception e){

                }
            }
        } ).start();

    }





}
