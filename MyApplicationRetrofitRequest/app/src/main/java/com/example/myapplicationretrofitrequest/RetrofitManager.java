package com.example.myapplicationretrofitrequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl( "http://192.168.9.79:9102" )
            .addConverterFactory( GsonConverterFactory.create() )
            .build();

    public static Retrofit getRetrofit() {

        return retrofit;
    }
}
