package com.example.himalaya.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.himalaya.R;
import com.example.himalaya.base.BaseApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class UILoader extends FrameLayout {

    private View mLoadingView;
    private View mSuccessView;
    private View mNetWorkErrorView;
    private View mEmptyView;

    public enum UIStatus {
        LOADING, SUCCESS, NETWORK_ERROR, EMPTY, NONE
    }

    public UIStatus mCurrentStatus = UIStatus.NONE;


    public UILoader(@NonNull Context context) {
        this( context, null );
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this( context, attrs, 0 );
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        //
        init();

    }
    public void updateStatus(UIStatus status){
        mCurrentStatus = status;
        //更新UI一定要在主線程上
        BaseApplication.getHandler().post( new Runnable() {
            @Override
            public void run() {

                switchUIByCurrentStatus();

            }
        } );

    }

    /**
     * 初始化UI
     */
    private void init() {
        switchUIByCurrentStatus();
    }

    private void switchUIByCurrentStatus() {
        //加載中
        if (mLoadingView == null) {

            mLoadingView = getLoadingView();
            addView( mLoadingView );
        }
        //根據狀態設置是否可見
        mLoadingView.setVisibility( mCurrentStatus == UIStatus.LOADING ? VISIBLE : GONE );

        //成功
        if (mSuccessView == null) {

            mSuccessView = getSuccessView(this);
            addView( mSuccessView );
        }
        //根據狀態設置是否可見
        mSuccessView.setVisibility( mCurrentStatus == UIStatus.SUCCESS ? VISIBLE : GONE );

        //網路錯誤頁面
        if (mNetWorkErrorView == null) {

            mNetWorkErrorView = getNetWorkErrorView();
            addView( mNetWorkErrorView );
        }
        //根據狀態設置是否可見
        mNetWorkErrorView.setVisibility( mCurrentStatus == UIStatus.NETWORK_ERROR ? VISIBLE : GONE );

        //數據為空的介面
        if (mEmptyView == null) {

            mEmptyView = getEmptyView();
            addView( mEmptyView );
        }
        //根據狀態設置是否可見
        mEmptyView.setVisibility( mCurrentStatus == UIStatus.EMPTY ? VISIBLE : GONE );

    }

    private View getEmptyView() {
        return LayoutInflater.from( getContext()).inflate( R.layout.fragment_empty_view,this,false);
    }

    private View getNetWorkErrorView() {
        return LayoutInflater.from( getContext()).inflate( R.layout.fragment_error_view,this,false );
    }

    protected abstract View getSuccessView(ViewGroup container);


    private View getLoadingView() {
        return LayoutInflater.from( getContext()).inflate( R.layout.fragment_loading_view,this,false );
    }
}
