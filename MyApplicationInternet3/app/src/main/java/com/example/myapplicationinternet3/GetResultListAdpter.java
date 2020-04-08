package com.example.myapplicationinternet3;

import android.app.admin.DeviceAdminInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GetResultListAdpter extends RecyclerView.Adapter<GetResultListAdpter.InnerHolder> {

    private List<getTestItem.DataBean> data = new ArrayList<>(  );


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_get_test, parent, false );

        return new InnerHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        View view = holder.itemView;
        TextView Title = view.findViewById( R.id.item_Title );
        getTestItem.DataBean dataBean = data.get( position );
        Title.setText( dataBean.getTitle());

        ImageView cover = view.findViewById( R.id.item_img );
        Glide.with( view.getContext() ).load( "http://192.168.9.79:9102"+ data.get( position ).getCover()).into(cover);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(getTestItem getTestItem) {
        data.clear();
        data.addAll(getTestItem.getData());
        notifyDataSetChanged();

    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super( itemView );
        }
    }
}
