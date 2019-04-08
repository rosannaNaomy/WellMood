package com.portillo.naomyportillo.wellmood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Button addLogbtn;
    private Button viewLogsbtn;
    private Button deleteLogsButton;
    private Intent intent;
    private DayLogDatabaseHelper dayLogDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dayLogDatabaseHelper = DayLogDatabaseHelper.getInstance(this);
        addLogbtn = findViewById(R.id.addLog);
        viewLogsbtn = findViewById(R.id.viewLogs);
        deleteLogsButton = findViewById(R.id.cleardata_button);

        OnclickBttns();
    }

    private void OnclickBttns() {
        addLogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(intent);
            }
        });

        viewLogsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), DisplayLogActivity.class);
                startActivity(intent);
            }
        });

        deleteLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayLogDatabaseHelper.clearLogList();
            }
        });

    }


}
