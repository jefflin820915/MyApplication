package com.example.himalaya;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.himalaya.base.BaseActivity;
import com.example.himalaya.interfances.IPlayerCallBack;
import com.example.himalaya.presenters.PlayerPresenter;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.text.SimpleDateFormat;
import java.util.List;

public class PlayerActivity extends BaseActivity implements IPlayerCallBack {

    private ImageView mControlBtn;
    private PlayerPresenter mPlayerPresenter;
    private SimpleDateFormat mMinFormat = new SimpleDateFormat("mm:ss");
    private SimpleDateFormat mHourFormat = new SimpleDateFormat("hh:mm:ss");
    private TextView mTotalDuration;
    private TextView mCurrentPosition;
    private SeekBar mTrackSeekBar;
    private int mCurrentProgress = 0 ;
    private boolean mIsUserTouchProgressBar = false;
    private ImageView mPlayNext;
    private ImageView mPlayPre;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);

        mPlayerPresenter = PlayerPresenter.getPlayerPresenter();
        mPlayerPresenter.registerViewCallBack(this);

        initView();

        initEvent();

        startPlay();
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
     * 開始播放
     */
    private void startPlay() {

        if (mPlayerPresenter != null) {

            mPlayerPresenter.play();

        }
    }

    /**
     * 給控件設置相關的事件
     */
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
                if (mPlayerPresenter!=null) {
                    mPlayerPresenter.playNext();
                }

            }
        });

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

    }

    @Override
    public void onPlayStart() {

        //開始播放, 修改UI成暫停的按鈕

        if (mControlBtn != null) {

            mControlBtn.setImageResource(R.mipmap.stop_normal);
        }
    }

    @Override
    public void onPlayPause() {

        if (mControlBtn != null) {

            mControlBtn.setImageResource(R.mipmap.play_normal);
        }
    }

    @Override
    public void onPlayStop() {

        if (mControlBtn != null) {

            mControlBtn.setImageResource(R.mipmap.play_normal);
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

    }

    @Override
    public void onPlayModeChange(XmPlayListControl.PlayMode playMode) {

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
}


