package com.example.myapplicationrecyclertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    總結:
    1.首先我們要有控件,RecyclerView在V7包下,需要打開Module Setting裡的依賴
    添加RecyclerView的依賴, 這樣才能在布局文件裡使用RecyclerView控件
    2. 通過findViewById找到控件
    3. 準備好數據
    4. 設置布局管理器
    5. 創建Adapter
    6. 設置Adapter
    7. 數據就可以實現出來
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ItemBean> data;
    private RecyclerViewBaseAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //找到控件
        recyclerView = findViewById( R.id.recylcer_view );
        swipeRefreshLayout = this.findViewById( R.id.refresh_layout );

        //準備數據
        /*
          準備數據,一般來說數據經由網路獲取,這裡面只是演示
          在實際開發中也要模擬數據,後台還沒準備好時
        */

        initData();

        //設置默認的顯示樣式為ListView的效果
        showList( true, false );

        //處理下拉刷新
        handlerDownPullUpdate();

    }

    private void handlerDownPullUpdate() {
        swipeRefreshLayout.setColorSchemeResources( R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent );
        swipeRefreshLayout.setEnabled( true );
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //這裡面去執行刷新數據的操作
                    /*
                    當我們去在頂部,下拉的時候,這個方法就會被觸發
                    但是這個方法是MainThread,不可以執行耗時操作
                    一般來說我們請求數據再開一個線程去獲取
                    //這裡面演示的話,直接添加一條數據
                    */
                //添加數據
                ItemBean redata = new ItemBean();
                redata.title = "new data....";
                redata.icon = R.mipmap.pic_10;
                data.add( 0, redata );
                //更新UI
                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        //這裡要做兩件事,1.刷新停止, 2. 更新列表

                        //更新列表
                        adapter.notifyDataSetChanged();

                        //刷新停止
                        swipeRefreshLayout.setRefreshing( false );

                    }
                }, 3000 );

            }
        } );


    }

    private void initListener() {
        adapter.setOnItemClickListener( new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //這裡處理點擊事件
                Toast.makeText( MainActivity.this, "第" + position + "條", Toast.LENGTH_SHORT ).show();
            }
        } );


        //這裡處理上拉加載更多
        if (adapter instanceof ListAdapter) {
            ((ListAdapter) adapter).setOnRefreshListener( new ListAdapter.OnRefreshListener() {
                @Override
                public void OnUpPullRefresh(final ListAdapter.LoaderMoreHolder loaderMoreHolder) {
                    //這裡面去加載數據,同樣需要在子線程中,這裡做演示

                    new Handler().postDelayed( new Runnable() {
                        @Override
                        public void run() {

                            Random random = new Random();
                            if (random.nextInt() % 2 == 0) {

                                ItemBean UpPulldata = new ItemBean();
                                UpPulldata.title = "new data....";
                                UpPulldata.icon = R.mipmap.pic_11;
                                data.add( UpPulldata );
                                adapter.notifyDataSetChanged();
                                loaderMoreHolder.update( loaderMoreHolder.LOADER_STATE_NORMAL );
                            } else {
                                loaderMoreHolder.update( loaderMoreHolder.LOADER_STATE_RELOAD );
                            }


                        }
                    }, 3000 );
                }
            } );
        }
    }

    /*
        這個方法用於初始化,模擬數據
     */
    private void initData() {
        //List<DataBean> ----> Adapter ----> setAdapter ----> 顯示數據
        //創建集合

        data = new ArrayList<>();

        //創建模擬數據
        for (int i = 0; i < Datas.icons.length  ; i++) {
            //創建對象
            ItemBean itemBean = new ItemBean();
            itemBean.icon = Datas.icons[i];
            itemBean.title = "第" + i + "條";

            //添加到集合裡頭
            data.add( itemBean );
        }

    }

    //創建menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    //menu點擊事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {

            //List
            case R.id.list_view_vertical_stander:
                showList( true, false );
                break;

            case R.id.list_view_vertical_reverse:
                showList( true, true );
                break;

            case R.id.list_view_horizontal_stander:
                showList( false, false );
                break;

            case R.id.list_view_horizontal_reverse:
                showList( false, true );
                break;

            //Grid
            case R.id.grid_view_vertical_stander:
                showGrid( true, false );
                break;

            case R.id.grid_view_vertical_reverse:
                showGrid( true, true );
                break;

            case R.id.grid_view_horizontal_stander:
                showGrid( false, false );
                break;

            case R.id.grid_view_horizontal_reverse:
                showGrid( false, true );
                break;

            //Stagger
            case R.id.stagger_view_vertical_stander:
                showStagger( true, false );
                break;

            case R.id.stagger_view_vertical_reverse:
                showStagger( true, true );
                break;

            case R.id.stagger_view_horizontal_stander:
                showStagger( false, false );
                break;

            case R.id.stagger_view_horizontal_reverse:
                showStagger( false, true );
                break;

            //多種條目類型
            case R.id.multi_type:

                //跳到新的MainActivity去
                Intent intent = new Intent( this, MultiActivity.class );
                startActivity( intent );
                break;
        }

        return super.onOptionsItemSelected( item );
    }

    //這個方法用於顯示Stagger一樣的效果
    private void showStagger(boolean isVertical, boolean isReverse) {

        //創建布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                ( 3, isVertical ? StaggeredGridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL );

        //設置布局管理器的方向
        staggeredGridLayoutManager.setReverseLayout( isReverse );

        //設置布局管理器
        recyclerView.setLayoutManager( staggeredGridLayoutManager );

        //創建Adapter
        adapter = new StaggerAdapter( data );

        //設置Adapter
        recyclerView.setAdapter( adapter );

        //初始化事件
        initListener();
    }


    /*
    這個方法用於顯示GridView一樣的效果
     */
    private void showGrid(boolean isVertical, boolean isReverse) {

        //創建布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 4 );

        //設置水平還垂直
        gridLayoutManager.setOrientation( isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL );

        //設置反向還正向
        gridLayoutManager.setReverseLayout( isReverse );

        //設置布局管理器
        recyclerView.setLayoutManager( gridLayoutManager );

        //創建Adapter
        adapter = new GridViewAdapter( data );

        //設置Adapter
        recyclerView.setAdapter( adapter );

        //初始化事件
        initListener();
    }


    /*
        這個方法用於顯示ListView一樣的效果
     */
    private void showList(boolean isVertical, boolean isReverse) {

        //RecyclerView需要設置樣式,其實就是設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );

        //設置布局管理器來控制
        //設置水平還是垂直
        linearLayoutManager.setOrientation( isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL );

        //設置反向還是正向
        linearLayoutManager.setReverseLayout( isReverse );


        recyclerView.setLayoutManager( linearLayoutManager );

        //創建調便器
        adapter = new ListAdapter( data );

        //設置到RecyclerView裡頭
        recyclerView.setAdapter( adapter );

        //初始化事件
        initListener();
    }
}
