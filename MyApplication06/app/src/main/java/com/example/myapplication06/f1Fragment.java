package com.example.myapplication06;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class f1Fragment extends Fragment {

    private  View mainView;
    private TextView msg;
    private String showmsg = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate( R.layout.fragment_f1, container, false );
        msg = mainView.findViewById(R.id.f1msg);
        msg.setText( showmsg );

        return mainView;
    }

    public void setMsg(String strMsg){
        Log.v("brad",strMsg);
        showmsg = strMsg;
    }
}
