package com.portillo.naomyportillo.wellmood.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.MainActivity;
import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.logfragments.SingleDailyLogDisplayFragment;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

import com.portillo.naomyportillo.wellmood.MainActivity;

public class DayLogHolder extends RecyclerView.ViewHolder {

    private TextView dateTextView;
    private CardView dailyLogCardView;

    public static final String DAY_LOG_DESCRIPTION = "dayDescription";
    public static final String DAY_LOG_THOUGHT = "dayThought";
    public static final String DAY_LOG_DATE = "dayDate";
    public static final String DAY_LOG_MOOD = "dayMood";
    public static final String DAY_LOG_CAUSE = "dayCause";

    public DayLogHolder(@NonNull View itemView) {
        super(itemView);

        dateTextView = itemView.findViewById(R.id.dailylog_dateTextView);
        dailyLogCardView = itemView.findViewById(R.id.dailylog_cardview);

    }

    public void onBind(DayLogModel logModel, final OnFragmentInteractionListener listener) {
        final Bundle bundle = new Bundle();

        dateTextView.setText(logModel.getDate());

        Log.d(".DayHolderActivity", "nummy - date" + logModel.getDate());
        Log.d(".DayHolderActivity", "nummy - thoughts: " +logModel.getThoughts());
        Log.d(".DayHolderActivity", "nummy - cause: " +logModel.getCause());
        bundle.putString(DAY_LOG_DATE, logModel.getDate());
        bundle.putString(DAY_LOG_DESCRIPTION, logModel.getDayDescription());
        bundle.putString(DAY_LOG_THOUGHT, logModel.getThoughts());
        bundle.putString(DAY_LOG_MOOD, logModel.getMood());
        bundle.putString(DAY_LOG_CAUSE, logModel.getCause());

        dailyLogCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFragmentInteraction(SingleDailyLogDisplayFragment.newInstance(bundle));
            }
        });
    }

}
