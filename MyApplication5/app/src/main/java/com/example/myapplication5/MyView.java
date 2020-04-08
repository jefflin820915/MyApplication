package com.example.myapplication5;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;


import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

public class MyView extends View {

    private Bitmap ball;
    private Resources resources;
    private boolean isInit;
    private int viewW, viewH;
    private float ballX, ballY, dx, dy, ballW, ballH;
    private Timer timer;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );

        resources = context.getResources();
        ball = BitmapFactory.decodeResource( resources, R.drawable.ball );

        timer = new Timer();
    }

    private void init() {

        viewW = getWidth();
        viewH = getHeight();

        ballW = viewW / 6f;
        ballH = ballW;

        Matrix matrix = new Matrix();
        matrix.postScale( ballW / ball.getWidth(), ballH / ball.getHeight() );
        ball = Bitmap.createBitmap( ball, 0, 0, ball.getWidth(), ball.getHeight(), matrix, false );

        dx = 20;
        dy = 20;

        timer.schedule( new BallTask(), 1000, 30 );

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );

        if (!isInit) init();
        canvas.drawBitmap( ball, ballX, ballY, null );

    }

    private class BallTask extends TimerTask {

        @Override
        public void run() {

            if (ballX<0 || ballX+ballW>viewW){
                dx *= -1;
            }
            if (ballY<0 || ballY+ballH>viewH){
                dy *= -1;
            }
            ballX += dx;
            ballY += dy;

            postInvalidate();
        }
    }
}
