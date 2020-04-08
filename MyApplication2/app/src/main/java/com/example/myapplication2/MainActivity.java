package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText editText4;
    private Button button23, button28;
    private ListView listView_001;
    //private String[] fram = {"text"};
    //private int[] to = {R.id.item};
    //private LinkedList<HashMap<String, String>> data = new LinkedList<>();
    //private SimpleAdapter adapter;
    //private LinkedList<String> data = new LinkedList<>();

    private ArrayAdapter adapter;
    private String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        button23 = findViewById( R.id.button23 );
        button28 = findViewById( R.id.button28 );
        editText4 = findViewById( R.id.editText4 );
        listView_001 = findViewById( R.id.listView_001 );

        //adapter = new SimpleAdapter( this, data, R.layout.bean, fram, to );

        adapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1);

        listView_001.setAdapter( adapter );

        LoadData();

        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s = editText4.getText().toString();

                //HashMap<String, String> d0 = new HashMap<>();
                //d0.put(fram[0], s );
                //data.add( d0 );
                //for(String cs:d0){}

              adapter.add( s );
                SaveData( "LISTS",s  );

            }
        } );

        button28.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                editText4.setText( "" );
                adapter.clear();
                adapter.notifyDataSetChanged();
            }
        } );
    }
/*
    private void initListView() {
        HashMap<String, String> d0 = new HashMap<>();
        d0.put( fram[0], "Test1" );
        data.add( d0 );

        adapter = new SimpleAdapter( this, data, R.layout.bean, fram, to );
        listView_001.setAdapter( adapter );
    }
*/

    protected void SaveData(String key, String value) {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences( this );
        SharedPreferences.Editor editor = data.edit();
        editor.putString( key, value );
        editor.apply();
    }

    protected void LoadData() {

        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences( this );
        String dataSet = data.getString( "LISTS", "" );
        adapter.add( dataSet );
        adapter.notifyDataSetChanged();
    }



}

