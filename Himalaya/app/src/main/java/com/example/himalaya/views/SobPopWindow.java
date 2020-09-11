package com.example.himalaya.views;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.himalaya.R;
import com.example.himalaya.base.BaseApplication;

public class SobPopWindow extends PopupWindow {

    public SobPopWindow(){

        //設置他的寬高
        super(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);


        //這裡要注意,設置setOutsideTouchable之前,先要設置:setBackgroundDrawable,
        //否則點擊外部無法關閉pop.
        //但這邊只要setOutsideTouchable就能關閉,可能版本?
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setOutsideTouchable(true);

        //載進來VIEW
        View popView = LayoutInflater.from(BaseApplication.getAppContext()).inflate(R.layout.pop_play_list, null);

        //設置內容
        setContentView(popView);
    }
}
