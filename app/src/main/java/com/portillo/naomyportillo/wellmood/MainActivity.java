package com.portillo.naomyportillo.wellmood;

import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.portillo.naomyportillo.wellmood.initialfragments.AboutFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.InitialFragment;
import com.portillo.naomyportillo.wellmood.initialfragments.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            initialFragment();
        }
    }

    public void initialFragment() {
        InitialFragment initialFragment = InitialFragment.newInstance();
        onFragmentInteraction(initialFragment);
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
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
                AboutFragment aboutFragment = AboutFragment.newInstance();
                onFragmentInteraction(aboutFragment);
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
