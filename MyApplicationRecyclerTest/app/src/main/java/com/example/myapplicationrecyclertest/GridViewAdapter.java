package com.example.myapplicationrecyclertest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridViewAdapter extends RecyclerViewBaseAdapter {


    public GridViewAdapter(List<ItemBean> data) {
        super( data );
    }

    @Override
    protected View getView(ViewGroup parent, int viewType) {
        //在這裡面創建條目
        View view = View.inflate( parent.getContext(), R.layout.item_grid_view, null );
        return view;
    }

}
