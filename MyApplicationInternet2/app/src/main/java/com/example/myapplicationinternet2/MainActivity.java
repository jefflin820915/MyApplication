package com.example.myapplicationinternet2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void test1(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                postConnect();
            }
        }.start();
    }

    private void postConnect() {
        try {
            URL url = new URL( "https://www.bradchao.com/v2/apptest/postTest1.php" );
            HttpURLConnection hc = (HttpURLConnection) url.openConnection();
            hc.setRequestMethod( "POST" );

            ContentValues params = new ContentValues();
            params.put( "account", "brad" );
            params.put( "passwd", "1234" );

            BufferedWriter writter = new BufferedWriter(new OutputStreamWriter( hc.getOutputStream()));

            writter.write( queryString( params ) );
            writter.flush();
            writter.close();

            hc.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
            String data = br.readLine();
            br.close();

            Log.v( "brad",data );

        } catch (Exception e) {
            Log.v( "brad", e.toString() );
        }
    }

    private String queryString(ContentValues params) {
        Set<String> keys = params.keySet();
        StringBuffer sb = new StringBuffer();

        try {
            for (String key : keys) {
                sb.append( URLEncoder.encode( key, "UTF-8" ) );
                sb.append( "=" );
                sb.append(URLEncoder.encode( params.getAsString( key ), "UTF-8" ) );
                sb.append( "&" );
            }
                sb.deleteCharAt( sb.length()-1 );
                return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
