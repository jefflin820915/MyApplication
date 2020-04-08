package com.example.myapplicationrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinkedList<HashMap<String,String>> data;
    private  MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( mLayoutManager );

        doData();

        myAdapter = new MyAdapter();
        recyclerView.setAdapter( myAdapter );

    }
    private void doData(){
        data = new LinkedList<>(  );
        for (int i = 0; i <100 ; i++) {
            HashMap<String,String> row = new HashMap<>(  );
            int radom = (int)(Math.random()*100);
            row.put( "title","Title" + radom );
            row.put( "date","Date"+radom );
            data.add( row ) ;
        }
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

         class MyViewHolder extends RecyclerView.ViewHolder {

            // each data item is just a string in this case
            public View itemView;
            public TextView title, date;

            public MyViewHolder(View v) {
                super(v);
                itemView = v;

                title = itemView.findViewById( R.id.item_title );
                date = itemView.findViewById( R.id.item_date );

            }
        }

        @NonNull
        @Override //處理 產生介面
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override //處理資料細節上來的對應
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
            holder.title.setText( data.get( position ).get( "title" ) );
            holder.date.setText( data.get( position ).get( "date" ) );

            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.v("brad","Click :" + position);
                }
            } );


        }

        @Override //處理總共有幾筆資料
        public int getItemCount() {
             return data.size();
        }

    }

    public void test1(View view) {

        data.get(3).put( "title","brad" );
        data.get( 3 ).put( "date","20XX-XX-XX" );
        myAdapter.notifyDataSetChanged();
    }

    public void test2(View view){

        data.removeFirst();
        myAdapter.notifyDataSetChanged();

    }
}
