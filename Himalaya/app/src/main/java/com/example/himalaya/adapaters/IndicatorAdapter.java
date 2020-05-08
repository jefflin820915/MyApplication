package com.example.himalaya.adapaters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.example.himalaya.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;


public class IndicatorAdapter extends CommonNavigatorAdapter {


    private final String[] mTitles;
    private OnIndicatorTapClickListener mOnTabClickListener;

    public IndicatorAdapter(Context context) {
        mTitles = context.getResources().getStringArray( R.array.indicator_name );
    }

    @Override
    public int getCount() {
        if (mTitles != null) {

            return mTitles.length;

        }
        return 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {

        //創建View
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView( context );

        //設置一般情況下的顏色為灰色
        colorTransitionPagerTitleView.setNormalColor( Color.parseColor( "#aaffffff" ) );

        //設置選中情況下的顏色為白色
        colorTransitionPagerTitleView.setSelectedColor( Color.WHITE );

        //設置文字大小,單位sp
        colorTransitionPagerTitleView.setTextSize( 18 );

        //設置要顯示內容
        colorTransitionPagerTitleView.setText( mTitles[index] );

        //設置title的點擊事件,這裡的話,如果點擊title,那麼就選中下面的viewPager到對應的index裏面去
        //當我們點擊了title的時候,下面的viewPager會對應著index進行切換內容
        colorTransitionPagerTitleView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切換viewPager的內容,如果index不一樣的話
                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onTabClick( index );
                }
            }
        } );
        //把這個創建好的view返回回去
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {

        LinePagerIndicator linePagerIndicator = new LinePagerIndicator( context );
        linePagerIndicator.setMode( LinePagerIndicator.MODE_WRAP_CONTENT );
        linePagerIndicator.setColors( Color.parseColor( "#ffffff" ) );
        return linePagerIndicator;

    }

    public void setOnIndicatorTapClickListener(OnIndicatorTapClickListener listener) {
        this.mOnTabClickListener = listener;
    }


    public interface OnIndicatorTapClickListener {
        void onTabClick(int index);
    }


}
