package com.example.himalaya;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himalaya.adapaters.DetailAdapter;
import com.example.himalaya.base.BaseActivity;
import com.example.himalaya.interfances.IAlbumDetailViewCallBack;
import com.example.himalaya.presenters.AlbumDetailPresenter;
import com.example.himalaya.utils.ImageBlur;
import com.example.himalaya.utils.LogUtil;
import com.example.himalaya.views.RoundRectImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailActivity extends BaseActivity implements IAlbumDetailViewCallBack {

    private ImageView mLargeCover;
    private RoundRectImageView mSmallCover;
    private TextView mAlbumTitle;
    private TextView mAlbumAuthor;
    private AlbumDetailPresenter mAlbumDetailPresenter;
    private int mCurrentPage = 1;
    private RecyclerView mDetailRecyclerView;
    private DetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
        getWindow().setStatusBarColor( Color.TRANSPARENT );

        initView();
        mAlbumDetailPresenter = AlbumDetailPresenter.getInstance();
        mAlbumDetailPresenter.registerViewCallBack( this );


    }

    private void initView() {
        mLargeCover = this.findViewById( R.id.iv_large_cover );
        mSmallCover = this.findViewById( R.id.viv_small_color );
        mAlbumTitle = this.findViewById( R.id.tv_album_title );
        mAlbumAuthor = this.findViewById( R.id.tv_album_author );
        mDetailRecyclerView = this.findViewById( R.id.detail_recycler_view );

        //RecyclerView步驟:
        // 1.設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        mDetailRecyclerView.setLayoutManager( linearLayoutManager );

        // 2.設置適配器
        mDetailAdapter = new DetailAdapter();
        mDetailRecyclerView.setAdapter( mDetailAdapter );

        //設置item的上下間距
        mDetailRecyclerView.addItemDecoration( new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = UIUtil.dip2px( view.getContext(),2 );
                outRect.bottom = UIUtil.dip2px( view.getContext(),2 );
                outRect.left = UIUtil.dip2px( view.getContext(),2 );
                outRect.right = UIUtil.dip2px( view.getContext(),2 );

            }
        } );

    }

    @Override
    public void onDetailListLoaded(List<Track> tracks) {

        //更新/設置UI數據

        mDetailAdapter.setData(tracks);
    }

    @Override
    public void onAlbumLoaded(Album album) {

        long id = album.getId();
        LogUtil.v( "jeff", "albumId --> " + id );

        //獲取專輯詳情的內容
        mAlbumDetailPresenter.getAlbumDetail( (int) id, mCurrentPage );

        if (mAlbumTitle != null) {
            mAlbumTitle.setText( album.getAlbumTitle() );

        }
        if (mAlbumAuthor != null) {
            mAlbumAuthor.setText( album.getAnnouncer().getNickname() );
        }

        //做毛玻璃效果
        if (mLargeCover != null && null != mLargeCover) {
            //Picasso獲取圖片為異步,所以不能直接去請求,不然如果為空就崩潰了
            Picasso.with( this ).load( album.getCoverUrlLarge() ).into( mLargeCover, new Callback() {
                @Override
                public void onSuccess() {
                    Drawable drawable = mLargeCover.getDrawable();
                    if (drawable != null) {
                        //到這裡才說明是有圖片的
                        ImageBlur.makeBlur( mLargeCover, DetailActivity.this );
                    }
                }

                @Override
                public void onError() {
                    LogUtil.v( "jeff", "onError" );
                }
            } );
        }
        if (mSmallCover != null) {
            Picasso.with( this ).load( album.getCoverUrlLarge() ).into( mSmallCover );
        }
    }
}
