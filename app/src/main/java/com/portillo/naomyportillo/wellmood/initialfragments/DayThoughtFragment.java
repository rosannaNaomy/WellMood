package com.portillo.naomyportillo.wellmood.initialfragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
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
        Log.d(".DayThoughtFragment", "nummy - description: " + dayDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dayDescription = getArguments().getString(DAY_DESCRIPTION);
        }
        Log.d(".DayThoughtFragment", "nummy - description: " + dayDescription);
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

        if (savedInstanceState != null){
            thoughtEditText.setText(savedInstanceState.getString("inputEditText"));
        }

        onClick();

    }

    private void onClick() {
        thoughtSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayThought = thoughtEditText.getText().toString();
                Log.d(".DayThoughtFragment", "nummy - editText_thoughts: " + dayThought);
                args.putString(DAY_THOUGHT, dayThought);
                args.putString(DAY_DESCRIPTION, dayDescription);

                setArguments(args);
                Log.d(".DayThoughtFragment", "nummy - onClick - thought: " + dayThought);
                Log.d(".DayThoughtFragment", "nummy - onClick - description: " + dayDescription);

                FeelFragment feelFragment = FeelFragment.newInstance(args);
                onButtonPressed(feelFragment);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputEditText", thoughtEditText.getText().toString());
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
