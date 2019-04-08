package com.portillo.naomyportillo.wellmood.logfragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.FeelFragment;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;
import com.portillo.naomyportillo.wellmood.recyclerview.DayLogAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllDailyLogsDisplayFragment extends Fragment {

    List<DayLogModel> logs;
    RecyclerView recyclerView;

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

        recyclerView = view.findViewById(R.id.recycler_view_container);

        recyclerView.setAdapter(new DayLogAdapter(logs));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
