package com.example.himalaya.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.himalaya.R;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class LoadingView extends ImageView {

    //旋轉的角度
    private int rotateDegree = 0;
    private boolean mNeedRotate = false;

    public LoadingView(Context context) {
        this( context,null );
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this( context, attrs,0 );
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        //設置圖標
        setImageResource( R.mipmap.loading );
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mNeedRotate = true;

        //綁定到window的時候
        post( new Runnable() {
            @Override
            public void run() {

                rotateDegree += 30;
                rotateDegree = rotateDegree <= 360 ? rotateDegree:0;
                invalidate();
                //是否繼續旋轉
                if (mNeedRotate) {
                    postDelayed( this,50 );
                }
            }
        } );
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //從window中解榜
        mNeedRotate = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 第一個參數是旋轉角度
         * 第二個參數是旋轉的x座標
         * 第三個參數是旋轉的y座標
         */
        canvas.rotate( rotateDegree,getWidth()/2,getHeight()/2 );
        super.onDraw( canvas );


    }
}
