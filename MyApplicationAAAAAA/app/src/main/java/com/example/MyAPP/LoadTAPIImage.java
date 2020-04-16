package com.example.MyAPP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javax.net.ssl.HttpsURLConnection;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoadTAPIImage extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;

    //構造方法傳回要設置的imageView
    public LoadTAPIImage(ImageView imageView) {

        imageViewReference = new WeakReference<>( imageView );
    }

    //執行前先執行的方法
    //讀取圖片
    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            return downloadBitmap( params[0] );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //執行完會執行的方法
    //顯示
    @Override
    protected void onPostExecute(Bitmap bitmap) {

        //拿到第二頁的Adapter傳過來要設置的imageView,設變數給imageView
        ImageView imageView = imageViewReference.get();

        //判斷如果讀取到bitmap的資料就把資料設給imageView
        if (bitmap != null) {

            imageView.setImageBitmap( bitmap );

            //如果沒有讀到就顯示自己設置的圖片給imageView
        } else {
            Drawable holder = imageView.getContext().getResources().getDrawable( R.drawable.ic_launcher_background );
            imageView.setImageDrawable( holder );
        }
    }

    private Bitmap downloadBitmap(String url) {

        //圖片網址使用原生HttpURLConnection來讀取圖片網址responseCode會返回 http 410 error
        //所以使用第三方套件OKHttp來讀取圖片網址
//        try {
//            URL uri = new URL( url );
//            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
//            connection.setRequestMethod( "GET" );
//            connection.setConnectTimeout( 8000 );
//            connection.connect();
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                mIs = connection.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream( mIs );
//
//                return bitmap;
//            }else{
//                Log.v("jeff","responseCode---> " +responseCode );
//            }
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//            if (mIs != null) {
//                try {
//                    mIs.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;

        //使用OkHttp來讀取圖片網址
        //responseCode返回200,讀取正常.
        //轉成bitmap後傳給onPostExecute設置給imageView
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url( url )
                .method( "GET", null )
                .build();

        byte[] bytes = new byte[0];

        try {
            Response response = okHttpClient.newCall( request ).execute();
            int code = response.code();
            if (code == HttpsURLConnection.HTTP_OK) {

                bytes = response.body().bytes();

                Log.v( "jeff", "responseCode---> " + code );

            } else {
                Log.v( "jeff", "responseCode---> " + code );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray( bytes, 0, bytes.length );

        return bitmap;
    }
}