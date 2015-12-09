package com.github.mytravelsapp.presentation.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.github.mytravelsapp.presentation.AndroidApplication;
import com.github.mytravelsapp.presentation.di.components.ApplicationComponent;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for all activities in this application.
 *
 * @author fjtorres
 */
public abstract class AbstractActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(final int containerViewId, final Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.github.mytravelsapp.presentation.di.components.ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
