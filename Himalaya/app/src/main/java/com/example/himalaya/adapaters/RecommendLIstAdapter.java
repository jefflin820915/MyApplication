package com.example.himalaya.adapaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himalaya.R;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendLIstAdapter extends RecyclerView.Adapter<RecommendLIstAdapter.InnerHolder> {

    private List<Album> mData = new ArrayList<>();

    @NonNull
    @Override
    public RecommendLIstAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //找到view,載view
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_recommend, parent, false );

        return new InnerHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendLIstAdapter.InnerHolder holder, int position) {

        //這裡是設置數據
        holder.itemView.setTag( position );
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {

        //返回顯示的個數
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public void setData(List<Album> albumList) {

        if (mData != null) {
            mData.clear();
            mData.addAll( albumList );

        }
        //更新UI
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super( itemView );
        }

        public void setData(Album album) {
            //找到各個控件,設置數據
            //專輯的封面
            ImageView albumCover = itemView.findViewById( R.id.album_cover );
            //描述
            TextView titleMainTv = itemView.findViewById( R.id.album_title_main_tv );
            TextView titleSubTv = itemView.findViewById( R.id.album_title_sub_tv);


        }
    }
}
