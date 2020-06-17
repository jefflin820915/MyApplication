package com.example.himalaya.adapaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.himalaya.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.InnerHolder> {

    private List<Track> mDetailData = new ArrayList<>(  );

    //格式化時間
    private SimpleDateFormat mUpdateDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
    private SimpleDateFormat mDurationFormat = new SimpleDateFormat( "mm:ss" );
    private ItemClickListener mItemClickListener = null;

    @NonNull
    @Override
    public DetailAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_album_detail, parent, false );
        return new InnerHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.InnerHolder holder, final int position) {

        //找到控件,設置數據
        final View itemView = holder.itemView;

        //順序ID
        TextView itemIdTv = itemView.findViewById( R.id.item_id_text );
        //標題
        TextView detailTitleTv = itemView.findViewById( R.id.detail_item_title );
        //撥放次數
        TextView detailPlayCountTv = itemView.findViewById( R.id.detail_item_play_count );
        //曲長
        TextView detailDurationTv = itemView.findViewById( R.id.detail_item_duration );
        //更新日
        TextView updateTimeDateTv = itemView.findViewById( R.id.detail_item_update_time );

        //設置數據
        Track track = mDetailData.get( position );
        itemIdTv.setText( position + ""  );
        detailTitleTv.setText( track.getTrackTitle());
        detailPlayCountTv.setText( track.getPlayCount() + "");

        int durationMil = track.getDuration() * 1000;
        String duration = mDurationFormat.format( durationMil );
        detailDurationTv.setText( duration);

        String updateTimeDateText = mUpdateDateFormat.format( track.getUpdatedAt() );
        updateTimeDateTv.setText(updateTimeDateText);

        //設置點擊事件
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:
                //Toast.makeText( v.getContext(),"you click " + position + " item" ,Toast.LENGTH_SHORT ).show();

                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick();
                }

            }
        } );
    }

    @Override
    public int getItemCount() {

        return mDetailData.size();
    }

    public void setData(List<Track> tracks) {
        //清除原來的數據
        mDetailData.clear();
        //添加新的數據
        mDetailData.addAll( tracks );
        //更新UI
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super( itemView );
        }
    }

    public void setItemClickListener(ItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface ItemClickListener{
        void onItemClick();
    }




}
