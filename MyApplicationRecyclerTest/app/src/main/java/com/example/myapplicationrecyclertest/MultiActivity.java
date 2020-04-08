package com.example.myapplicationrecyclertest;

import android.os.Bundle;
import android.os.PersistableBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MultiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MultiTypeBean> data;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.activity_multi_type_activity );

        //find the view

        recyclerView = this.findViewById( R.id.multi_type_list );

        //準備數據
        data = new ArrayList<>(  );
        initDatas();

        //設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( linearLayoutManager );

        //創建Adapter
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(data);

        //設置Adapter
        recyclerView.setAdapter( multiTypeAdapter );
    }

    private void initDatas() {

        data = new ArrayList<>(  );
        Random random = new Random(  );

        for (int i = 0; i < Datas.icons.length; i++) {
         //創建數據對象
            MultiTypeBean multiTypeBean = new MultiTypeBean();

            multiTypeBean.pic = Datas.icons[i];
            multiTypeBean.type = random.nextInt(3);

            data.add( multiTypeBean );
        }
    }
}
