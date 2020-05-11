package com.example.himalaya;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.List;

import androidx.annotation.Nullable;

public class DetailActivity extends BaseActivity implements IAlbumDetailViewCallBack {

    private ImageView mLargeCover;
    private RoundRectImageView mSmallCover;
    private TextView mAlbumTitle;
    private TextView mAlbumAuthor;
    private AlbumDetailPresenter mAlbumDetailPresenter;

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


    }

    @Override
    public void onDetailListLoaded(List<Track> tracks) {

    }

    @Override
    public void onAlbumLoaded(Album album) {
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
