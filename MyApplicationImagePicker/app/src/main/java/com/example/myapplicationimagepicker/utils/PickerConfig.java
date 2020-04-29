package com.example.myapplicationimagepicker.utils;

import com.example.myapplicationimagepicker.domain.ImageItem;

import java.util.List;

public class PickerConfig {

    private PickerConfig(){

    }

    private static PickerConfig sPickerConfig;

    public static PickerConfig getInstance(){

        if (sPickerConfig==null){

            sPickerConfig = new PickerConfig();
        }
            return sPickerConfig;
    }

    private int maxSelectedCount = 1;
    private OnImageSelectedFinishedListener mImageSelectedFinishedListener = null;

    public int getMaxSelectedCount() {
        return maxSelectedCount;
    }

    public void setMaxSelectedCount(int maxSelectedCount) {
        this.maxSelectedCount = maxSelectedCount;
    }

    public OnImageSelectedFinishedListener getmImageSelectedFinishedListener(){
        return mImageSelectedFinishedListener;

    }


    public void setOnImageSelectedFinishedListener(OnImageSelectedFinishedListener listener){
        this.mImageSelectedFinishedListener = listener;


    }




    public interface OnImageSelectedFinishedListener{

        void onSelectedFinished(List<ImageItem> result);

    }



}
