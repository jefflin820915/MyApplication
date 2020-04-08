package com.example.myapplicationapppppppp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

class ImgButton1PageAdapter extends RecyclerView.Adapter<ImgButton1PageAdapter.InnerHolder> {
    private List<ItemBean> mData;
    View view;

    public ImgButton1PageAdapter(List<ItemBean> data) {

        this.mData = data;

    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.imgbutton1pageapdaper, parent, false );
        return new InnerHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.setData( mData.get( position ), position );

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        int mposition;
        TextView textView;
        ImageView imageView;

        public InnerHolder(@NonNull View itemView) {
            super( itemView );


            textView = itemView.findViewById( R.id.bnt1page_text_view );

            imageView = itemView.findViewById( R.id.bnt1page_img_view );

        }


        public void setData(ItemBean itemBean, int position) {
            this.mposition = position;
            textView.setText( itemBean.title );
            imageView.setImageResource( itemBean.icon );

        }
    }
}
