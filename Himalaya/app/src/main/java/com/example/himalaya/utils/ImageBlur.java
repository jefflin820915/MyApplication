package com.example.himalaya.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

public class ImageBlur {

    public static void makeBlur(ImageView imageView, Context context) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap blurred = blurRenderScript( bitmap, 10, context );
        imageView.setImageBitmap( blurred );
    }

    private static Bitmap blurRenderScript(Bitmap smallbitmap, int radius, Context context) {
        smallbitmap = RGB565toARGB888( smallbitmap );
        Bitmap bitmap = Bitmap.createBitmap( smallbitmap.getWidth(), smallbitmap.getHeight(), smallbitmap.getConfig().ARGB_8888 );
        RenderScript renderScript = RenderScript.create( context );
        Allocation blurInput = Allocation.createFromBitmap( renderScript, smallbitmap );
        Allocation blurOutput = Allocation.createFromBitmap( renderScript, bitmap );
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create( renderScript, Element.U8_4( renderScript ) );
        blur.setInput( blurInput );
        blur.setRadius( radius );
        blur.forEach( blurOutput );
        blurOutput.copyTo( bitmap );
        renderScript.destroy();

        return bitmap;
    }

    private static Bitmap RGB565toARGB888(Bitmap img) {
        int numPixels = img.getWidth() * img.getHeight();
        int[] pixels = new int[numPixels];

        img.getPixels( pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight() );

        Bitmap result = Bitmap.createBitmap( img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888 );

        result.setPixels( pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight() );

        return result;
    }
}