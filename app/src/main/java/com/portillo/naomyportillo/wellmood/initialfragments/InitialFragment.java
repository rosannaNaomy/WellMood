package com.portillo.naomyportillo.wellmood.initialfragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.logfragments.AllDailyLogsDisplayFragment;

public class InitialFragment extends Fragment {

    private Button addLogbtn;
    private Button viewLogsbtn;
    private Button deleteLogsButton;
    private DayLogDatabaseHelper dayLogDatabaseHelper;
    private OnFragmentInteractionListener listener;


    public InitialFragment() {
    }

    public static InitialFragment newInstance() {
        return new InitialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_initial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addLogbtn = view.findViewById(R.id.addLog);
        viewLogsbtn = view.findViewById(R.id.viewLogs);
        deleteLogsButton = view.findViewById(R.id.cleardata_button);

        OnclickBttns();
    }

    private void OnclickBttns() {
        addLogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayDescriptionFragment dayDescriptionFragment = DayDescriptionFragment.newInstance();
                onButtonPressed(dayDescriptionFragment);
            }
        });

        viewLogsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllDailyLogsDisplayFragment allDailyLogsDisplayFragment = AllDailyLogsDisplayFragment.newInstance();
                onButtonPressed(allDailyLogsDisplayFragment);
            }
        });

        deleteLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayLogDatabaseHelper.clearLogList();
            }
        });

    }

    public void onButtonPressed(Fragment fragment) {
        if (listener != null) {
            listener.onFragmentInteraction(fragment);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
