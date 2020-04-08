package com.example.myapplicationactivitylaunchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *  stardard模式:
 *
 *  1.stardard模式的話就是會創建新的任務,並且於當前的棧頂,當我們點擊返回的時候,其實就是銷毀當前任務,移除當前任務,也就是出棧的過程
 *
 *  創建多少個,就要點多少次返回去退出
 *
 *  2.使用場景: 大多數場景都是使用這個模式
 *
 *  3.如果我們不進行配置, 那麼默認的啟動模式就是這個standard
 *
 *              android:launchMode="standard"
 *
 *
 *  singleTop模式:
 *
 *  1.singleTop模式的話表示, 如果棧類已經是當前的任務了, 那麼就不會再創建新的任務
 *
 *  2.使用場景: 一般來說為了保證只有一個任務,不被創建多個,所以就需要這種模式,比如說我們的瀏覽器的書籤,我們應用的通知推送
 *
 *  3.配置, 把配置里的內容配置成singleTop即可
 *
 *              android:launchMode="singleTop"
 *
 *
 *  singleTask模式:
 *
 *  1.如果我們要創建的任務沒有,就會創建任務,並且放在棧頂
 *
 *  2.如果我們要創建的任務已存在,就會把這個任務以上全部從棧中移除,使得當前任務成為最頂部的任務
 *
 *  3.當我們棧頂的任務已經是我們要打開的任務時,已經滿足了第二條件,已經存在棧中,但頂部沒有別的任務,所以不會再創建新的任務
 *
 *  使用場景: 當我們這個任務佔的資源相對比較大的時候,就使用singleTask模式
 *
 *                android:launchMode="singleTop"
 *
 *
 *  singleInstance模式:
 *
 *  1.前三個是在同個任務棧,但是singleInstance是獨立的一個任務棧,他是一個單一的對象,獨佔一個棧,不會再創建,只是會把他提前
 *
 *  使用場景: 在整個系統中只有唯一一個實例,比如說我們的Launcher,只有一個,比如說辭典的取詞,因為他在每個介面都可以取詞
 *
 *
 *              android:launchMode="singleInstance"
 *
 */


public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_first );


    }


    public void openFirst(View view) {
        //打開第一個Activity
        startActivity( new Intent( this,FirstActivity.class ) );

    }

    public void openSecond(View view) {
        //打開第二個Activity
        startActivity( new Intent( this,SecondActivity.class ) );


    }
}
