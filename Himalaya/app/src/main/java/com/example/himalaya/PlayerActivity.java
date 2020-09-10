package com.example.himalaya;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.himalaya.adapaters.PlayerTrackPageAdapter;
import com.example.himalaya.base.BaseActivity;
import com.example.himalaya.interfances.IPlayerCallBack;
import com.example.himalaya.presenters.PlayerPresenter;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl.PlayMode.PLAY_MODEL_LIST;
import static com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl.PlayMode.PLAY_MODEL_LIST_LOOP;
import static com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl.PlayMode.PLAY_MODEL_RANDOM;
import static com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE_LOOP;

public class PlayerActivity extends BaseActivity implements IPlayerCallBack, ViewPager.OnPageChangeListener {

    private ImageView mControlBtn;
    private PlayerPresenter mPlayerPresenter;
    private SimpleDateFormat mMinFormat = new SimpleDateFormat("mm:ss");
    private SimpleDateFormat mHourFormat = new SimpleDateFormat("hh:mm:ss");
    private TextView mTotalDuration;
    private TextView mCurrentPosition;
    private SeekBar mTrackSeekBar;
    private int mCurrentProgress = 0;
    private boolean mIsUserTouchProgressBar = false;
    private ImageView mPlayNext;
    private ImageView mPlayPre;
    private TextView mTrackTitle;
    private String mTrackTitleText;
    private ViewPager mTrackPagerView;
    private PlayerTrackPageAdapter mTrackPageAdapter;
    private boolean mIsUserSlidePage = false;
    private ImageView mPlayerModeSwitchBtn;

    private XmPlayListControl.PlayMode mCurrentMode = PLAY_MODEL_LIST;

    //
    private static Map<XmPlayListControl.PlayMode, XmPlayListControl.PlayMode> sPlayModeRule = new HashMap<>();

    //處理播放模式的切換
    //1. 默認是:列表播放 PLAY_MODEL_LIST
    //2. 列表循環: PLAY_MODEL_LIST_LOOP
    //3. 隨機播放: PLAY_MODEL_RANDOM
    //4. 單曲循環: PLAY_MODEL_SINGLE_LOOP

    static {
        sPlayModeRule.put(PLAY_MODEL_LIST, PLAY_MODEL_LIST_LOOP);
        sPlayModeRule.put(PLAY_MODEL_LIST_LOOP, PLAY_MODEL_RANDOM);
        sPlayModeRule.put(PLAY_MODEL_RANDOM, PLAY_MODEL_SINGLE_LOOP);
        sPlayModeRule.put(PLAY_MODEL_SINGLE_LOOP, PLAY_MODEL_LIST);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);

        initView();

        mPlayerPresenter = PlayerPresenter.getPlayerPresenter();
        mPlayerPresenter.registerViewCallBack(this);

