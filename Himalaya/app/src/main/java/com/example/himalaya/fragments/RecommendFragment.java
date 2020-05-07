package com.example.himalaya.fragments;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.himalaya.R;
import com.example.himalaya.adapaters.RecommendLIstAdapter;
import com.example.himalaya.base.BaseFragment;
import com.example.himalaya.interfances.IRecommendViewCallBack;
import com.example.himalaya.presenters.RecommendPresenter;
import com.example.himalaya.utils.Constants;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendFragment extends BaseFragment implements IRecommendViewCallBack {


    private View mRootView;
    private RecyclerView mRecommendList;
    private RecommendLIstAdapter mRecommendLIstAdapter;
    private RecommendPresenter mRecommendPresenter;


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        //VIEW已經加載完成
        mRootView = layoutInflater.inflate( R.layout.fragment_recommend, container,false );

        //recyclerview的使用
        //1.找到對應的控件
        mRecommendList = mRootView.findViewById( R.id.recommend_list );

        //2.設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext());
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        mRecommendList.setLayoutManager( linearLayoutManager );
        mRecommendList.addItemDecoration( new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = UIUtil.dip2px(view.getContext(), 5 );
                outRect.bottom = UIUtil.dip2px( view.getContext(),5 );
                outRect.left = UIUtil.dip2px( view.getContext(),5 );
                outRect.right = UIUtil.dip2px( view.getContext(),5 );
            }
        } );

        //3.設置適配器
        mRecommendLIstAdapter = new RecommendLIstAdapter();
        mRecommendList.setAdapter( mRecommendLIstAdapter );

        //獲取到邏輯層的對象
        mRecommendPresenter = RecommendPresenter.getInstance();
        //先要設置通知接口的註冊
        mRecommendPresenter.registerViewCallBack( this );
        //獲取推薦列表
        mRecommendPresenter.getRecommendList();

        //返回VIEW, 給介面顯示
        return mRootView;
    }

    /**
     * 獲取推薦內容, 其實就是猜你喜歡
     * 這個接口: 3.10.6 獲取猜你喜歡專輯
     */


    @Override
    public void onRecommendListLoaded(List<Album> result) {
        //當我們獲取到推薦內容的時候,這個方法就會被調用(成功了)
        //數據回來以後就是更新UI
        //把數據設置給適配器並且更新
        mRecommendLIstAdapter.setData(result);
    }

    @Override
    public void onLoadMore(List<Album> result) {

    }

    @Override
    public void onRefreshMore(List<Album> result) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消接口的註冊,以免內存洩漏
        if (mRecommendPresenter!=null) {
            mRecommendPresenter.unRegisterViewCallBack( this );
        }
    }
}
