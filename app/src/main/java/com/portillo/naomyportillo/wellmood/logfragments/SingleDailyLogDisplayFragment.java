package com.portillo.naomyportillo.wellmood.logfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portillo.naomyportillo.wellmood.R;


public class SingleDailyLogDisplayFragment extends Fragment {


    public SingleDailyLogDisplayFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_log_retrieval, container, false);
    }

}
