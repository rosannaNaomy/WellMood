package com.portillo.naomyportillo.wellmood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.model.DayLogModel;
import com.portillo.naomyportillo.wellmood.recyclerview.DayLogAdapter;

import java.util.List;

public class DisplayLogActivity extends AppCompatActivity {
    private TextView displayTextView;

    List<DayLogModel> logs;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log);

        DayLogDatabaseHelper dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(getApplicationContext());

        logs = dayLogDatabaseHelper.getLogList();
        recyclerView = findViewById(R.id.recycler_view_container);

//        displayTextView = findViewById(R.id.displayText);
//        displayTextView.setText(logs.get(0).getCause().toString());

       // dayLogDatabaseHelper.clearLogList();

        recyclerView.setAdapter(new DayLogAdapter(logs));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
