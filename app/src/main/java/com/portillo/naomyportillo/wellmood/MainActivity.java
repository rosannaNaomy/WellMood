package com.portillo.naomyportillo.wellmood;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.DayDescriptionFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.InitialFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DayDescriptionFragment dayDescriptionFragment = DayDescriptionFragment.newInstance();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, dayDescriptionFragment)
//                .addToBackStack("detailFragment").commit();

        InitialFragment initialFragment = InitialFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, initialFragment)
                .addToBackStack("detailFragment").commit();
    }


    @Override
    public void onFragmentInteraction(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack("nextFragment").commit();
    }
}
