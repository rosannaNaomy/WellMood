package com.portillo.naomyportillo.wellmood.logfragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder;

import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_DATE;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_CAUSE;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_DESCRIPTION;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_MOOD;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_THOUGHT;


public class SingleDailyLogDisplayFragment extends Fragment {


    private static Bundle args;
    public static final String DAY_LOG_ID = "ID";

    private TextView dayLogDateTextview;
    private TextView dayLogThoughsTextView;
    private Button updateLogButton;

    private String dayLogDate;
    private String dayLogThoughts;
    private String dayLogMood;
    private String dayLogCause;
    private String dayLogDescription;
    private int id;
    private DayLogDatabaseHelper dayLogDatabaseHelper;
    OnFragmentInteractionListener listener;


    public SingleDailyLogDisplayFragment() {
    }

    public static SingleDailyLogDisplayFragment newInstance(Bundle bundle) {
        SingleDailyLogDisplayFragment fragment = new SingleDailyLogDisplayFragment();
        args = bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());

        if (getArguments() != null) {
            dayLogDate = getArguments().getString(DAY_LOG_DATE);
            dayLogDescription = getArguments().getString(DAY_LOG_DESCRIPTION);
            dayLogThoughts = getArguments().getString(DAY_LOG_THOUGHT);
            dayLogMood = getArguments().getString(DAY_LOG_MOOD);
            dayLogCause = getArguments().getString(DAY_LOG_CAUSE);
        }

        Log.d(".SingleDailyLogDisplay", "nummy - thoughts: " + dayLogThoughts);
        Log.d(".SingleDailyLogDisplay", "nummy - cause: " + dayLogCause);
        Log.d(".SingleDailyLogDisplay", "nummy - date: " + dayLogDate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_log_retrieval, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dayLogDateTextview = view.findViewById(R.id.date_textview);
        dayLogThoughsTextView = view.findViewById(R.id.daylogdata_textview);
        updateLogButton = view.findViewById(R.id.edit_cause_button);

        dayLogThoughsTextView.setText(dayLogThoughts);
        dayLogDateTextview.setText(dayLogDate);

        updateLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = dayLogDatabaseHelper.getItemID(dayLogDate);


                id = data.getInt(0);
                args.putInt(DAY_LOG_ID,id);
                setArguments(args);

                EditLogFragment editLogFragment = EditLogFragment.newInstance(args);
                onButtonPressed(editLogFragment);
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
