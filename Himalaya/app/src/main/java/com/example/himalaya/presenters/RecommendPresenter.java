package com.example.himalaya.presenters;

import com.example.himalaya.interfances.IRecommendPresenter;
import com.example.himalaya.interfances.IRecommendViewCallBack;
import com.example.himalaya.utils.Constants;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendPresenter implements IRecommendPresenter {

    private List<IRecommendViewCallBack> mCallBacks = new ArrayList<>(  );

    private RecommendPresenter(){

    }

    private static RecommendPresenter sInstance = null;

    /**
     * 獲取單例對象
     *
     * @return
     */
    public static RecommendPresenter getInstance(){
        if (sInstance ==null) {
            synchronized (RecommendPresenter.class){
                if (sInstance == null) {
                    sInstance = new RecommendPresenter();
                }
            }
        }

        return sInstance;
    }

    @Override
    public void getRecommendList() {

        //獲取推薦內容
        //封裝參數
        updateLoading();
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
                    //upRecommendUI(albumList);
                    handleRecommendResult(albumList);
                }
            }

            @Override
            public void onError(int i, String s) {
                //數據獲取出錯
                LogUtil.v( "jeff","error --> " + i );
                LogUtil.v( "jeff","errorMsg -->" + s );
                handleError();

            }
        });
    }

    private void handleError() {

        if (mCallBacks!=null) {
            for (IRecommendViewCallBack callBack : mCallBacks) {
                callBack.onNetworkError(  );
            }
        }
    }

    private void handleRecommendResult(List<Album> albumList) {

        //通知UI更新
        if (albumList!=null) {

            //測試.清空一下,讓介面顯示空
            //albumList.clear();

            if (albumList.size() == 0) {
                for (IRecommendViewCallBack callBack : mCallBacks) {
                    callBack.onEmpty(  );
                }

            }else {

                if (mCallBacks!=null) {
                    for (IRecommendViewCallBack callBack : mCallBacks) {

                        callBack.onRecommendListLoaded( albumList );
                    }
                }
            }
        }
    }

    private void updateLoading(){
        for (IRecommendViewCallBack callBack : mCallBacks) {
            callBack.onLoading( );
        }
    }
    @Override
    public void pull2RefreshMore() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void registerViewCallBack(IRecommendViewCallBack callBack) {
        if (mCallBacks!=null && !mCallBacks.contains( callBack )) {
            mCallBacks.add(callBack );
        }
    }

    @Override
    public void unRegisterViewCallBack(IRecommendViewCallBack callBack) {
        if (mCallBacks!=null) {
            mCallBacks.remove( mCallBacks );
        }
    }
}
