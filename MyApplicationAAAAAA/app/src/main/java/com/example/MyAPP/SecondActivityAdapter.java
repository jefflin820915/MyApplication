package com.example.MyAPP;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;


public class SecondActivityAdapter extends RecyclerView.Adapter<SecondActivityAdapter.InnerHolder> {

    private Context mContext;
    private List<HashMap<String, String>> mList;
    private String mThumbnailUrl;


    public SecondActivityAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void addList(List<HashMap<String, String>> list) {
        mList.addAll( list );
        notifyDataSetChanged();
    }

    //在這裡面創建條目
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.activity_sec_adapter, parent, false );
        return new InnerHolder( view );
    }

    //綁定holder,用來設置數據
    @Override
    public void onBindViewHolder(@NonNull final InnerHolder holder, final int position) {

        //imageView設置拿到mList的位置並key為id的mList資料
        holder.mIdView.setText( mList.get( position ).get( "id" ) );

        //TextView設置拿到mList的位置並key為title的mList資料
        holder.mTitleView.setText( mList.get( position ).get( "title" ) );

        //拿到mList位置並key為thumbnailUrl的資料並設一個變數
        mThumbnailUrl = mList.get( position ).get( "thumbnailUrl" );

        //傳想設置的imageView和URL給LoadTAPIImage
        new LoadTAPIImage( holder.mImgView ).execute( mThumbnailUrl );


//        holder.mImgView.post( new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    URL url = new URL( mThumbnailUrl );
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setConnectTimeout( 10000000 );
//                    connection.setRequestMethod( "GET" );
//                    connection.connect();
//                    InputStream inputStream = connection.getInputStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream( inputStream );
//                    holder.mImgView.setImageBitmap( bitmap );
//
//                } catch (Exception e) {
//                }
//            }
//        } );

        Log.v( "jeff", "thumbnailUrl--> " + mThumbnailUrl );

        //每個item監聽點擊事件
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextToThirdPage( position );
            }
        } );
    }

    //使用意圖傳資料給第三頁
    //跳轉到第三頁
    private void nextToThirdPage(int position) {

        Intent intent = new Intent();
        intent.setClass( mContext, ThirdActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS );
        intent.putExtra( "ItemData", mList.get( position ) );
        mContext.startActivity( intent );
    }

    //返回條目個數
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
