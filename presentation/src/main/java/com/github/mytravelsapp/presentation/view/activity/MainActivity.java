package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerMainComponent;
import com.github.mytravelsapp.presentation.di.components.MainComponent;
import com.github.mytravelsapp.presentation.view.fragment.CategoryFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;

/**
 * Created by stefani on 14/01/2016.
 */
public class MainActivity extends AbstractActivity implements HasComponent<MainComponent>{

    private MainComponent component;

    private static final int CATEGORYES = 0;
    private static final int TRAVEL_LIST = 1;
    private static final int TRAVELS_ARCHIVED = 2;
    private static final int ABOUT = 3;

    private DrawerLayout drawerLayout;
    private String drawerTitle;
    private Toolbar toolbar;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;


    private String[] drawerOptions;

    /**
     * Generate intent to open this activity.
     *
     * @param context Source context.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout definition for activity
        setContentView(R.layout.activity_main_drawable);
        // Set the support toolbar to show in activity
        setToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        drawerTitle = getResources().getString(R.string.activity_travel_list_title);
        if (savedInstanceState == null) {
            selectItem(drawerTitle);
        }
        initializeInjector();
    }




    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        // Crear nuevo fragmento
                        String title = menuItem.getTitle().toString();
                        selectItem(title);
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize DI components for this activity.
     */
    private void initializeInjector() {
        this.component = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private void selectItem(String title) {
        Fragment fragment  = new TravelListFragment();
        if(title.equals(getString(R.string.activity_categories_title))){
            fragment=  new CategoryFragment();
        }

        replaceFragment(R.id.content_frame, fragment);

        drawerLayout.closeDrawers(); // Cerrar drawer

        setTitle(title); // Setear título actual

    }


    @Override
    public MainComponent getComponent() {
        return component;
    }
}
