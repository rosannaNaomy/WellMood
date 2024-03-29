package com.portillo.naomyportillo.wellmood.recyclerview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.logfragments.SingleDailyLogDisplayFragment;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

public class DayLogHolder extends RecyclerView.ViewHolder {

    private TextView dateTextView;
    private CardView dailyLogCardView;

    public static final String DAY_LOG_DESCRIPTION = "dayDescription";
    public static final String DAY_LOG_THOUGHT = "dayThought";
    public static final String DAY_LOG_DATE = "dayDate";
    public static final String DAY_LOG_MOOD = "dayMood";
    public static final String DAY_LOG_CAUSE = "dayCause";
    public static final String DAY_LOG_ID = "dayId";


    public DayLogHolder(@NonNull View itemView) {
        super(itemView);

        dateTextView = itemView.findViewById(R.id.dailylog_dateTextView);
        dailyLogCardView = itemView.findViewById(R.id.dailylog_cardview);

    }

    public void onBind(DayLogModel logModel, final OnFragmentInteractionListener listener) {
        final Bundle bundle = new Bundle();

        dateTextView.setText(logModel.getDate());

        Log.d(".DayHolder", "nummy - date" + logModel.getDate());
        Log.d(".DayHolder", "nummy - thoughts: " +logModel.getThoughts());
        Log.d(".DayHolder", "nummy - cause: " +logModel.getCause());
        Log.d(".DayHolder", "nummy - id: " + logModel.getId());

        bundle.putLong(DAY_LOG_ID, logModel.getId());
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
