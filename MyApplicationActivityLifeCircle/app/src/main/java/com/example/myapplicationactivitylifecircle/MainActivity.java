package com.example.myapplicationactivitylifecircle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_RECORD = "msg_record";
    private static final String RECORD_KEY = "msg";
    private EditText mcontentBox;
    private Button msendBtn;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initView();

        initLisener();

        //恢復數據
        mSharedPreferences = this.getSharedPreferences( MSG_RECORD, MODE_PRIVATE );
        String record = mSharedPreferences.getString( RECORD_KEY, null );
        if (!TextUtils.isEmpty( record )) {
            mcontentBox.setText( record );
        }
    }

    private void initLisener() {
        msendBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //獲取到簡訊內容
                String content = mcontentBox.getText().toString().trim();

                if (TextUtils.isEmpty( content )) {

                    Toast.makeText( MainActivity.this,"清輸入內容",Toast.LENGTH_SHORT).show();
                    return;
                }
                //
                Log.v("brad","發送簡訊....");
            }
        } );


    }

    private void initView() {

        mcontentBox = findViewById( R.id.content_box );
        msendBtn = findViewById( R.id.send );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //把數據存起來,保存到sharedPreferences裡.
        String content = mcontentBox.getText().toString().trim();
        if (!TextUtils.isEmpty( content )) {

            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString(RECORD_KEY, content);
            edit.commit();

        }
    }
}
