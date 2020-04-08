package com.example.myapplicationretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    RecyclerView resultList;
    ResultListAdapter resultListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();

    }

    private void initView() {

        resultList = findViewById( R.id.result_list );
        resultList.setLayoutManager( new LinearLayoutManager( this ) );
        resultListAdapter = new ResultListAdapter();
        resultList.setAdapter( resultListAdapter );

    }

    public void getRequest(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://192.168.9.79:9102" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        API api = retrofit.create( API.class );
        Call<JsonResult> task = api.getJSON();
        task.enqueue( new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {

                Log.v("brad","Response ---> " + response.code());
                JsonResult result = response.body();
                updateList( result );

            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.v( "brad","Fail ----> "+ t.toString() );
            }
        } );
    }
    private void updateList(JsonResult jsonResult) {
        resultListAdapter.setData(jsonResult);
    }
}