        //在介面初始化以後才去獲取數據
        mPlayerPresenter.getPlayList();

        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //釋放資源
        if (mPlayerPresenter == null) {

            mPlayerPresenter.unRegisterViewCallBack(this);
            mPlayerPresenter = null;
        }
    }


    /**
     * 給控件設置相關的事件
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        mControlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //如果現在的狀態是正在播放的,那麼就暫停
                //todo:
                if (mPlayerPresenter.isPlay()) {

                    mPlayerPresenter.pause();
                } else {

                    //如果現在的狀態是非播放的,那麼就讓播放器播放
                    mPlayerPresenter.play();
                }
            }
        });

        mTrackSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mCurrentProgress = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                mIsUserTouchProgressBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mIsUserTouchProgressBar = false;
                //手離開拖動進度條的時候更新進度
                mPlayerPresenter.SeekTo(mCurrentProgress);
            }
        });

        mPlayPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放上一首
                if (mPlayerPresenter != null) {
                    mPlayerPresenter.playPre();
                }

            }
        });

        mPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放下一首
                if (mPlayerPresenter != null) {
                    mPlayerPresenter.playNext();
                }

            }
        });

        mTrackPagerView.addOnPageChangeListener(this);

        mTrackPagerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {

                    case MotionEvent.ACTION_DOWN:

                        mIsUserSlidePage = true;
                        break;

                }
                return false;
            }
        });


        mPlayerModeSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //把當前的mode獲取到下一個mode
                XmPlayListControl.PlayMode playMode = sPlayModeRule.get(mCurrentMode);
                //修改播放模式
                if (mPlayerPresenter != null) {

                    mPlayerPresenter.switchPlayMode(playMode);
                }
            }
        });

    }

    /**
     * 根據當前的狀態,更新播放模式
     * <p>
     * PLAY_MODEL_LIST
     * : PLAY_MODEL_LIST_LOOP
     * : PLAY_MODEL_RANDOM
     * : PLAY_MODEL_SINGLE_LOOP
     */
    private void updatePlayModeBtnImg() {

        int resId = R.drawable.selector_player_play_mode_list_order;

        switch (mCurrentMode) {

            case PLAY_MODEL_LIST:
                resId = R.drawable.selector_player_play_mode_list_order;
                break;

            case PLAY_MODEL_RANDOM:
                resId = R.drawable.selector_player_play_mode_random;

                break;

            case PLAY_MODEL_LIST_LOOP:
                resId = R.drawable.selector_player_play_mode_list_order_loop;

                break;

            case PLAY_MODEL_SINGLE_LOOP:
                resId = R.drawable.selector_player_play_mode_single_loop;

                break;
        }
        mPlayerModeSwitchBtn.setImageResource(resId);
    }

    /**
     * 找到各種控件
     */
    private void initView() {

        mControlBtn = this.findViewById(R.id.play_or_pause_btn);
        mTotalDuration = this.findViewById(R.id.track_duration);
        mCurrentPosition = this.findViewById(R.id.current_position);
        mTrackSeekBar = this.findViewById(R.id.track_seek_bar);
        mPlayNext = this.findViewById(R.id.play_next);
        mPlayPre = this.findViewById(R.id.play_pre);

        mTrackTitle = this.findViewById(R.id.track_title);
        if (!TextUtils.isEmpty(mTrackTitleText)) {
            mTrackTitle.setText(mTrackTitleText);
        }

        mTrackPagerView = this.findViewById(R.id.track_pager_view);
        //創建適配器
        mTrackPageAdapter = new PlayerTrackPageAdapter();
        //設置適配器
        mTrackPagerView.setAdapter(mTrackPageAdapter);

        //切換播放模式按鈕
        mPlayerModeSwitchBtn = this.findViewById(R.id.player_mode_switch_btn);

    }

    @Override
    public void onPlayStart() {

        //開始播放, 修改UI成暫停的按鈕

        if (mControlBtn != null) {

            mControlBtn.setImageResource(R.drawable.selector_player_stop);
        }
    }

    @Override
    public void onPlayPause() {

        if (mControlBtn != null) {

            mControlBtn.setImageResource(R.drawable.selector_player_play);
        }
    }

    @Override
    public void onPlayStop() {

        if (mControlBtn != null) {

            mControlBtn.setImageResource(R.drawable.selector_player_play);
        }
    }

    @Override
    public void onPlayError() {

    }

    @Override
    public void onNextPlay(Track track) {

    }

    @Override
    public void onPrePlay(Track track) {

    }

    @Override
    public void onListLoad(List<Track> list) {

        LogUtil.v("jeff", "list --> " + list);
        //把數據到適配器裡
        if (mTrackPageAdapter != null) {

            mTrackPageAdapter.setData(list);
        }

    }

    @Override
    public void onPlayModeChange(XmPlayListControl.PlayMode playMode) {

        //更新播放模式並寫修改UI
        mCurrentMode = playMode;
        updatePlayModeBtnImg();

    }

    @Override
    public void onProgressChange(int currentDuration, int total) {

        mTrackSeekBar.setMax(total);

        //更新播放進度, 更新進度條
        String totalDuration;
        String currentPosition;

        if (total > 1000 * 60 * 60) {
            totalDuration = mHourFormat.format(total);
            currentPosition = mHourFormat.format(currentDuration);
        } else {
            totalDuration = mMinFormat.format(total);
            currentPosition = mMinFormat.format(currentDuration);
        }

        if (mTotalDuration != null) {
            mTotalDuration.setText(totalDuration);
        }

        //更新當前時間
        if (mCurrentPosition != null) {
            mCurrentPosition.setText(currentPosition);
        }

        //更新進度
        //計算當前的進度
        if (!mIsUserTouchProgressBar) {
            mTrackSeekBar.setProgress(currentDuration);
        }
    }

    @Override
    public void onAdLoading() {

    }

    @Override
    public void onAdFinished() {

    }

    @Override
    public void onTrackUpdate(Track track, int playIndex) {

        this.mTrackTitleText = track.getTrackTitle();

        if (mTrackTitle != null) {
            //設置當前節目的標題
            mTrackTitle.setText(mTrackTitleText);
        }
        //當節目改變的時候,就獲取到當前播放器中改變的位置
        //當前的節目改變以後,要修改頁面的照片
        if (mTrackPagerView != null) {
            mTrackPagerView.setCurrentItem(playIndex, true);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        LogUtil.v("jeff", "position --> " + position);
        //當頁面選中的時候,就去切換播放內容
        if (mPlayerPresenter != null && mIsUserSlidePage) {

            mPlayerPresenter.playIndex(position);

        }
        mIsUserSlidePage = false;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


