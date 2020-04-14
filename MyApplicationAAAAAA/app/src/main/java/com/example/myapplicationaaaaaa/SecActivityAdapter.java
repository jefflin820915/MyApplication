package com.example.myapplicationaaaaaa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SecActivityAdapter extends RecyclerView.Adapter<SecActivityAdapter.InnerHolder> {

    private Context mContext;
    private List<HashMap<String, String>> mList;

    OnRecyclerViewItemClickListener listener;

    public interface OnRecyclerViewItemClickListener{

    void OnItemClick(View v,int position);

    }

    public void setOnItemClick(OnRecyclerViewItemClickListener listener){
        this.listener=listener;
    }


    public SecActivityAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void addList(List<HashMap<String, String>> list) {
        mList.addAll( list );
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.activity_sec_adapter, parent, false );

        return new InnerHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final InnerHolder holder, final int position) {

        holder.mIdView.setText( mList.get( position ).get( "id" ) );
        holder.mTitleView.setText( mList.get( position ).get( "title" ) );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener!=null) {
                    listener.OnItemClick( v,position );
                }
            }
        } );

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        ImageView mImgView;
        TextView mIdView;
        TextView mTitleView;


        public InnerHolder(@NonNull View itemView) {
            super( itemView );

            mImgView = itemView.findViewById( R.id.img_view );
            mIdView = itemView.findViewById( R.id.id_view );
            mTitleView = itemView.findViewById( R.id.title_view );

        }
    }
}
