package com.example.myapplicationbankservicesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.BankBossActivity;
import com.BankService;
import com.BankWorkerActivity;
import com.NormalUserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 普通用戶按鈕被點擊
     *
     * @param view
     */
    public void normalUserClick(View view) {

        startActivity(new Intent(this, NormalUserActivity.class));

    }


    /**
     * 銀行工作人員按鈕被點擊
     *w
     * @param view
     */
    public void bankorkerClick(View view) {

        startActivity(new Intent(this, BankWorkerActivity.class));
    }

    /**
     * @param view 老闆按鈕被點擊
     */
    public void bankBossClick(View view) {


        startActivity(new Intent(this, BankBossActivity.class));
    }
}
