package com.portillo.naomyportillo.wellmood.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

import java.util.List;

public class DayLogAdapter extends RecyclerView.Adapter<DayLogHolder> {

    private final OnFragmentInteractionListener listener;
    private List<DayLogModel> logModels;


    public DayLogAdapter(OnFragmentInteractionListener listener, List<DayLogModel> logModels) {
        this.listener = listener;
        this.logModels = logModels;
    }

    @NonNull
    @Override
    public DayLogHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dailylog_item, viewGroup, false);
        return new DayLogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayLogHolder dayLogHolder, int pos) {
        dayLogHolder.onBind(logModels.get(pos), listener);
    }

    @Override
    public int getItemCount() {
        return logModels.size();
    }

}
