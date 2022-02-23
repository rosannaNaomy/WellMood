package com.portillo.naomyportillo.wellmood.logfragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;
import com.portillo.naomyportillo.wellmood.recyclerview.DayLogAdapter;

import java.util.List;

public class AllDailyLogsDisplayFragment extends Fragment {

    List<DayLogModel> logs;
    RecyclerView recyclerView;
    OnFragmentInteractionListener listener;
    DayLogAdapter dayLogAdapter;
    Button toReoccurringFragment;

    public AllDailyLogsDisplayFragment() {
    }

    public static AllDailyLogsDisplayFragment newInstance() {
        return new AllDailyLogsDisplayFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DayLogDatabaseHelper dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());
        logs = dayLogDatabaseHelper.getLogList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_log_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toReoccurringFragment = view.findViewById(R.id.to_reoccurringbehavior_button);
        recyclerView = view.findViewById(R.id.recycler_view_container);

        dayLogAdapter = new DayLogAdapter(listener, logs);
        recyclerView.setAdapter(dayLogAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        toReoccurringFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReoccurringBehaviorFragment reoccurringBehaviorFragment = ReoccurringBehaviorFragment.newInstance();
                onButtonPressed(reoccurringBehaviorFragment);
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

    @Override
    public void onResume() {
        super.onResume();
        Log.d(AllDailyLogsDisplayFragment.class.getName(), "onResume: " + "updating log list");
        DayLogDatabaseHelper dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());
        logs = dayLogDatabaseHelper.getLogList();
        dayLogAdapter.updateLogs(logs);

    }
}
