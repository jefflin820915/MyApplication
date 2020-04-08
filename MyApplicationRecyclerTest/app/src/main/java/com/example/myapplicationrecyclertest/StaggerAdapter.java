package com.example.myapplicationrecyclertest;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StaggerAdapter extends RecyclerViewBaseAdapter {
    public StaggerAdapter(List<ItemBean> data) {
        super( data );
    }

    @Override
    protected View getView(ViewGroup parent, int viewType) {
        View view = View.inflate( parent.getContext(), R.layout.item_stagger_view, null );
        return view;
    }
}
