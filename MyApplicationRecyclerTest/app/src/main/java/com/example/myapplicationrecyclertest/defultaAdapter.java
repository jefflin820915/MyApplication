package com.example.myapplicationrecyclertest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class defultaAdapter extends RecyclerView.Adapter<defultaAdapter.InnerHolder> {

    private final List<ItemBean> mData;

    public defultaAdapter(List<ItemBean> data){
        this.mData = data;
    }

    @NonNull
    @Override
    public defultaAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate( parent.getContext(),R.layout.item_list_view,null );
        return new InnerHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull defultaAdapter.InnerHolder holder, int position) {
        holder.setData(mData.get( position ));

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title;

        public InnerHolder(@NonNull View itemView) {
            super( itemView );
        icon = itemView.findViewById( R.id.icon );
        title = itemView.findViewById( R.id.title );
        }

        public void setData(ItemBean itemBean) {

            icon.setImageResource( itemBean.icon );
            title.setText( itemBean.title );

        }
    }
}
