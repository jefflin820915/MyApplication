package com.example.myapplicationretrofitrequest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Part;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int request_Code = 1;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        int permission = checkCallingOrSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE );
        int writeStorage = checkCallingOrSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE );
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, request_Code );
            if (writeStorage != PackageManager.PERMISSION_GRANTED){
                requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},request_Code );
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if (requestCode == request_Code) {

            //TODO:處理權限獲取結束

        }
    }

    public void getWithParams(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();
        API api = retrofit.create( API.class );
        Call<JsonResult> task = api.getWithParams( "123asdzxc", 10, "1" );
        task.enqueue( new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    JsonResult body = response.body();
                    Log.v( "brad", "response ---> " + body );
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.v( "brad", "Fail ----> " + t.toString() );
            }
        } );

    }

    public void getWithPramsMap(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();
        API api = retrofit.create( API.class );
        Map<String, Object> params = new HashMap<>();
        params.put( "keyword", "123asdzxc" );
        params.put( "page", 10 );
        params.put( "order", "0" );


        Call<JsonResult> task = api.getWithParamsMap( params );
        task.enqueue( new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    JsonResult body = response.body();
                    Log.v( "brad", "body ----> " + body );

                }

            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.v( " brad", "fail ---> " + t.toString() );
            }
        } );

    }

    public void postWithparams(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();
        API api = retrofit.create( API.class );
        Call<PostWithParamsResult> task = api.PostWthParams( "這是我的內容........" );
        task.enqueue( new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {

                    PostWithParamsResult body = response.body();
                    Log.v( "brad", "body ---> " + body );

                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );


    }

    public void postWithUrl(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();
        API api = retrofit.create( API.class );
        Call<PostWithParamsResult> task = api.PostWthUrl( "/post/string?string=内容测试内容" );
        task.enqueue( new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );

                if (code == HttpURLConnection.HTTP_OK) {
                    PostWithParamsResult body = response.body();
                    Log.v( "brad", "body ---> " + body );

                }
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );

    }

    public void postWithBody(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();
        API api = retrofit.create( API.class );
        CommentItem commentItem = new CommentItem( "12345", "55555344121" );
        Call<PostWithParamsResult> task = api.postWithBody( commentItem );
        task.enqueue( new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    PostWithParamsResult body = response.body();
                    Log.v( "brad", "body ---> " + body );

                }

            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );

    }

    public void postFile(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();
        API api = retrofit.create( API.class );
        MultipartBody.Part part = creatPartByPathAndKey( "/sdcard/Pictures/e99e6b5e-ca6c-4c19-87b7-dfd63db6381a_m.jpg", "file" );
        Call<postFileRequest> task = api.postFile( part,"21321321sdjashdiaj" );
        task.enqueue( new Callback<postFileRequest>() {
            @Override
            public void onResponse(Call<postFileRequest> call, Response<postFileRequest> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    postFileRequest body = response.body();
                    Log.v( "brad", "body --->" + body );

                }

            }

            @Override
            public void onFailure(Call<postFileRequest> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );


    }

    private MultipartBody.Part creatPartByPathAndKey(String path, String key) {
        File file = new File( path );
        RequestBody body = RequestBody.create( MediaType.parse( "image/jpeg" ), file );
        MultipartBody.Part part = MultipartBody.Part.createFormData( key, file.getName(), body );
        return part;
    }


    public void postFiles(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://192.168.9.79:9102" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        API api = retrofit.create( API.class );
        List<MultipartBody.Part> parts = new ArrayList<>();

        MultipartBody.Part partOne = creatPartByPathAndKey( "/sdcard/Pictures/1574754952940.jpg", "files" );
        MultipartBody.Part partTwo = creatPartByPathAndKey( "/sdcard/Pictures/7849306_151617247181_2.jpg", "files" );
        MultipartBody.Part partThree = creatPartByPathAndKey( "/sdcard/Pictures/images.jpg", "files" );
        MultipartBody.Part partFour = creatPartByPathAndKey( "/sdcard/Pictures/mT19-hvscktf4828704.jpg", "files" );
        parts.add( partOne );
        parts.add( partTwo );
        parts.add( partThree );
        parts.add( partFour );


        Call<postFileRequest> task = api.postFiles( parts );
        task.enqueue( new Callback<postFileRequest>() {
            @Override
            public void onResponse(Call<postFileRequest> call, Response<postFileRequest> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    postFileRequest body = response.body();
                    Log.v( "brad", "body ---> " + body );
                }
            }

            @Override
            public void onFailure(Call<postFileRequest> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );


    }

    public void postFileWithParams(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://192.168.9.79:9102" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        MultipartBody.Part part = creatPartByPathAndKey( "/sdcard/Pictures/213212.jpg", "file" );

        Map<String, String> params = new HashMap<>();
        params.put( "description", "123213123" );
        params.put( "isFree", "true" );

        API api = retrofit.create( API.class );
        Map<String,String> headers = new HashMap<>(  );
        headers.put( "token","312eksadaodk" );
        headers.put( "version","2.0" );
        headers.put( "client","iphone19" );
        Call<postFileRequest> task = api.postFileWithParams( part, params,headers );
        task.enqueue( new Callback<postFileRequest>() {
            @Override
            public void onResponse(Call<postFileRequest> call, Response<postFileRequest> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    postFileRequest body = response.body();
                    Log.v( "brad", "body ---> " + body );

                }
            }

            @Override
            public void onFailure(Call<postFileRequest> call, Throwable t) {
                Log.v( "brad", "fail ----> " + t.toString() );
            }
        } );

    }

    public void doLogin(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://192.168.9.79:9102" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        API api = retrofit.create( API.class );

        Call<loginResult> task = api.doLogin( "巨槌瑞斯", "123456" );
        task.enqueue( new Callback<loginResult>() {
            @Override
            public void onResponse(Call<loginResult> call, Response<loginResult> response) {
                int code = response.code();
                Log.v( "brad", "code --->" + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    loginResult body = response.body();
                    Log.v( "brad", "body --->" + body );
                }
            }

            @Override
            public void onFailure(Call<loginResult> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );

    }

    public void mapdoLogin(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://192.168.9.79:9102" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        API api = retrofit.create( API.class );

        Map<String, String> loginfo = new HashMap<>();
        loginfo.put( "userName", "冰鳥" );
        loginfo.put( "password", "123456" );

        Call<loginResult> task = api.mapdoLogin( loginfo );
        task.enqueue( new Callback<loginResult>() {
            @Override
            public void onResponse(Call<loginResult> call, Response<loginResult> response) {
                int code = response.code();
                Log.v( "brad", "code ---> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    loginResult body = response.body();
                    Log.v( "brad", "body --->" + body );

                }
            }

            @Override
            public void onFailure(Call<loginResult> call, Throwable t) {
                Log.v( "brad", "fail ---> " + t.toString() );
            }
        } );
    }

    public void downloadFile(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://192.168.9.79:9102" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        String url = "/download/8";
        API api = retrofit.create( API.class );
        Call<ResponseBody> task = api.downloadFile(url);
        task.enqueue( new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                Log.v( "brad","code ---> " +code );
                if (code == HttpURLConnection.HTTP_OK){
                    Log.v( "brad","thread name ---> " + Thread.currentThread().getName() );

                    //文件名稱---header裡
                    Headers headers = response.headers();
                    String fileNameHeaders = headers.get( "Content-disposition" );
                    String fileName = "reyyyshi.png";
                    if (fileNameHeaders != null){
                        fileName = fileNameHeaders.replace( "attachment; filename=","" );
                        Log.v( "brad","filename ---> " + fileName );
                    }

                   // for (int i = 0; i < headers.size(); i++) {
                   //     String value = headers.value( i );
                   //     String key = headers.name( i );
                   //     Log.v("brad",key + " ---> " +value);
                    // }

                    //寫文件,但這裡是UI線程,不能寫
                    writeStringDisk(response,fileName);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("brad","fail ---> " + t.toString());
            }
        } );


    }

    private void writeStringDisk(Response<ResponseBody> response, String fileName) {
        new Thread( new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = response.body().byteStream();

                //過時方法
                //File exPath = Environment.getExternalStorageDirectory(); //storage/emulated/0
                //Log.v( "brad","exPath ---> " +exPath );

                //File outFile = new File( "" );

               // File path = getExternalFilesDir( Environment.DIRECTORY_PICTURES);
               // Log.v( "brad","path ---> " +path );

                String baseFile = File.separator + "mnt" + File.separator + "sdcard" + File.separator
                        + "Android" + File.separator + "data" + File.separator + "com.example.myapplicationretrofitrequest"
                        + File.separator + "files" + File.separator + "Pictures";
                Log.v( "brad","baseFile ---> " + baseFile );

                File outFile = new File(baseFile,fileName);
                Log.v( "brad","outFile ---> "+ outFile );
                try {
                    FileOutputStream fos = new FileOutputStream( outFile );
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1 ){
                        fos.write( buffer , 0, len );
                    }
                    fos.close();

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        } ).start();


    }
}
