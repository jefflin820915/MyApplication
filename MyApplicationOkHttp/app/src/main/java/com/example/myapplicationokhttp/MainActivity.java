package com.example.myapplicationokhttp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Pair;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.9.79:9102";
    public static final  int perMisson_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        int permission = checkCallingOrSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE );
        if (permission != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, perMisson_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        //;
    }

    public void getquest(View view) {

        //要有客戶端,就類似我們瀏覽器
        OkHttpClient okHttpClient = new OkHttpClient.Builder( )
                .connectTimeout( 10000, TimeUnit.MILLISECONDS )
                .build();

        //創建請求內容
        final Request request = new Request.Builder(  )
                .get()
                .url( URL + "/get/text")
                .build();

        //用Client創建請求任務
        Call newCall = okHttpClient.newCall( request );

        //不同步請求
        newCall.enqueue( new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v( "brad","Fail....-> " + e.toString() );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.v( "brad", "code--> " + code );
                if (code == HttpURLConnection.HTTP_OK) {
                    ResponseBody body = response.body();
                    Log.v( "brad","body--->"+body.string() );

                }
            }
        } );

    }

    public void postComment(View view) {

        //要有客戶端,就類似我們瀏覽器
        OkHttpClient okHttpClient = new OkHttpClient.Builder(  )
                .connectTimeout( 10000,TimeUnit.MILLISECONDS )
                .build();



        //要提交的內容
        CommentItem commentItem = new CommentItem( "12321312", "mycomment" );
        Gson gson = new Gson();
        String jsonStr = gson.toJson( commentItem );
        MediaType mediaType = MediaType.parse( "application/json" );

        //請求內容
        RequestBody requestBody = RequestBody.create(jsonStr,mediaType);

        Request request = new Request.Builder(  )
                .post(requestBody)
                .url(URL + "/post/comment")
                .build();

        Call newCall = okHttpClient.newCall( request );
        newCall.enqueue( new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v("brad","Fail----> "+ e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                int code = response.code();
                Log.v( "brad", "code-----> " + code );

                if (code == HttpURLConnection.HTTP_OK) {

                    ResponseBody body = response.body();

                    if(body != null){
                        Log.v("brad","body-----> " + body.string());
                    }


                }

            }
        } );

    }

    public void uploadFile(View view) {

        //創建客戶端
        OkHttpClient okHttpClient = new OkHttpClient.Builder(  )
                .connectTimeout( 50000,TimeUnit.MILLISECONDS )
                .build();

        File file = new File( "/sdcard/Pictures/1574754952940.jpg");
        MediaType fileType = MediaType.parse( "image/png" );

        RequestBody fileBody = RequestBody.create( file,fileType );

        RequestBody requestBody = new MultipartBody.Builder(  )
                .addFormDataPart( "file",file.getName(),fileBody)
                .build();

        //請求內容
        final Request request = new Request.Builder(  )
                .url( URL + "/file/upload" )
                .post( requestBody )
                .build();


        Call newcall = okHttpClient.newCall( request );
        newcall.enqueue( new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v( "brad","Fail----> " + e.toString() );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                int code = response.code();
                Log.v( "brad","code----> " + code );
                if (code == HttpURLConnection.HTTP_OK){
                    ResponseBody body = response.body();
                    if (body != null){
                        String bodyString = body.string();
                        Log.v( "brad","body----> " + bodyString );
                    }
                }

            }
        } );

    }

    public void uploadFiles(View view) {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder(  )
                .connectTimeout( 10000,TimeUnit.MILLISECONDS)
                .build();

        File file1 = new File( "/sdcard/Pictures/1574754952940.jpg" );
        File file2 = new File( "/sdcard/Pictures/7849306_151617247181_2.jpg" );
        File file3 = new File("/sdcard/Pictures/images.jpg"  );
        File file4 = new File( "/sdcard/Pictures/mT19-hvscktf4828704.jpg" );

        MediaType fileType = MediaType.parse( "image/png" );

        RequestBody fileTypeBody1 = RequestBody.create( file1, fileType );
        RequestBody fileTypeBody2 = RequestBody.create( file2, fileType );
        RequestBody fileTypeBody3 = RequestBody.create( file3, fileType );
        RequestBody fileTypeBody4 = RequestBody.create( file4, fileType );


        RequestBody requestBody = new MultipartBody.Builder(  )
                .addFormDataPart( "files",file1.getName(),fileTypeBody1  )
                .addFormDataPart("files",file2.getName(),fileTypeBody2  )
                .addFormDataPart("files",file3.getName(),fileTypeBody3  )
                .addFormDataPart("files",file4.getName(),fileTypeBody4  )
                .build();



             Request request = new Request.Builder(  )
                .url(URL + "/files/upload")
                .post( requestBody )
                .build();

        Call newCall = okHttpClient.newCall( request );
        newCall.enqueue( new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.v( "brad", "Fail------> " + e.toString() );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                int code = response.code();
                Log.v( "brad","code----> " + code );
                if (code == HttpURLConnection.HTTP_OK){
                    ResponseBody body = response.body();
                    if (body != null){
                        String bodyString = body.string();
                        Log.v("brad",bodyString);
                    }
                }
            }
        } );
    }

    public void downloadFiles(View view) {

        //
        final OkHttpClient okHttpClient = new OkHttpClient.Builder( )
                .connectTimeout( 10000,TimeUnit.MILLISECONDS )
                .build();


        Request request = new Request.Builder(  )
                .get()
                .url( URL + "/download/15" )
                .build();

        final Call newCall = okHttpClient.newCall( request );

        new Thread( new Runnable() {
            @Override
            public void run() {
                FileOutputStream fileOutputStream = null;
                InputStream inputStream = null;


                try {

                    Response execute = newCall.execute();
                    Headers headers = execute.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        String key = headers.name( i );
                        String value = headers.value( i );

                        Log.d( "brad",key + " -----> " + value );
                    }

                    String contentType = headers.get( "Content-disposition" );
                    String fileName = contentType.replace( "attachment; filename=", "" );
                    File outputFile = new File( MainActivity.this.getExternalFilesDir( Environment.DIRECTORY_PICTURES )+ File.separator + fileName);
                    if (!outputFile.getParentFile().exists()){
                        outputFile.mkdirs();
                    }

                    if (!outputFile.exists()){
                        outputFile.createNewFile();
                    }

                    fileOutputStream = new FileOutputStream( outputFile );
                    if (execute.body() != null) {

                        inputStream = execute.body().byteStream();
                        byte[] buffer  = new byte[1024];
                        int length;

                        while ((length = inputStream.read(buffer,0,buffer.length)) != -1){
                            fileOutputStream.write( buffer,0,length );

                        }
                            fileOutputStream.flush();
                    }

                } catch (IOException e) {

                    e.printStackTrace();

                }finally {

                    IOUtils.ioClose( fileOutputStream );
                    IOUtils.ioClose( inputStream );
                }
            }
        } ).start();
    }
}
