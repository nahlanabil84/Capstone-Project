package com.nanodegree.nahla.capstoneproject.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.fragments.HistoryFragment;
import com.nanodegree.nahla.capstoneproject.fragments.HomeFragment;
import com.nanodegree.nahla.capstoneproject.fragments.SettingsFragment;
import com.nanodegree.nahla.capstoneproject.fragments.TypesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.nanodegree.nahla.capstoneproject.Utils.Const.FRAGMENT_TAG_HISTORY;
import static com.nanodegree.nahla.capstoneproject.Utils.Const.FRAGMENT_TAG_SETTINGS;
import static com.nanodegree.nahla.capstoneproject.Utils.Const.FRAGMENT_TAG_TODAY;
import static com.nanodegree.nahla.capstoneproject.Utils.Const.FRAGMENT_TAG_TYPES;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationV)
    NavigationView navigationV;
    @BindView(R.id.sortIV)
    ImageView sortIV;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View headerV;
    private TextView headerNameTV;
    private CircleImageView headerImageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpToolbar();
        setUpDrawerHeader();
        startFragment(new HomeFragment(), FRAGMENT_TAG_TODAY);
    }

    private void setUpDrawerHeader() {
        headerV = navigationV.getHeaderView(0);
        headerNameTV = headerV.findViewById(R.id.nameTV);
        headerImageIV = headerV.findViewById(R.id.profileCIV);

        headerNameTV.setText(getString(R.string.name));

        Glide.with(this)
                .load("")
                .centerCrop()
                .placeholder(R.drawable.person)
                .error(R.drawable.person)
                .into(headerImageIV);

    }

    private void setUpToolbar() {
        setTitle("");
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationV.setNavigationItemSelectedListener(this);
        toolbar.setNavigationOnClickListener(this);

        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void startFragment(Fragment fragment, String tag) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.containerFL);

        //first time to load HomeFragment
        if (currentFragment == null) {
            Log.d("replaceContentMain", "first time to load home");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFL, fragment, tag)
                    .commit();
            return;
        }


        if (tag.equals(currentFragment.getTag())) {
            Log.d("replaceContentMain", "tag equals current fragment");
            return;
        }

        //special case for handling navigation from EditProfile to profile from the navigation drawer
        //couldn't find a better solution :/
        if (tag.equals("profile") && currentFragment.getTag().equals("EditProfileFragment")) {
            getSupportFragmentManager().popBackStack();
            return;
        }


        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            Log.d("replaceContentMain", "backStack count " + getSupportFragmentManager().getBackStackEntryCount());
            getSupportFragmentManager().popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


        if (!tag.equals("home")) {
            Log.d("replaceContentMain", "fragment tag is " + tag);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFL, fragment, tag)
                    .addToBackStack("home")
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        final int counterFragments = getSupportFragmentManager().getBackStackEntryCount();

        // check the navigation is opened or not
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        if (counterFragments > 0) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().addOnBackStackChangedListener(this);
        } else {
            super.onBackPressed();
            return;
        }
    }

    @Override
    public void onClick(View v) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.todaySTask:
                startFragment(new HomeFragment(), FRAGMENT_TAG_TODAY);
                drawerLayout.closeDrawers();
                break;
            case R.id.history:
                startFragment(new HistoryFragment(), FRAGMENT_TAG_HISTORY);
                drawerLayout.closeDrawers();
                break;
            case R.id.types:
                startFragment(new TypesFragment(), FRAGMENT_TAG_TYPES);
                drawerLayout.closeDrawers();
                break;
            case R.id.settings:
                startFragment(new SettingsFragment(), FRAGMENT_TAG_SETTINGS);
                drawerLayout.closeDrawers();
                break;
        }
        return true;

    }

    @Override
    public void onBackStackChanged() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.containerFL);
        if (currentFragment.getTag().equals(FRAGMENT_TAG_TODAY))
            sortIV.setVisibility(View.VISIBLE);
    }
}
