package com.example.myapplicationaaaaaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivityAdapter extends RecyclerView.Adapter<ThirdActivityAdapter.InnerHolder> {

    private Context mContext;
    private List<HashMap<String, String>> mList;

    public ThirdActivityAdapter(Context mContext) {
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

        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.activity_third_adapter,parent,false );

        return new InnerHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.mThirdIdTextView.setText( mList.get( position ).get( "id" ) );
        holder.mThirdTitleTextView.setText( mList.get( position ).get( "title" ) );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final ImageView mThirdImgView;
        private final TextView mThirdIdTextView;
        private final TextView mThirdTitleTextView;

        public InnerHolder(@NonNull View itemView) {
            super( itemView );

            mThirdImgView = itemView.findViewById( R.id.third_img_view );
            mThirdIdTextView = itemView.findViewById( R.id.third_id_text_view );
            mThirdTitleTextView = itemView.findViewById( R.id.third_title_text_view );

        }
    }
}
