package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class page2 extends AppCompatActivity {
    //宣告
    private Button button, button12, button14, button7;
    private TextView textView6;
    private EditText editText2, editText3, editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_page2 );

        //連結
        button = (Button) findViewById( R.id.button ); //找button元件的屬性id給button
        button.setOnClickListener( new View.OnClickListener() {  //用Onclick監聽器建立事件,點擊button會發生甚麼

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(); //初始化Intent, 建立一個意圖 名為intent
                intent.setClass( page2.this, MainActivity.class ); // 使用setClass方法來設定你要前往的類
                startActivity( intent ); //啟動Activity intent
            }
        } );

        //連結
        button14 = (Button) findViewById( R.id.button14 );
        button14.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(); //初始化Intent, 建立一個意圖 名為intent
                intent.setClass( page2.this, page3.class ); //使用setClass方法來設定你要前往的類
                startActivity( intent ); //啟動Activity intent
            }
        } );

        //連結
        editText2 = (EditText) findViewById( R.id.editText2 );
        editText3 = (EditText) findViewById( R.id.editText3 );
        textView6 = (TextView) findViewById( R.id.textView6 );
        button12 = (Button) findViewById( R.id.button12 );
        editText5 = (EditText) findViewById( R.id.editText5 );

        button12.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try { // 使用try....catch語句來解決發生的錯誤
                    String a = editText2.getText().toString(); //將editText2顯示的文字取出Text物件,轉成字串 名為a
                    String b = editText3.getText().toString(); //將editText3顯示的文字取出Text物件,轉成字串 名為b
                    String c = editText5.getText().toString(); //將editText5顯示的文字取出Text物件,轉成字串 名為c
                    int ai = Integer.parseInt( a ); //將a字串轉為int類型 名為ai
                    int bi = Integer.parseInt( b ); //將b字串轉為int類型 名為bi

                    switch (c) { //使用字串c來判斷
                        case "+": //當c為"+"時
                            textView6.setText( a + "+" + b + "=" + (ai + bi) ); //設置文字給textView6顯示
                            break;
                        case "-":
                            textView6.setText( a + "-" + b + "=" + (ai - bi) );
                            break;
                        case "*":
                            textView6.setText( a + "*" + b + "=" + ai * bi );
                            break;
                        case "/":
                            textView6.setText( a + "/" + b + "=" + ai / bi );
                            break;
                        case "%":
                            textView6.setText( a + "%" + b + "=" + ai % bi );
                            break;

                        default:
                            textView6.setText( "無法確認" ); //如果c字串是以上以外的結果則執行 default
                    }
                } catch (NumberFormatException e) {
                    textView6.setText( "輸入非完整" ); // 使用try....catch語句來解決發生的錯誤
                }
            }

        } );

        button7 = findViewById( R.id.button7 ); //取得元件button7的id屬姓 給 button7
        button7.setOnClickListener( new View.OnClickListener() { //用Onclick監聽器建立事件,點擊button7會發生甚麼
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder( page2.this ); //創建一個新對象AlerDialog, 建立在目前環境一個名為builder的AlerDialog
                builder.setTitle( "My Application" ); //設置一個builder的標題
                builder.setIcon( R.drawable.alerlog ); //設置一個builder的圖示
                builder.setMessage( "Close this App u sure?" ); //設置一個builder內容訊息

                //設置一個builder正面鍵 按鍵文字為"確定" ,建立新對象DialogInterface的監聽器建立點擊事件
                builder.setPositiveButton( "確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                } );

                //設置一個builder負面鍵 按鍵文字為"取消" ,建立新對象DialogInterface的監聽器建立點擊事件
                builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
                builder.show(); //用show()顯示出builder變數
            }
        } );
    }
}
