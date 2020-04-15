package com.example.myapplicationaaaaaa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask {

    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;

    }

    @Override
    protected Bitmap doInBackground(Object[] objects) {
        String urlDisplay = (String) objects[0];
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }


}

