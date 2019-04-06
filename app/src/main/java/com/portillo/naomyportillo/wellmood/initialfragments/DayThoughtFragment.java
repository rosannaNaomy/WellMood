package com.portillo.naomyportillo.wellmood.initialfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.portillo.naomyportillo.wellmood.R;

public class DayThoughtFragment extends Fragment {

    public static final String DAY_DESCRIPTION = "dayDescription";
    public static final String DAY_THOUGHT = "dayThought";

    private static Bundle args;
    private String dayThought;
    private String dayDescription;
    private EditText thoughtEditText;
    private Button thoughtSubmitButton;
    private OnFragmentInteractionListener listener;


    public DayThoughtFragment() {
    }

    public static DayThoughtFragment newInstance(String dayDescription) {
        DayThoughtFragment fragment = new DayThoughtFragment();
        args = new Bundle();
        args.putString(DAY_DESCRIPTION, dayDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dayDescription = getArguments().getString(DAY_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_thought, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        thoughtEditText = view.findViewById(R.id.editText_thought);
        thoughtSubmitButton = view.findViewById(R.id.submit_button_thought);


        dayThought = thoughtEditText.getText().toString();
        args.putString(DAY_THOUGHT, dayThought);
        setArguments(args);

        onClick(args);

    }

    private void onClick(final Bundle fragmentBundle) {
        thoughtSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeelFragment feelFragment = FeelFragment.newInstance(fragmentBundle);
                onButtonPressed(feelFragment);
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
