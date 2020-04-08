package com.example.myapplicationretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @GET("/get/text")
    Call<JsonResult> getJSON();

}
