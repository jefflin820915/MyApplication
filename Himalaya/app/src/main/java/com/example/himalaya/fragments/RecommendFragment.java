package com.example.himalaya.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.himalaya.DetailActivity;
import com.example.himalaya.R;
import com.example.himalaya.adapaters.RecommendLIstAdapter;
import com.example.himalaya.base.BaseFragment;
import com.example.himalaya.interfances.IRecommendViewCallBack;
import com.example.himalaya.presenters.AlbumDetailPresenter;
import com.example.himalaya.presenters.RecommendPresenter;
import com.example.himalaya.utils.LogUtil;
import com.example.himalaya.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendFragment extends BaseFragment implements IRecommendViewCallBack, RecommendLIstAdapter.OnRecommendItemClickListener {


    private View mRootView;
    private RecyclerView mRecommendList;
    private RecommendLIstAdapter mRecommendLIstAdapter;
    private RecommendPresenter mRecommendPresenter;
    private UILoader mUILoader;


    @Override
    protected View onSubViewLoaded(final LayoutInflater layoutInflater, ViewGroup container) {

        mUILoader = new UILoader(getContext()) {
            @Override
            protected View getSuccessView(ViewGroup container) {
                return createSuccessView(layoutInflater, container);
            }
        };

        //獲取到邏輯層的對象
        mRecommendPresenter = RecommendPresenter.getInstance();
        //先要設置通知接口的註冊
        mRecommendPresenter.registerViewCallBack(this);
        //獲取推薦列表
        mRecommendPresenter.getRecommendList();

        if (mUILoader.getParent() instanceof ViewGroup) {
            ((ViewGroup) mUILoader.getParent()).removeView(mUILoader);
        }

        mUILoader.setOnRetryClickListener(new UILoader.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                //表示網路不佳時用戶點擊重試
                //重新獲取數據即可
                if (mRecommendPresenter != null) {
                    mRecommendPresenter.getRecommendList();
                }
            }
        });

        //返回VIEW, 給介面顯示
        return mUILoader;
    }

    private View createSuccessView(LayoutInflater layoutInflater, ViewGroup container) {
        //VIEW已經加載完成
        mRootView = layoutInflater.inflate(R.layout.fragment_recommend, container, false);
        //recyclerview的使用
        //1.找到對應的控件
        mRecommendList = mRootView.findViewById(R.id.recommend_list);

        //2.設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecommendList.setLayoutManager(linearLayoutManager);

        //設置清單邊框為圓角
        mRecommendList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = UIUtil.dip2px(view.getContext(), 5);
                outRect.bottom = UIUtil.dip2px(view.getContext(), 5);
                outRect.left = UIUtil.dip2px(view.getContext(), 5);
                outRect.right = UIUtil.dip2px(view.getContext(), 5);
            }
        });

        //3.設置適配器
        mRecommendLIstAdapter = new RecommendLIstAdapter();
        mRecommendList.setAdapter(mRecommendLIstAdapter);
        mRecommendLIstAdapter.setOnRecommendItemClickListener(this);
        return mRootView;
    }

    /**
     * 獲取推薦內容, 其實就是猜你喜歡
     * 這個接口: 3.10.6 獲取猜你喜歡專輯
     */

    @Override
    public void onRecommendListLoaded(List<Album> result) {
        LogUtil.v("jeff", "onRecommendListLoaded");
        //當我們獲取到推薦內容的時候,這個方法就會被調用(成功了)
        //數據回來以後就是更新UI
        //把數據設置給適配器並且更新
        mRecommendLIstAdapter.setData(result);
        mUILoader.updateStatus(UILoader.UIStatus.SUCCESS);
    }

    @Override
    public void onNetworkError() {
        LogUtil.v("jeff", "onNetWorkError");
        mUILoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR);
    }

    @Override
    public void onEmpty() {
        LogUtil.v("jeff", "onEmpty");

        mUILoader.updateStatus(UILoader.UIStatus.EMPTY);
    }

    @Override
    public void onLoading() {
        LogUtil.v("jeff", "onLoading");

        mUILoader.updateStatus(UILoader.UIStatus.LOADING);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消接口的註冊,以免內存洩漏
        if (mRecommendPresenter != null) {
            mRecommendPresenter.unRegisterViewCallBack(this);
        }
    }

    @Override
    public void onItemClick(int position, Album album) {

        AlbumDetailPresenter.getInstance().setTargetAlbum(album);
        //item條目被點擊了,跳轉到Detail詳情頁面
        Intent intent = new Intent();
        intent.setClass(getContext(), DetailActivity.class);
        startActivity(intent);
    }
}