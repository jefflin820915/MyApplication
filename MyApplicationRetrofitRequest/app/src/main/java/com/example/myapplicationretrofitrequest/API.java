package com.example.myapplicationretrofitrequest;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface API {

    @GET("/get/text")
    Call<JsonResult> getJson();


    @GET("/get/param")
    Call<JsonResult> getWithParams(@Query("keyword") String keyword,
                                   @Query("page") int page,
                                   @Query("order") String order);

    @GET("/get/param")
    Call<JsonResult> getWithParamsMap(@QueryMap Map<String, Object> params);

    @POST("/post/string")
    Call<PostWithParamsResult> PostWthParams(@Query("string") String Content);

    @POST
    Call<PostWithParamsResult> PostWthUrl(@Url String url);


    @POST("/post/comment")
    Call<PostWithParamsResult> postWithBody(@Body CommentItem commentItem);

    @Multipart
    @POST("/file/upload")
    Call<postFileRequest> postFile(@Part MultipartBody.Part part, @Header( "token")String token);

    @Headers( {"token:213123123123","client:andriod","version:1.0"})
    @Multipart
    @POST("/files/upload")
    Call<postFileRequest> postFiles(@Part List<MultipartBody.Part> parts);

    @Multipart
    @POST("/file/params/upload")
    Call<postFileRequest> postFileWithParams(@Part MultipartBody.Part part, @PartMap Map<String, String> params, @HeaderMap Map<String,String> headers);

    @FormUrlEncoded
    @POST("/login")
    Call<loginResult> doLogin(@Field("userName")String userName,@Field( "password" )String word);

    @FormUrlEncoded
    @POST("/login")
    Call<loginResult> mapdoLogin(@FieldMap Map<String,String> params);

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String url);

}
