package com.example.himalaya.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.himalaya.R;
import com.example.himalaya.adapaters.RecommendLIstAdapter;
import com.example.himalaya.base.BaseFragment;
import com.example.himalaya.utils.Constants;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendFragment extends BaseFragment {


    private View mRootView;
    private RecyclerView mRecommendList;
    private RecommendLIstAdapter mRecommendLIstAdapter;


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

        //3.設置適配器
        mRecommendLIstAdapter = new RecommendLIstAdapter();
        mRecommendList.setAdapter( mRecommendLIstAdapter );

        //去拿數據回來
        getRecommendData();

        //返回VIEW, 給介面顯示
        return mRootView;
    }

    /**
     * 獲取推薦內容, 其實就是猜你喜歡
     * 這個接口: 3.10.6 獲取猜你喜歡專輯
     */
    private void getRecommendData() {

        //封裝參數
        Map<String, String> map = new HashMap<>();
        //這個參數表示一頁數據返回多少條
        map.put( DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT + "" );
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {

                LogUtil.v( "jeff","thread name -->"+ Thread.currentThread().getName() );

                //數據獲取成功
                if (gussLikeAlbumList !=null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();

                    //數據回來以後,更新UI
                    upRecommendUI(albumList);
                }
            }

            @Override
            public void onError(int i, String s) {
                //數據獲取出錯
                LogUtil.v( "jeff","error --> " + i );
                LogUtil.v( "jeff","errorMsg -->" + s );
            }
        });
    }

    private void upRecommendUI(List<Album> albumList) {
        //把數據設置給適配器並且更新
        mRecommendLIstAdapter.setData(albumList);
    }
}
