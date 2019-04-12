package com.portillo.naomyportillo.wellmood;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.portillo.naomyportillo.wellmood.database.DayLogDatabaseHelper;
import com.portillo.naomyportillo.wellmood.initialfragments.DayDescriptionFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.InitialFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;
import com.portillo.naomyportillo.wellmood.logfragments.SingleDailyLogDisplayFragment;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack("nextFragment").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appmenu, menu);
        return true;
    }

    public void linkIntent(String link) {

        Intent openLink = new Intent(Intent.ACTION_VIEW);
        openLink.setData(Uri.parse(link));

        if (openLink.resolveActivity(getPackageManager()) != null) {
            startActivity(openLink);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.git:
                linkIntent(getString(R.string.gitHubLink));
                return true;

            case R.id.linked:
                linkIntent(getString(R.string.linkedInLink));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
