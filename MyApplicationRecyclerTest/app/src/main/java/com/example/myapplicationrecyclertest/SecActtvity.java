package com.example.myapplicationrecyclertest;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SecActtvity  extends AppCompatActivity {

    List<ItemBean> data;
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        recyclerView = this.findViewById( R.id.recylcer_view );

        initDate();

        showList();
    }

    private void showList() {
        defultaAdapter defultaAdapter = new defultaAdapter( data );
        recyclerView.setAdapter( defultaAdapter );

    }

    private void initDate() {

        data = new ArrayList<>(  );

        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.icon = Datas.icons[i];
            itemBean.title = "第" + i + "條";

            data.add( itemBean );
        }

    }
}