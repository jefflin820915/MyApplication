package com.example.myapplicationrecyclertest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewBaseAdapter(List<ItemBean> data) {

        this.mData = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = getView( parent, viewType );
        return new InnerHolder( view );
    }

    protected abstract View getView(ViewGroup parent, int viewType);


    //這個方法用於綁定Holder的,一般用來設置數據
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //設置數據
        ((InnerHolder) holder).setData( mData.get( position), position );

    }

    //返回條目個數
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size() +1 ;
        }

        return 0;
    }


    /*
        編寫回調的步驟
        1. 創建接口
        2. 定義接口內部的方法
        3. 提供設置接口(外部實現)
        4. 接口方法的調用
     */
    public interface OnItemClickListener {
        void onItemClick(int position);

    }


    public void setOnItemClickListener(OnItemClickListener Listener) {

        //設置一個監聽,就是要設置一個缺口,一個回調的接口
        this.mOnItemClickListener = Listener;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title;
        private int mPosition;

        public InnerHolder(@NonNull View itemView) {
            super( itemView );

            //找到條目控件
            icon = itemView.findViewById( R.id.icon );
            title = itemView.findViewById( R.id.title );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick( mPosition );
                    }
                }
            } );
        }


        public void setData(ItemBean itemBean, int position) {

            this.mPosition = position;

            //開始設置數據
            icon.setImageResource( itemBean.icon );
            title.setText( itemBean.title );
        }
    }
}