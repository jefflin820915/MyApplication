package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private EditText editText4;
    private TextView textView11, textView12, textView13, textView14;
    private Button button23,button28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );

        button23 = findViewById(R.id.button23 );
        button28 = findViewById(R.id.button28);
        textView11 = findViewById(R.id.textView11 );
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13 );
        textView14 = findViewById( R.id.textView14 );
        editText4 = findViewById( R.id.editText4 );

        button23.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText4.getText().toString();
                textView11.setText(s);
                textView12.setText(s);
                textView13.setText(s);
                textView14.setText(s);
            }
        } );

        button28.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText4.setText("");
            }
        } );


    }
}
