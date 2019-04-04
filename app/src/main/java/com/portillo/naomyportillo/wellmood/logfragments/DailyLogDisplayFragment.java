package com.portillo.naomyportillo.wellmood.logfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portillo.naomyportillo.wellmood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyLogDisplayFragment extends Fragment {


    public DailyLogDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_log_display, container, false);
    }

}
