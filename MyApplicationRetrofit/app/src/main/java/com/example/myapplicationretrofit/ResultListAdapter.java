package com.example.myapplicationretrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.InnerHolder> {
    private List<JsonResult.DataBean> data = new ArrayList<>(  );

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_json_result, parent, false );
        return new InnerHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //綁定數據
        View view = holder.itemView;
        ImageView result_imgview = holder.itemView.findViewById( R.id.result_imgview );
        TextView title = holder.itemView.findViewById( R.id.result_title );
        TextView user = holder.itemView.findViewById( R.id.result_user );

        JsonResult.DataBean dataBean = data.get( position );

        title.setText( dataBean.getTitle() );
        user.setText( dataBean.getUserName());
        Log.v( "brad","cover url ---> " +dataBean.getCover());

        Glide.with(view.getContext()).load( "http://192.168.9.79:9102"+dataBean.getCover()).into(result_imgview );

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JsonResult jsonResult) {
        data.clear();
        data.addAll( jsonResult.getData() );
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super( itemView );
        }
    }
}
