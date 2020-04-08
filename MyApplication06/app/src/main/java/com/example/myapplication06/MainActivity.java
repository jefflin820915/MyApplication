package com.example.myapplication06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private f1Fragment f1;
    private f2Fragment f2;
    private f3Fragment f3;
    private f4Fragment f4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        fm = getSupportFragmentManager();

        f1 = new f1Fragment();
        f2 = new f2Fragment();
        f3 = new f3Fragment();
        f4 = new f4Fragment();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container,f1);
        transaction.commit();

    }

    public void setf1(View view) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace( R.id.container, f1);
        transaction.commit();

    }

    public f1Fragment getf1() {
        return f1;
    }

    public void setf2(View view) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace( R.id.container, f2 );
        transaction.commit();
    }

    public void setf3(View view) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace( R.id.container, f3 );
        transaction.commit();
    }

    public void setf4(View view) {

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace( R.id.container, f4 );
        transaction.commit();

    }
}
