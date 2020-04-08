package com.example.myapplicationvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private TextView msg;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        msg = findViewById( R.id.msg );
        queue = Volley.newRequestQueue( this );
        img = findViewById( R.id.img );
    }

    public void test1(View view) {

        StringRequest stringRequest = new StringRequest( Request.Method.GET, "http://192.168.9.79:9102/get/text", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("brad",response);
            }
        },null ){};

        queue.add(stringRequest);
    }

    public void test2(View view) {
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "http://192.168.9.79:9102/get/text", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJSON( response );
            }
        },null){};

        queue.add( stringRequest );
    }


    public void test3(View view) {
        StringRequest stringRequest = new StringRequest( Request.Method.POST, "http://192.168.9.79:9102/get/text", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.v( "brad",response );
                parseJSON( response );
            }
        }, null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>(  );
                param.put( "account","aaaa");
                param.put( "passwd","1234" );

                return param;
            }
        };
        queue.add( stringRequest );
    }

    public void test4(View view){
        String imgurl = "https://www.bradchao.com/wp/wp-content/uploads/2019/09/%E5%B0%81%E9%9D%A21129-1-01-757x1024.jpg";

        ImageRequest imageRequest = new ImageRequest( imgurl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                img.setImageBitmap( response );
            }
        },0,0,ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
        queue.add( imageRequest );
    }

    public void test5(View view){
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "https://www.google.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            parseJSON( response );
            Log.v( "brad",response );
            }
        },null){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String myAuth ="admin:admin";
                String myAuthBase64 = Base64.encodeToString( myAuth.getBytes(),Base64.NO_WRAP );
                HashMap<String,String> ret = new HashMap<>(  );
                ret.put( "Authorization", "Basic" + myAuthBase64 );

                return ret;
            }
        };
        queue.add( stringRequest );

    }

    public void test6(View view){
        StringRequest stringRequest = new StringRequest( Request.Method.GET, "https://www.google.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            parseJSON( response );
            Log.v( "brad",response );
            }
        },null){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = "1234";
                HashMap<String,String> ret = new HashMap<>(  );
                ret.put( "Authorization","Bearer "+token );
                return ret;
            }
        };
        queue.add( stringRequest );

    }


    public void parseJSON(String json){
        try{
            JSONObject root = new JSONObject(json);
            String result = root.getString( "result" );
            if (result.equals( "success" )){
                msg.setText( "OK" );
            }else{
                msg.setText( "XX" );
            }
        }catch (Exception e){
            Log.v( "brad",e.toString() );
        }

    }

}
