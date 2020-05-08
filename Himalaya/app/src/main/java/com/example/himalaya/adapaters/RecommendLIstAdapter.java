package com.example.himalaya.adapaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himalaya.R;
import com.example.himalaya.utils.LogUtil;
import com.squareup.picasso.Picasso;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendLIstAdapter extends RecyclerView.Adapter<RecommendLIstAdapter.InnerHolder> {

    private List<Album> mData = new ArrayList<>();
    private OnRecommendItemClickListner mItemClickListner = null;

    @NonNull
    @Override
    public RecommendLIstAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //找到view,載view
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_recommend, parent, false );

        return new InnerHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendLIstAdapter.InnerHolder holder, final int position) {

        //這裡是設置數據
        holder.itemView.setTag( position );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mItemClickListner!=null) {
                    mItemClickListner.onItemClick( (Integer) v.getTag() );
                }
                LogUtil.v( "jeff","itemView Click ---> " + v.getTag() );
            }
        } );
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
            //title
            TextView albumTitleMainTv = itemView.findViewById( R.id.album_title_main_tv );
            //描述
            TextView albumTitleSubTv = itemView.findViewById( R.id.album_title_sub_tv);
            //播放數量
            TextView albumPlayCount = itemView.findViewById( R.id.album_play_count );
            //內容數量
            TextView albumContentCount = itemView.findViewById( R.id.album_content_size );

            albumTitleMainTv.setText( album.getAlbumTitle() );
            albumTitleSubTv.setText( album.getAlbumIntro() );
            albumPlayCount.setText( album.getPlayCount()+"");
            albumContentCount.setText( album.getIncludeTrackCount()+"");

            Picasso.with( itemView.getContext()).load( album.getCoverUrlLarge()).into( albumCover);

        }
    }

    public void setOnRecommendItemClickListner(OnRecommendItemClickListner listner){
        this.mItemClickListner = listner;
    }


    public interface OnRecommendItemClickListner{
        void onItemClick(int position);
    }



}
