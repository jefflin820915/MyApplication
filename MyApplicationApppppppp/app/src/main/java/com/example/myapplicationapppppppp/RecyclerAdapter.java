package com.example.myapplicationapppppppp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.InnerHolder> {

    private List<ItemBean> mData;
    ItemBean itemBean = new ItemBean();
    private OnItemClickListener mOnItemClickListener;
    View view;


    public RecyclerAdapter(List<ItemBean> data) {
        this.mData = data;

    }


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_show_list, parent, false );

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

        TextView title;
        TextView title2;
        ImageView img_view;
        int mPosition;


        public InnerHolder(@NonNull View itemView) {
            super( itemView );


            img_view = itemView.findViewById( R.id.img_view );
            title = itemView.findViewById( R.id.text_view );
            title2 = itemView.findViewById( R.id.text_view2 );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnItemClick( mPosition );
                    }
                }
            } );
        }

        public void setData(ItemBean itemBean, int position) {

            this.mPosition = position;
            img_view.setImageResource( itemBean.icon );
            title.setText( itemBean.title );
            title2.setText( itemBean.title2 );

        }
    }


    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.mOnItemClickListener = onItemClickListener;


    }

}
