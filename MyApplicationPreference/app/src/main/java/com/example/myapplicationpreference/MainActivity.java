package com.example.myapplicationpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch openClose;
    private SharedPreferences settings_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

         openClose = findViewById( R.id.open_close );
        settings_info = this.getSharedPreferences( "settings_info", MODE_PRIVATE );
        boolean state = settings_info.getBoolean( "state", false );
        openClose.setChecked( state );

        openClose.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = settings_info.edit();
                editor.putBoolean( "state",isChecked );
                editor.commit();
            }
        } );
    }
}
