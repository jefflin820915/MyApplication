package com.example.myapplication06;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class f2Fragment extends Fragment {

    private View mainView;
    private MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        mainActivity = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mainView = inflater.inflate( R.layout.fragment_f2, container, false );
        Button f2btn = mainView.findViewById(R.id.f2btn);
        f2btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                test1();
            }
        } );
        return mainView;

    }

    private void test1(){
        Log.v("brad","click");
        mainActivity.getf1().setMsg( "Lottery: " + (int)(Math.random()*49+1) );
    }
}
