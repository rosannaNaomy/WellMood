package com.portillo.naomyportillo.wellmood.logfragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.DayThoughtFragment;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;
import com.portillo.naomyportillo.wellmood.recyclerview.CauseAdapter;
import com.portillo.naomyportillo.wellmood.recyclerview.DayLogAdapter;

import java.util.List;


public class DisplayCauseFragment extends Fragment {

    private static Bundle args;
    DayLogDatabaseHelper dayLogDatabaseHelper;
    public static final String MOOD = "mood";
    private String mood;
    private TextView moodTextview;
    RecyclerView recyclerView;
    private CauseAdapter causeAdapter;
    private List<DayLogModel> logs;

    public DisplayCauseFragment() {
    }

    public static DisplayCauseFragment newInstance(String mood) {
        DisplayCauseFragment fragment = new DisplayCauseFragment();
        args = new Bundle();
        args.putString(MOOD, mood);
        Log.d(".DisplayCauseFragment", "nummy - mood: " + mood);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mood = getArguments().getString(MOOD);
        }
        Log.d(".DisplayCauseFragment", "nummy onCreate - mood: " + mood);

        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());
        logs = dayLogDatabaseHelper.getLogList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_cause, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_container_display_cause);

        causeAdapter = new CauseAdapter(logs, mood);
        recyclerView.setAdapter(causeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        moodTextview = view.findViewById(R.id.mood_Textview);
        moodTextview.setText(mood);
    }


}
