package com.portillo.naomyportillo.wellmood;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.portillo.naomyportillo.wellmood.initialfragments.DayDescriptionFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;

public class LogActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        DayDescriptionFragment dayDescriptionFragment = DayDescriptionFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, dayDescriptionFragment)
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
