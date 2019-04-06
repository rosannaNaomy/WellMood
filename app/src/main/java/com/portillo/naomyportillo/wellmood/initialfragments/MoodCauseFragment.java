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
import android.widget.EditText;

import com.portillo.naomyportillo.wellmood.DisplayLogActivity;
import com.portillo.naomyportillo.wellmood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodCauseFragment extends Fragment {


    private static Bundle args;
    public static final String MOOD_CAUSE = "moodCause";
    private String moodCause;
    private EditText moodCauseEditText;
    private Button causeSubmitButton;
    private OnFragmentInteractionListener listener;


    public MoodCauseFragment() {
        // Required empty public constructor
    }

    public static MoodCauseFragment newInstance(Bundle bundle) {
        MoodCauseFragment fragment = new MoodCauseFragment();
        args = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_cause, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moodCauseEditText = view.findViewById(R.id.moodCauseEditText);
        causeSubmitButton = view.findViewById(R.id.submitButton_cause);

        moodCause = moodCauseEditText.getText().toString();
        args.putString(MOOD_CAUSE, moodCause);
        setArguments(args);

        onClick();
    }

    private void onClick() {
        causeSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DisplayLogActivity.class);
                startActivity(intent);
            }
        });
    }




}
