package com.portillo.naomyportillo.wellmood.logfragments;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

import java.util.List;


public class ReoccurringBehaviorFragment extends Fragment {

    DayLogDatabaseHelper dayLogDatabaseHelper;
    OnFragmentInteractionListener listener;

    private ImageView greatImagaView;
    private ImageView badImagaView;
    private ImageView okayImagaView;
    private ImageView terribleImagaView;

    private TextView greatextView;
    private TextView badtextView;
    private TextView okaytextView;
    private TextView terribletextView;

    public ReoccurringBehaviorFragment() {
    }

    public static ReoccurringBehaviorFragment newInstance() {
        return new ReoccurringBehaviorFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reoccurring_behavior, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        greatImagaView = view.findViewById(R.id.load_great_imageView);
        badImagaView = view.findViewById(R.id.load_bad_imageView);
        okayImagaView = view.findViewById(R.id.load_okay_imageView);
        terribleImagaView = view.findViewById(R.id.load_terrible_imageView);

        greatextView = view.findViewById(R.id.reoccurring_greatTextView);
        badtextView = view.findViewById(R.id.reoccurring_badFeelTextView);
        okaytextView = view.findViewById(R.id.reoccurring_okatTextview);
        terribletextView = view.findViewById(R.id.reoccurring_terribleTextview);

        getCount();

        textViewOnClick( greatextView,  greatextView.getText().toString());
        textViewOnClick(badtextView, badtextView.getText().toString());
        textViewOnClick(okaytextView, okaytextView.getText().toString());
        textViewOnClick(terribletextView, terribletextView.getText().toString());


    }

    private void textViewOnClick(View view, final String text) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisplayCauseFragment displayCauseFragment = DisplayCauseFragment.newInstance(text);
                onButtonPressed(displayCauseFragment);
            }
        });
    }

    private void getCount() {

        int greatSetWidth = 0;
        int badSetWidth = 0;
        int okaySetWidth = 0;
        int terribbleSetWidth = 0;

        List<DayLogModel> databaseList = dayLogDatabaseHelper.getLogList();

        for (int i = 0; i < databaseList.size(); i++) {
            if (databaseList.get(i).getMood().toLowerCase().equals("okay")) {
                okaySetWidth++;
            }
            if (databaseList.get(i).getMood().toLowerCase().equals("great")) {
                greatSetWidth++;
            }
            if (databaseList.get(i).getMood().toLowerCase().equals("bad")) {
                badSetWidth++;
            }
            if (databaseList.get(i).getMood().toLowerCase().equals("terrible")) {
                terribbleSetWidth++;
            }
        }

        int widths[] = {greatSetWidth, badSetWidth, okaySetWidth, terribbleSetWidth};

        for (int i = 0; i < widths.length; i++) {
            widths[i] = getWidth(widths[i]);
        }

        setWidth(widths[0], widths[1], widths[2], widths[3]);
    }

    //if 0 set to 1;
    //if max set to 350
    //if less than max more than min set to 175
    //if min set to 90

    public int getWidth(int width) {

        int max = 31;
        int min = 10;
        int midMax = 20;

        if (width == 0 || width <= 1) {
            width = 1;
        }
        if (width <= min && width >= 2) {
            width = 90;
        }
        if (width <= max && width >= midMax) {
            width = 350;
        }
        if (width < midMax && width >= min) {
            width = 175;
        }

        return width;
    }

    private void setWidth(int greatSetWidth, int badSetWidth, int okaySetWidth, int terribbleSetWidth) {
        greatImagaView.requestLayout();
        greatImagaView.getLayoutParams().width = greatSetWidth;

        badImagaView.requestLayout();
        badImagaView.getLayoutParams().width = badSetWidth;

        okayImagaView.requestLayout();
        okayImagaView.getLayoutParams().width = okaySetWidth;

        terribleImagaView.requestLayout();
        terribleImagaView.getLayoutParams().width = terribbleSetWidth;
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
