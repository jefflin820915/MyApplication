package com.example.myapplicationactivitylandscapelifecircle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Activity在橫縱屏切換的時候,生命週期的變化
 *
 * V/brad: onCreate...
 * V/brad: onStart...
 * V/brad: onResume...
 * V/brad: onStop...
 * (把螢幕切換成橫屏)
 * V/brad: onPause
 * V/brad: onStop
 * V/brad: onDestory
 * V/brad: onCreate...
 * V/brad: onStart...
 * V/brad: onResume...
 *
 * 從上面結果可以得出結論,當我們切換成橫屏的時候Activity會銷毀,並且重新創建
 *
 * (把螢幕從橫屏切換成縱屏)
 * V/brad: onPause
 * V/brad: onStop
 * V/brad: onDestory
 * V/brad: onCreate...
 * V/brad: onStart...
 * V/brad: onResume...
 *
 * Activity銷毀,且重新創建
 *
 * 結論: 當從縱屏切換橫屏,Activity會銷毀,並且重新創建
 *
 * 在實際開發中會遇到那些橫縱屏切換
 * 1. 遊戲開發,一般來說,遊戲開發會使用橫屏
 * 2. 影片撥放器,影片撥放器會使用橫屏縱屏,達到更好的撥放效果
 * 3. 其他使用場景
 *
 *  橫縱屏的切換,Activity生命週期的變化,會對我們開發帶來甚麼影響
 *
 *  橫縱屏解決:
 *  1.禁止旋轉,指定螢幕方向 (適合只有一種屏幕狀態的應用開發配置)
 *      android:screeOrientation="landscape/partrait";
 *
 *  2.對配置不敏感, (適合有兩種/多種屏幕狀態的應用開發
 *      android:configChanges="keyboradHidder,screenSize,orientation"
 *
 */

public class MainActivity extends AppCompatActivity {

    private SeekBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Log.v( "brad","onCreate..." );

        initView();
    }

    private void initView() {

        mProgress = findViewById( R.id.progress );

        Log.v( "brad","progess--> "  + mProgress.toString() );

        //設置初始化數據
        mProgress.setMax( 100 );
        mProgress.post( new Runnable() {
            @Override
            public void run() {
                mProgress.setProgress( 0 );

            }
        } );

    }

    @Override
    protected void onStart() {
        super.onStart();

    Log.v( "brad","onStart..." );

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v( "brad","onResume..." );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v( "brad","onPause" );
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.v( "brad","onStop" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v( "brad","onDestory" );
    }
}

