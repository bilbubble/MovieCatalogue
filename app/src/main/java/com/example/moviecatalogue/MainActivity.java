package com.example.moviecatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNav;
    public static final String EXTRA_STRING = "extra_string";
    private Map<Integer, Fragment> fragmentMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bnv_main);
        fragmentMaps = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // put all fragments to map
        fragmentMaps.put(R.id.menu_item_movie, MovieFragment.newInstance());
        fragmentMaps.put(R.id.menu_item_tv_show, TvShowFragment.newInstance());
        fragmentMaps.put(R.id.menu_item_profile, ProfileFragment.newInstance());

        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.menu_item_movie);

        // change action bar's title color
        changeActionBarTitle(MovieFragment.newInstance().getArguments().getString(EXTRA_STRING));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMaps.get(item.getItemId());
        if (fragment != null) {
            assert fragment.getArguments() != null;
            String titleActionBar = fragment.getArguments().getString(EXTRA_STRING);
            changeActionBarTitle(titleActionBar);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();
        }
        return true;
    }

    private void changeActionBarTitle(String title) {
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#ED4E7C\">" + title + "</font>")));
    }
}