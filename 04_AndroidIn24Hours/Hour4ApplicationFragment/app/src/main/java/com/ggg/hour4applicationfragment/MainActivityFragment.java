package com.ggg.hour4applicationfragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    /**
     * Constructor
     */
    public MainActivityFragment() {
    }


    /**
     * onCreateView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
