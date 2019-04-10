package com.portillo.naomyportillo.wellmood.logfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portillo.naomyportillo.wellmood.R;


public class EditLogFragment extends Fragment {


    public EditLogFragment() {
        // Required empty public constructor
    }


    public static EditLogFragment newInstance(String param1, String param2) {
        EditLogFragment fragment = new EditLogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_log, container, false);
    }

}
