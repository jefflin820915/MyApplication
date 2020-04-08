package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //宣告
    private Button button3, button4, button5, button25, button27, button29, button30;
    private TextView textView;
    String[] item = {"奶茶", "紅茶", "綠茶"};
    boolean[] checkitem = {false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //連結
        button3 = (Button) findViewById( R.id.button3 ); //找button3元件的屬性id給button3
        textView = (TextView) findViewById( R.id.textView );
        button3.setOnClickListener( new View.OnClickListener() { //用Onclick監聽器建立事件,點擊button會發生甚麼

            @Override
            public void onClick(View view) {
                textView.setText( "Hello World Hello World Hello World Hello World Hello World" ); //設定字串顯示在textView.

            }
        } );
        button4 = (Button) findViewById( R.id.button4 ); //找button4元件的屬性id給button4
        textView = (TextView) findViewById( R.id.textView );
        button4.setOnClickListener( new View.OnClickListener() { //用Onclick監聽器建立事件,點擊button會發生甚麼

            @Override
            public void onClick(View view) {
                textView.setText( "5" + "+" + "5" + "=" + 10 ); //設定字串顯示在textView.
            }
        } );


        button5 = (Button) findViewById( R.id.button5 ); //找button5元件的屬性id給button5
        button5.setOnClickListener( new View.OnClickListener() { //用Onclick監聽器建立事件,點擊button會發生甚麼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(); //初始化Intent, 建立一個意圖 名為intent
                intent.setClass( MainActivity.this, page2.class ); // 使用setClass方法來設定你要前往的類
                startActivity( intent ); //啟動Activity intent
            }
        } );

        //連結
        button25 = (Button) findViewById( R.id.button25 ); //找button25元件的屬性id給button25
        button25.setOnClickListener( new View.OnClickListener() { //用Onclick監聽器建立事件,點擊button會發生甚麼
            @Override
            public void onClick(View v) {
                // 初始化一個AlerDialog, 建立在目前環境一個名為builder的AlerDialog
                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );

                builder.setTitle( "My Application" ); //設置一個Dialog的標題, 為 My Applocation

                builder.setIcon( R.drawable.alerlog ); //設置一個Dialog的icon, 來源在 drawable裡的 alerlog檔案

                builder.setMessage( "Close this App u sure?" ); //設置一個Dialog的訊息, 為 Close this App u sure?

                //設置一個dialog的確定按鈕, 按鈕文字為"確定", 用Onclick監聽器建立事件,點擊button會發生甚麼事
                builder.setPositiveButton( "確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                } );

                builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
                builder.show();
            }
        } );

        button27 = findViewById( R.id.button27 );
        button27.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
                builder.setTitle( "My Application" );
                builder.setIcon( R.drawable.alerlog );
                builder.setItems( item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String a = item[which];
                        Toast.makeText( MainActivity.this, a, Toast.LENGTH_SHORT ).show();
                        textView.setText( a );
                    }
                } );
                builder.show();
            }
        } );

        button29 = findViewById( R.id.button29 );
        button29.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder( MainActivity.this );
                builder2.setTitle( "My Application" );
                builder2.setIcon( R.drawable.alerlog );
                builder2.setSingleChoiceItems( item, 3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String a = item[which];
                        textView.setText( a );
                    }
                } );
                builder2.setPositiveButton( "確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText( MainActivity.this, "完成訂購", Toast.LENGTH_SHORT ).show();
                    }
                } );
                builder2.show();
            }
        } );

        button30 = findViewById( R.id.button30 );
        button30.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder3 = new AlertDialog.Builder( MainActivity.this );
                builder3.setTitle( "My Application" );
                builder3.setIcon( R.drawable.alerlog );
                builder3.setMultiChoiceItems( item, checkitem, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String result = "";
                        for (int i = 0; i < item.length; i++) {
                            if (checkitem[i] == true) {
                                result = result + " " + item[i];
                            }
                        }
                        textView.setText( result );
                    }
                } );

                builder3.setPositiveButton( "確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText( MainActivity.this, "完成訂購", Toast.LENGTH_SHORT ).show();
                    }
                } );
                builder3.show();
            }
        } );


    }
}