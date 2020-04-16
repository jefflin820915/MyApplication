package com.example.myapplicationaaaaaa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoadThumbnailUrlImage extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private InputStream mIs;

    public LoadThumbnailUrlImage(ImageView imageView) {

        imageViewReference = new WeakReference<>( imageView );
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            return downloadBitmap( params[0] );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        ImageView imageView = imageViewReference.get();

        if (bitmap != null) {
            imageView.setImageBitmap( bitmap );

        } else {
            Drawable holder = imageView.getContext().getResources().getDrawable( R.drawable.ic_launcher_background );
            imageView.setImageDrawable( holder );
        }
    }

    private Bitmap downloadBitmap(String url) {

        try {
            URL uri = new URL( url );
            HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
            connection.setRequestMethod( "GET" );
            connection.setConnectTimeout( 8000 );
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                mIs = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream( mIs );

                return bitmap;
            }else{
                Log.v("jeff","responsecode---> " +responseCode );
            }


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (mIs != null) {
                try {
                    mIs.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}