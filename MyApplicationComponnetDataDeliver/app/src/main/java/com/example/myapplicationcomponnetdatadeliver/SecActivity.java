package com.example.myapplicationcomponnetdatadeliver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );

        Intent intent = getIntent();
        if (intent != null) {
            int intvalue = intent.getIntExtra( "intKey", -1 );
            boolean booleanvalue = intent.getBooleanExtra( "booleanKey", false );
            //除了傳單個值以外,還可以傳同一類型的數組數據
            //intent.getbooleanArrayExtra();
            Log.v("brad","int value--> " + intvalue);
            Log.v("brad","booleanvalue---> " + booleanvalue);


            User userValue = intent.getParcelableExtra( "UserKey" );

            if (userValue != null) {
                Log.v( "brad","username--> "+userValue.getName());
                Log.v( "brad","userAge--> "+userValue.getAge());
                Log.v( "brad","userTall--> " +userValue.getTall());
            }
        }

    }
}
