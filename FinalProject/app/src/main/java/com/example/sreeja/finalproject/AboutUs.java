package com.example.sreeja.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sreeja on 4/28/2016.
 */
public class AboutUs extends Fragment {

    private View view;

    public static AboutUs newInstance() {
        AboutUs fragment = new AboutUs();
        return fragment;

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.aboutus, container, false);

        return view;
    }


}
