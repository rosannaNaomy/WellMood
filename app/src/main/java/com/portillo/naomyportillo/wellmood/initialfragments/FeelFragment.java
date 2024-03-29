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
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;

import static com.portillo.naomyportillo.wellmood.initialfragments.DayThoughtFragment.DAY_DESCRIPTION;
import static com.portillo.naomyportillo.wellmood.initialfragments.DayThoughtFragment.DAY_THOUGHT;


public class FeelFragment extends Fragment {

    private OnFragmentInteractionListener listener;
    private TextView greatTextView;
    private TextView badTextView;
    private TextView terribleTextView;
    private TextView okayTextView;
    public static final String MOOD = "mood";


    private static Bundle args;
    private String dayDescription;
    private String dayThought;
    public String mood;

    public FeelFragment() {
    }

    public static FeelFragment newInstance(Bundle bundle) {
        FeelFragment fragment = new FeelFragment();
        args = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dayDescription = getArguments().getString(DAY_DESCRIPTION);
            dayThought = getArguments().getString(DAY_THOUGHT);

        }

        Log.d(".FeelFragment", "nummy - onCreate - thought: " + dayThought);
        Log.d(".FeelFragment", "nummy - onCreate - description: " + dayDescription);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        greatTextView = view.findViewById(R.id.excitingTextView);
        badTextView = view.findViewById(R.id.badFeelTextView);
        okayTextView = view.findViewById(R.id.mood_Textview);
        terribleTextView = view.findViewById(R.id.terribleTextview);

        textViewOnClick(greatTextView, greatTextView.getText().toString());
        textViewOnClick(badTextView, badTextView.getText().toString());
        textViewOnClick(okayTextView, okayTextView.getText().toString());
        textViewOnClick(terribleTextView, terribleTextView.getText().toString());

    }

    private void textViewOnClick(View view, final String text) {


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = text;
                args.putString(MOOD, mood);
                args.putString(DAY_DESCRIPTION, dayDescription);
                args.putString(DAY_THOUGHT, dayThought);
                setArguments(args);

                Log.d(".FeelFragment", "nummy - onClick - thought: " + mood);
                Log.d(".FeelFragment", "nummy - onClick - thought: " + dayThought);
                Log.d(".FeelFragment", "nummy - onClick - description: " + dayDescription);

                MoodCauseFragment moodCauseFragment = MoodCauseFragment.newInstance(args);
                onButtonPressed(moodCauseFragment);
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
