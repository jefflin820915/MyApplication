package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
   // private TextView num0, num1, num2, num3, num4, num5;
    private ListView list;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"title","num0","num1","num2","num3","num4","num5"};
    private int[] to ={R.id.item_title,R.id.num0,R.id.num1,R.id.num2,R.id.num3,R.id.num4,R.id.num5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn1 = findViewById( R.id.btn1 );
        list = findViewById( R.id.list );
        initView();

        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] lottery = cL();
                HashMap<String,String> row = new HashMap<>(  );
                row.put( from[0],"第" + (data.size() +1) + "組");
                row.put( from[1], "" + lottery[0] );
                row.put( from[2], "" + lottery[1] );
                row.put( from[3], "" + lottery[2] );
                row.put( from[4], "" + lottery[3] );
                row.put( from[5], "" + lottery[4] );
                row.put( from[6], "" + lottery[5] );
                data.add( row );
                adapter.notifyDataSetChanged();
                list.smoothScrollToPosition( row.size()-1);
            }
        } );
    }
    private void initView () {
        data = new LinkedList<>();
        adapter = new SimpleAdapter( this, data, R.layout.item, from, to );
        list.setAdapter( adapter );
    }

    private int[] cL(){
        HashSet<Integer> nums = new HashSet<>( );
        while(nums.size() < 6 ){
            nums.add((int)(Math.random() *49 + 1));
        }
        int i = 0; int[] ret = new int[6];
        for(Integer num : nums){
            ret[i] = num;
            i++;
        }
        return ret;

    }
}