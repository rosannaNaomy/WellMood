package com.portillo.naomyportillo.wellmood.initialfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;


public class DayDescriptionFragment extends Fragment {

    private OnFragmentInteractionListener listener;
    private TextView productiveDescription;
    private TextView greatDescription;
    private TextView badDescription;
    private TextView unpleasantDescription;

    public DayDescriptionFragment() {
        // Required empty public constructor
    }

    public static DayDescriptionFragment newInstance() {
        return new DayDescriptionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productiveDescription = view.findViewById(R.id.productiveTextView);
        greatDescription = view.findViewById(R.id.greatTextView);
        badDescription = view.findViewById(R.id.badTextView);
        unpleasantDescription = view.findViewById(R.id.unpleasantextView);

        textViewOnClick(productiveDescription, productiveDescription.getText().toString());
        textViewOnClick(greatDescription, greatDescription.getText().toString());
        textViewOnClick(badDescription, badDescription.getText().toString());
        textViewOnClick(unpleasantDescription, unpleasantDescription.getText().toString());

    }

    private void textViewOnClick(final View view, final String text){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DayThoughtFragment dayThoughtFragment = DayThoughtFragment.newInstance(text);
                onButtonPressed(dayThoughtFragment);
                Log.d("description", " nummy - on pressed" + text);
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
