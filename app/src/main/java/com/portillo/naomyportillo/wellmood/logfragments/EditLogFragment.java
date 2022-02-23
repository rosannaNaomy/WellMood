package com.portillo.naomyportillo.wellmood.logfragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;

import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_CAUSE;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_DATE;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_DESCRIPTION;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_ID;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_MOOD;
import static com.portillo.naomyportillo.wellmood.recyclerview.DayLogHolder.DAY_LOG_THOUGHT;


public class EditLogFragment extends Fragment {

    private EditText editLogText;
    private Button sumbitUpdateButton;
    private static Bundle args;
    DayLogDatabaseHelper dayLogDatabaseHelper;
    OnFragmentInteractionListener listener;

    private String dayLogDate;
    private String dayLogThoughts;
    private String dayLogMood;
    private String dayLogCause;
    private String dayLogDescription;
    private long id;
    private String updatedCause;

    public EditLogFragment() {
    }


    public static EditLogFragment newInstance(Bundle bundle) {
        EditLogFragment fragment = new EditLogFragment();
        args = bundle;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());
        if (getArguments() != null) {
            dayLogDate = getArguments().getString(DAY_LOG_DATE);
            dayLogDescription = getArguments().getString(DAY_LOG_DESCRIPTION);
            dayLogThoughts = getArguments().getString(DAY_LOG_THOUGHT);
            dayLogMood = getArguments().getString(DAY_LOG_MOOD);
            dayLogCause = getArguments().getString(DAY_LOG_CAUSE);
            id = getArguments().getLong(DAY_LOG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_log, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputEditText", editLogText.getText().toString());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editLogText = view.findViewById(R.id.editLog_moodCauseEditText);
        sumbitUpdateButton = view.findViewById(R.id.edit_log_submit_button);

        if (savedInstanceState != null){
            editLogText.setText(savedInstanceState.getString("inputEditText"));
        }

        sumbitUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatedCause = editLogText.getText().toString();

                dayLogDatabaseHelper.updateLog(updatedCause, id, dayLogCause);

                Log.d(".EditLogFragment", "onCLick: " + updatedCause + " " + id + " " + dayLogCause);

                Toast.makeText(getContext(), "Your log has been updated", Toast.LENGTH_SHORT).show();

//                InitialFragment initialFragment = InitialFragment.newInstance();
//                onButtonPressed(initialFragment);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.popBackStack();

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
