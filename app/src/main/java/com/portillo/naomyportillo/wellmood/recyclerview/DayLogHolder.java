package com.portillo.naomyportillo.wellmood.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

public class DayLogHolder extends RecyclerView.ViewHolder {

    private TextView dateTextView;

    public DayLogHolder(@NonNull View itemView) {
        super(itemView);

        dateTextView = itemView.findViewById(R.id.dailylog_dateTextView);
    }

    public void onBind(DayLogModel logModel){

        dateTextView.setText(logModel.getDate());
    }
}
