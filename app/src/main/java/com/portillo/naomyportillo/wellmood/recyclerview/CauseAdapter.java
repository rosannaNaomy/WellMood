package com.portillo.naomyportillo.wellmood.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portillo.naomyportillo.wellmood.R;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;

import java.util.List;

public class CauseAdapter extends RecyclerView.Adapter<CauseHolder> {

    List<DayLogModel> models;
    String mood;

    public CauseAdapter(List<DayLogModel> models, String mood) {
        this.models = models;
        this.mood = mood;
    }

    @NonNull
    @Override
    public CauseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cause_item, viewGroup, false);
        return new CauseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CauseHolder causeHolder, int pos) {
        causeHolder.onBind(models.get(pos), mood);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
