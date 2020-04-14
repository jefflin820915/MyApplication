package com.example.myapplicationaaaaaa;

import android.app.job.JobInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class SecActivityAdapter extends RecyclerView.Adapter<SecActivityAdapter.InnerHolder> {

    SecActivity secActivity = new SecActivity();


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.activity_sec_adapter, parent, false );

        return new InnerHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {


        holder.mIdView.setText( secActivity.arrayListtwo.get( position ).get( "id" ) );
        holder.mTitleView.setText( secActivity.arrayListtwo.get( position ).get( "title" ) );

    }

    @Override
    public int getItemCount() {

        if (secActivity.arrayList != null) {
            return secActivity.arrayList.size();
        }

        return 0;
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
