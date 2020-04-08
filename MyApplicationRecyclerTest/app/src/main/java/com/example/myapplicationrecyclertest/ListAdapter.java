package com.example.myapplicationrecyclertest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerViewBaseAdapter {

    //普通的條目類型
    public static final int TYPE_NORMAL = 0;
    //加載更多
    public static final int TYPE_LOADER_MORE = 1;
    private OnRefreshListener mUpPullRefreshListener;

    public ListAdapter(List<ItemBean> data) {
        super( data );
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getView( parent, viewType );

        if (viewType == TYPE_NORMAL){
            return new InnerHolder( view );
        }else {

            LoaderMoreHolder loaderMoreHolder = new LoaderMoreHolder( view );
            loaderMoreHolder.update( LoaderMoreHolder.LOADER_STATE_LOADING );

            return loaderMoreHolder;
        }

    }


    @Override
    protected View getView(ViewGroup parent, int viewType) {
        View view;
        //根據類型來創建View
        if (viewType == TYPE_NORMAL) {
            //普通的
            view = View.inflate( parent.getContext(), R.layout.item_list_view, null );
        }else {
            //這是加載更多的
            view = View.inflate( parent.getContext(),R.layout.item_list_loader_more,null );
        }
        return view;
    }


    //這個方法用於綁定Holder的,一般用來設置數據
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType( position )==TYPE_NORMAL&& holder instanceof InnerHolder){
            //設置數據
            ((InnerHolder) holder).setData( mData.get( position ), position );
        }else if (getItemViewType( position )==TYPE_LOADER_MORE && holder instanceof LoaderMoreHolder){
            ((LoaderMoreHolder) holder).update( LoaderMoreHolder.LOADER_STATE_LOADING );
        }
    }


    @Override
    public int getItemViewType(int position) {

        if (position +1 == getItemCount()){
            //最後一個則返回加載更多
            return TYPE_LOADER_MORE;

        }
            return TYPE_NORMAL;
    }

    /*
        設置刷新監聽接口
     */
    public void setOnRefreshListener(OnRefreshListener listener) {

        this.mUpPullRefreshListener = listener;
    }

    //定義接口
    public interface OnRefreshListener{
        void OnUpPullRefresh(LoaderMoreHolder loaderMoreHolder);
    }

    public class LoaderMoreHolder extends RecyclerView.ViewHolder{

        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;

        private LinearLayout loading;
        private TextView reload;
        public LoaderMoreHolder(@NonNull View itemView) {
            super( itemView );

            loading = itemView.findViewById( R.id.loading );
            reload = itemView.findViewById( R.id.reload );

            reload.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update( LOADER_STATE_LOADING );
                }
            } );

        }

        public void update(int state){

            //重置控件的狀態
            loading.setVisibility( View.GONE );
            reload.setVisibility( View.GONE );

            switch (state){
                case LOADER_STATE_LOADING :
                    loading.setVisibility( View.VISIBLE );
                    //觸發加載數據
                    startLoaderMore();
                    break;

                case LOADER_STATE_RELOAD :
                    reload.setVisibility( View.VISIBLE );
                    break;

                case LOADER_STATE_NORMAL :
                    loading.setVisibility( View.GONE );
                    reload.setVisibility( View.GONE );
                    break;


            }

            }

        private void startLoaderMore() {

            if (mUpPullRefreshListener != null) {
                mUpPullRefreshListener.OnUpPullRefresh(this);
            }
        }

    }
}
