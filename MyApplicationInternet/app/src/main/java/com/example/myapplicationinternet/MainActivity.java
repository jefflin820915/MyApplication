package com.example.myapplicationinternet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView data;
    private StringBuffer dataString;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById( R.id.data );
        uiHandler = new UIHandler();
    }


    public void test1(View view) {
        dataString = new StringBuffer(  );


        //處理異常: 開一個線程 new Thread(){}.start();
        //因Thread類實現Runnable接口,必須重寫run方法
        new Thread(  ){
            @Override
            public void run() {
                super.run();

                try {
                    //創建URL實例對象賦予給URL類型變數url,丟網址進去
                    //URL需處理異常
                    URL url = new URL( "https://www.gamer.com.tw/" );

                    //openConnection(); 返回一個URLConnection實例表示由引用的遠端對象的連接URL
                    //此處通訊協定為http,所以需強轉為http,賦予給HttpURLConnection類型的變數hc
                    HttpURLConnection hc = (HttpURLConnection) url.openConnection();

                    //執行連接
                    hc.connect();


                    //getInputStream() 返回從hc打開的連接讀取的輸入流
                    BufferedReader br = new BufferedReader(new InputStreamReader( hc.getInputStream()));

                    //創建String類型變數line
                    String line;

                    //當讀取一行文字不等於null,就往下執行,讀到null就關閉流
                    while ((line = br.readLine()) != null){

                        //追加每讀取的一行文字+換行至序列裡
                        dataString.append( line +"\n");
                    }

                    //關閉流
                    br.close();

                    //sendEmptyMessage( 0 ); 發送僅包含甚麼值的消息
                    uiHandler.sendEmptyMessage( 0 );

                } catch (Exception e) {

                    //異常: NetworkOnMainThreadException,不應該在主線程處理,必須擺在背景中
                    //處理異常: 開一個線程 new Thread(){}.start();
                    Log.v("brad",e.toString());
                }
            }
        }.start(); //執行線程
    }

    public void test2(View view){

        dataString = new StringBuffer(  );


        //處理異常: 開一個線程 new Thread(){}.start();
        //因Thread類實現Runnable接口,必須重寫run方法
        new Thread(  ){
            @Override
            public void run() {
                super.run();

                try {
                    //創建URL實例對象賦予給URL類型變數url,丟網址進去
                    //URL需處理異常
                    URL url = new URL( "https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx" );

                    //openConnection(); 返回一個URLConnection實例表示由引用的遠端對象的連接URL
                    //此處通訊協定為http,所以需強轉為http,賦予給HttpURLConnection類型的變數hc
                    HttpURLConnection hc = (HttpURLConnection) url.openConnection();

                    //執行連接
                    hc.connect();

                    //getInputStream() 返回從hc打開的連接讀取的輸入流
                    BufferedReader br = new BufferedReader(new InputStreamReader( hc.getInputStream()));

                    //創建String類型變數line
                    String line;

                    StringBuffer tempJSON = new StringBuffer(  );

                    //當讀取一行文字不等於null,就往下執行,讀到null就關閉流
                    while ((line = br.readLine()) != null){

                        //追加每讀取的一行文字+換行至序列裡
                        tempJSON.append( line);
                    }

                    //關閉流
                    br.close();

                    parseJSON( tempJSON.toString());

                } catch (Exception e) {

                    //異常: NetworkOnMainThreadException,不應該在主線程處理,必須擺在背景中
                    //處理異常: 開一個線程 new Thread(){}.start();
                    Log.v("brad",e.toString());
                }
            }
        }.start(); //執行線程
    }

    private void parseJSON(String json){

        try {
            JSONArray root = new JSONArray( json );

            for (int i = 0; i <root.length() ; i++) {
                JSONObject row = root.getJSONObject( i );
                String name = row.getString( "Name" );
                String tel = row.getString( "Tel" );
                dataString.append( name + " : " + tel + "\n");
            }

                //sendEmptyMessage( 0 ); 發送僅包含甚麼值的消息
                uiHandler.sendEmptyMessage( 0 );

        } catch (Exception e) {
            Log.v("brad",e.toString());
        }
    }

//Handler 處理程序允許發送和處理與線程MessageQueue相關聯的Message和Runnable對象
    private class UIHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage( msg );

            //分支語句,以msg.what來進行後面數據匹配
            //這裡what傳了0
            switch (msg.what){
                //在此數據匹配到,執行下面程序
                case 0:
                    //顯示字串到ID為data的TextView裡
                    data.setText( dataString );
                    break;
            }
        }
    }
}