package com.portillo.naomyportillo.wellmood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addLogbtn;
    Button viewLogsbtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addLogbtn = findViewById(R.id.addLog);
        viewLogsbtn = findViewById(R.id.viewLogs);
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
    }


}
