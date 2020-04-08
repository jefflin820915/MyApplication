package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class page3 extends AppCompatActivity {

    // 宣告
    private static final int REQUEST_CALL_PHONE = 111;
    private Button button2, button17, button18, button19, button20, button21, button22, button6, button8, button9, button10, button11, button15, button16, button24;
    private Spinner spinner, spinner2;
    private TextView textView3;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_page3 );

        // 連結
        button2 = (Button) findViewById( R.id.button2 ); // 取得元件button2的id屬姓 給 button2
        button2.setOnClickListener( new View.OnClickListener() { //建立事件, 點擊button2會發生甚麼事情
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(); // 初始化Intent物件, 建一個意圖名為intent
                intent.setClass( page3.this, page2.class ); // 使用setClass方法來設定你要前往的Activity
                startActivity( intent );// 啟動Activity intent
            }
        } );

        //連結
        button6 = (Button) findViewById( R.id.button6 );
        textView3 = (TextView) findViewById( R.id.textView3 );
        spinner = (Spinner) findViewById( R.id.spinner );
        spinner2 = (Spinner) findViewById( R.id.spinner2 );

        //建立一個名為adapter, ArrayAdapter將字串陣列的字串"drimk"一個一個塞進去spinner中
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.drink, android.R.layout.simple_spinner_item );
        //建立spinner下拉式選單樣式
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        //設置spinner的 Adapter 給adapter
        spinner.setAdapter( adapter );
        ////建立一個名為adapter2, ArrayAdapter將字串陣列的字串"temp"一個一個塞進去spinner中
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource( this, R.array.temp, android.R.layout.simple_spinner_item );
        //建立spinner下拉式選單樣式
        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        //設置spinner的 Adapter 給adapter2
        spinner2.setAdapter( adapter2 );

        //建立事件, 點擊下拉式選單會發生甚麼事
        spinner.setOnItemSelectedListener( new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long s) {
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );
        {
        }

        button6.setOnClickListener( new View.OnClickListener() { //建立事件, 點擊button6會發生甚麼事
            @Override
            public void onClick(View view) {
                String spDrink = spinner.getSelectedItem().toString(); //取得spinner顯示出的選項SelectedItem物件轉為字串, 起名為spDrink
                String spTemp = spinner2.getSelectedItem().toString(); //取得spinner2顯示出的選項SelectedItem物件轉為字串, 起名為spTemp
                textView3.setText( "飲料: " + spDrink + "   " + "溫度: " + spTemp ); //設置字串在textView中顯示出來
                //做一個字串用Toast方式呈現, 顯示時間為短時間 context: 存活/處在的環境, text: 所要顯示的文字, duration: 要多久時間
                Toast.makeText( page3.this, "飲料: " + spDrink + "   " + "溫度: " + spTemp, Toast.LENGTH_SHORT ).show();
            }
        } );


        button8 = (Button) findViewById( R.id.button8 );   //取得元件button8的id屬姓 給 button8
        button9 = (Button) findViewById( R.id.button9 );   //取得元件button9的id屬姓 給 button9
        button10 = (Button) findViewById( R.id.button10 ); //取得元件button10的id屬姓 給 button10
        button11 = (Button) findViewById( R.id.button11 );
        button15 = (Button) findViewById( R.id.button15 );
        button16 = (Button) findViewById( R.id.button16 );
        button17 = (Button) findViewById( R.id.button17 );
        button18 = (Button) findViewById( R.id.button18 );
        button19 = (Button) findViewById( R.id.button19 );
        button20 = (Button) findViewById( R.id.button20 );
        button21 = (Button) findViewById( R.id.button21 );
        button22 = (Button) findViewById( R.id.button22 );
        button24 = findViewById( R.id.button24 );
        editText = (EditText) findViewById( R.id.editText );
        View.OnClickListener Listener = new View.OnClickListener() { //給一個點擊監聽器建立事件 名為Listener

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                try {
                    int id = view.getId(); //取每個按鈕元件的id屬性
                    switch (id) { //使用switch語法來判斷按按鈕會發生甚麼事
                        case R.id.button8: //當按button8
                            display( "1" ); //顯示出1
                            break; //停止當前程序
                        case R.id.button9: //當按button9
                            display( "2" ); //顯示出2
                            break; //停止當前循環程序
                        case R.id.button10:
                            display( "3" );
                            break;
                        case R.id.button11:
                            display( "4" );
                            break;
                        case R.id.button15:
                            display( "5" );
                            break;
                        case R.id.button16:
                            display( "6" );
                            break;
                        case R.id.button17:
                            display( "0" );
                            break;
                        case R.id.button18:
                            //如果CALL_PHONE權限不允許,就去調用REQUEST_CALL_PHONE請求CALL_PHONE權限
                            if (ContextCompat.checkSelfPermission( page3.this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {

                                ActivityCompat.requestPermissions( page3.this, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE );

                            } else { //允許就執行下列撥打程序
                                String tel = editText.getText().toString(); //將顯示在editText的資料轉成字串, 起名叫tel
                                // 創立一個新對象意圖調用內置的call去抓tel字串來撥打電話, 起名叫 intent2
                                Intent intent2 = new Intent( Intent.ACTION_CALL, Uri.parse( "tel: " + tel ) );
                                startActivity( intent2 ); //啟動Activity intent2
                            }
                            break; //停止當前循環程序
                        case R.id.button19:
                            editText.setText( "" );
                            break;
                        case R.id.button20:
                            display( "7" );
                            break;
                        case R.id.button21:
                            display( "8" );
                            break;
                        case R.id.button22:
                            display( "9" );
                            break;
                        case R.id.button24:
                            Intent intent = new Intent();
                            intent.setClass( page3.this, Main3Activity.class);
                            startActivity( intent);

                            break;
                    }
                } catch (ActivityNotFoundException e) {
                    Toast.makeText( page3.this, "8888888", Toast.LENGTH_SHORT ).show();
                }
            }
        };
        button8.setOnClickListener( Listener ); //將button8放進去名為Listener的事件裡
        button9.setOnClickListener( Listener ); //將button9放進去名為Listener的事件裡
        button10.setOnClickListener( Listener ); //將button10放進去名為Listener的事件裡
        button11.setOnClickListener( Listener ); //將button11放進去名為Listener的事件裡
        button15.setOnClickListener( Listener );
        button16.setOnClickListener( Listener );
        button17.setOnClickListener( Listener );
        button18.setOnClickListener( Listener );
        button19.setOnClickListener( Listener );
        button20.setOnClickListener( Listener );
        button21.setOnClickListener( Listener );
        button22.setOnClickListener( Listener );
        button24.setOnClickListener( Listener );

    }


    public void display(String view) { //建立一個新方法,來調用進去事件裡, 減少寫太多重複程序
        String old = editText.getText().toString(); //將editText顯示出來的文字取出Text物件轉為字串, 名為old
        String newV = old + view; // old+按鍵點擊下去的字串 名為newV
        editText.setText( newV ); // 設置一個文字newV顯示到editText
    }

    //請求權限: 參數1：上下文，2：權限，3：請求碼
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //判斷請求碼
        switch (requestCode) {

            case REQUEST_CALL_PHONE: //case1 call(打電話)
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 如果同意,就撥打
                    Toast.makeText( page3.this, "Read_Call_Phone Granted", Toast.LENGTH_SHORT ).show();//權限同意: 用Toast方式顯示權限同意訊息字串
                } else {
                    Toast.makeText( page3.this, "Read_Call_Phone Denied", Toast.LENGTH_SHORT ).show();//權限不同意: 用Toast方式顯示權限不同意訊息字串
                }
                break; //停止循環當前程序
        }
    }
}