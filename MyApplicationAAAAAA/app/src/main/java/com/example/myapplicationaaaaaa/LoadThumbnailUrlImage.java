package com.example.myapplicationaaaaaa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadThumbnailUrlImage extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;

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
            HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
            InputStream is = urlConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream( is );

            return bitmap;

        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }
}