package com.example.himalaya;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.himalaya.adapaters.IndicatorAdapter;
import com.example.himalaya.adapaters.MainContentAdapter;
import com.example.himalaya.utils.LogUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

public class MainActivity extends FragmentActivity {

    private MagicIndicator mMagicIndicator;
    private ViewPager mContentPager;
    private IndicatorAdapter mIndicatorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initView();
        initEvent();

    }

    private void initEvent() {
        mIndicatorAdapter.setOnIndicatorTapClickListener( new IndicatorAdapter.OnIndicatorTapClickListener() {
            @Override
            public void onTabClick(int index) {
                LogUtil.v( "jeff","click index is --> " + index );
                if (mContentPager!=null) {
                    mContentPager.setCurrentItem(index);
                }
            }
        } );
    }

    private void initView() {
        mMagicIndicator = this.findViewById( R.id.main_indicator );
        mMagicIndicator.setBackgroundColor( this.getResources().getColor( R.color.main_color ) );

        //創建indicator的適配器
        mIndicatorAdapter = new IndicatorAdapter(this);
        CommonNavigator commonNavigator = new CommonNavigator( this );
        commonNavigator.setAdjustMode( true );
        commonNavigator.setAdapter( mIndicatorAdapter );

        //ViewPager
        mContentPager = this.findViewById( R.id.content_page );

        //創建內容適配器
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MainContentAdapter mainContentAdapter = new MainContentAdapter( supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        mContentPager.setAdapter( mainContentAdapter );

        //把ViewPager和indicator綁定到一起
        mMagicIndicator.setNavigator( commonNavigator );
        ViewPagerHelper.bind( mMagicIndicator, mContentPager );

    }
}
