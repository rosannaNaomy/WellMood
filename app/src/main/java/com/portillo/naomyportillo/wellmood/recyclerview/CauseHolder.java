package com.portillo.naomyportillo.wellmood.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

public class CauseHolder extends RecyclerView.ViewHolder {

    private TextView displayCause;
    private TextView displayCauseDate;

    public CauseHolder(@NonNull View itemView) {
        super(itemView);

        displayCause = itemView.findViewById(R.id.item_cause_textview);
        displayCauseDate = itemView.findViewById(R.id.item_cause_date_textview);

    }

    public void onBind(DayLogModel logModel, String mood) {

        Log.d(".CauseHolder", "Nummy - " + mood);

        Log.d(".CauseHolder", "Nummy - " + logModel.getMood());
        if (logModel.getMood().equals(mood)) {
            displayCauseDate.setText(logModel.getDate());
            displayCause.setText(logModel.getCause());
            Log.d(".CauseHolder", "Nummy - " + logModel.getCause());

        }
    }


}
