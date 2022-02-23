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
import android.widget.Toast;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

import static com.portillo.naomyportillo.wellmood.initialfragments.DayThoughtFragment.DAY_DESCRIPTION;
import static com.portillo.naomyportillo.wellmood.initialfragments.DayThoughtFragment.DAY_THOUGHT;
import static com.portillo.naomyportillo.wellmood.initialfragments.FeelFragment.MOOD;


public class MoodCauseFragment extends Fragment {


    private static Bundle args;
    public static final String MOOD_CAUSE = "moodCause";
    private String moodCause;
    private String dayDescription;
    private String dayThought;
    private String mood;


    private EditText moodCauseEditText;
    private Button causeSubmitButton;
    private DayLogDatabaseHelper dayLogDatabaseHelper;
    private OnFragmentInteractionListener listener;


    public MoodCauseFragment() {
    }

    public static MoodCauseFragment newInstance(Bundle bundle) {
        MoodCauseFragment fragment = new MoodCauseFragment();
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
            mood = getArguments().getString(MOOD);
            Log.d(".MoodCauseFragment", "nummy - onCreate - description: " + dayDescription);
            Log.d(".MoodCauseFragment", "nummy - onCreate - mood: "  + mood);

            Log.d(".MoodCauseFragment", "nummy - onCreate - thought: "  + dayThought);

        }
        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mood_cause, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moodCauseEditText = view.findViewById(R.id.moodCauseEditText);
        causeSubmitButton = view.findViewById(R.id.submitButton_cause);

        if (savedInstanceState != null){
            moodCauseEditText.setText(savedInstanceState.getString("inputEditText"));
        }

        onClick();
    }

    private void addData() {
        dayLogDatabaseHelper.addLog(new DayLogModel(-1,null, dayDescription, dayThought, mood, moodCause));
        Log.i("Note List", dayDescription + " " + dayThought + " " + mood + " " + moodCause);

        Log.i("Note List", dayLogDatabaseHelper.getLogList().toString());

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputEditText", moodCauseEditText.getText().toString());
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

    private void onClick() {
        causeSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moodCause = moodCauseEditText.getText().toString();
                args.putString(MOOD_CAUSE, moodCause);
                setArguments(args);
                addData();

                Log.i("logfragment", "" + moodCause);
                Log.i("logfragment", "" + mood);
                Log.i("logfragment", "" + dayThought);
                Log.i("logfragment", "" + dayDescription);

                InitialFragment initialFragment = InitialFragment.newInstance();
                Toast.makeText(getContext(), "Your log has been added", Toast.LENGTH_SHORT).show();
                onButtonPressed(initialFragment);
            }
        });
    }


}
