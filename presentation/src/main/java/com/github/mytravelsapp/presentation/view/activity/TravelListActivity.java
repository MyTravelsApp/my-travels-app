package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelComponent;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;

/**
 * Activity that shows a list of travels.
 *
 * @author fjtorres
 */
public class TravelListActivity extends AbstractActivity implements HasComponent<TravelComponent> {

    private TravelComponent component;

    /**
     * Generate intent to open this activity.
     *
     * @param context Source context.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, TravelListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout definition for activity
        setContentView(R.layout.activity_travel_list);

        // Set the support toolbar to show in activity
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        initializeInjector();
    }

    /**
     * Initialize DI components for this activity.
     */
    private void initializeInjector() {
        this.component = DaggerTravelComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public TravelComponent getComponent() {
        return component;
    }

}
