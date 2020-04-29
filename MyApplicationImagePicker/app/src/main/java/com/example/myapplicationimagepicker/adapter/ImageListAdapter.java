package com.example.myapplicationimagepicker.adapter;

import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplicationimagepicker.R;
import com.example.myapplicationimagepicker.domain.ImageItem;
import com.example.myapplicationimagepicker.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.InnerHolder> {

    //給個集合保存起來,即使外面清空了也不會空指針異常
    private List<ImageItem> mImageItems = new ArrayList<>(  );

    private List<ImageItem> mSelectedItems = new ArrayList<>(  );
    private OnItemSelectedChangeListener mItemSelectedChangeListener = null;

    public static final int MAX_SELECTED_COUNT = 9;
    private int MAXSelectedCount = MAX_SELECTED_COUNT;

    public List<ImageItem> getmSelectedItems() {
        return mSelectedItems;
    }

    public void setmSelectedItems(List<ImageItem> mSelectedItems) {
        this.mSelectedItems = mSelectedItems;
    }

    public int getMAXSelectedCount() {
        return MAXSelectedCount;
    }

    public void setMAXSelectedCount(int MAXSelectedCount) {
        this.MAXSelectedCount = MAXSelectedCount;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //加載ItemView
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_image, parent, false );

        Point point = SizeUtils.getScreenSize( itemView.getContext() );
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(point.x/3,point.x/3  );
        itemView.setLayoutParams( layoutParams );

        return new InnerHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        //綁定數據
        final View itemView = holder.itemView;
        ImageView imageView = itemView.findViewById( R.id.img_iv );
        final ImageItem imageItem = mImageItems.get( position );
        final CheckBox checkBox = itemView.findViewById( R.id.image_check_box );
        final View cover = itemView.findViewById( R.id.image_cover );

        Glide.with( imageView.getContext() ).load( imageItem.getPath()).into( imageView );

        //根據數據狀態顯示內容
        if (mSelectedItems.contains( imageItem )) {
            //沒有選擇上,應該要選擇
            mSelectedItems.add( imageItem );

            //修改UI
            checkBox.setChecked( false );
            cover.setVisibility( View.VISIBLE);
            checkBox.setButtonDrawable( itemView.getContext().getDrawable( R.mipmap.check ) );

        }else {

            //已經選擇上了,應該取消選擇
            mSelectedItems.remove( imageItem );

            //修改UI
            checkBox.setChecked( true );
            checkBox.setButtonDrawable( itemView.getContext().getDrawable( R.mipmap.cancel ) );

            cover.setVisibility( View.GONE );
        }


            itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //是否選擇上
                //如果選擇上就變成取消
                //如果沒選擇上就選上
                if (mSelectedItems.contains( imageItem )) {
                    //已經選擇上了,應該取消選擇
                    mSelectedItems.remove( imageItem );

                    //修改UI
                    checkBox.setChecked( true );
                    checkBox.setButtonDrawable( itemView.getContext().getDrawable( R.mipmap.cancel ) );

                    cover.setVisibility( View.GONE );

                }else {

                    if (mSelectedItems.size() >= MAXSelectedCount ){

                        //給個提示
                        Toast toast = Toast.makeText( checkBox.getContext(), null, Toast.LENGTH_SHORT );
                        toast.setText( "最多可以選擇"+ MAXSelectedCount + "張圖片" );
                        toast.show();
                        return;
                    }

                    //沒有選擇上,應該要選擇
                    mSelectedItems.add( imageItem );

                    //修改UI
                    checkBox.setChecked( false );
                    cover.setVisibility( View.VISIBLE);
                    checkBox.setButtonDrawable( itemView.getContext().getDrawable( R.mipmap.check ) );

                }
                if (mItemSelectedChangeListener!=null) {
                    mItemSelectedChangeListener.onItemSelectedChange( mSelectedItems );
                }

            }
        } );
    }

    public void setOnItemSelectedChangeListener(OnItemSelectedChangeListener listener){
        this.mItemSelectedChangeListener = listener;
    }

    public interface OnItemSelectedChangeListener {
        void onItemSelectedChange(List<ImageItem> selectedItems);

    }



    @Override
    public int getItemCount() {

        return mImageItems.size();
    }

    public void setData(List<ImageItem> imageItems) {

        mImageItems.clear();
        mImageItems.addAll( imageItems );
        notifyDataSetChanged();

    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super( itemView );
        }
    }
}
