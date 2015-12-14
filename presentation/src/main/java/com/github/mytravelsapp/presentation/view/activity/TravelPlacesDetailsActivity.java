package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelPlacesComponent;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesDetailsFragment;

/**
 * Created by stefani on 11/12/2015.
 */
public class TravelPlacesDetailsActivity  extends AbstractActivity implements HasComponent<TravelPlacesComponent>, TravelPlacesDetailsFragment.TravelPlacesDetailsListener{

    private TravelPlacesComponent component;

    private static final String INTENT_EXTRA_PARAM_TRAVEL_PLACES_ID = "INTENT_PARAM_TRAVEL_PLACES_ID";

    private long travelPlacesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_places_details);

        // Set the support toolbar to show in activity
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        // Active home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Load travel identifier from parameters or saved state.
        if (savedInstanceState == null) {
            travelPlacesId = getIntent().getLongExtra(INTENT_EXTRA_PARAM_TRAVEL_PLACES_ID, TravelPlacesModel.DEFAULT_ID);
            addFragment(R.id.fragment_places_detail, TravelPlacesDetailsFragment.newInstance(this.travelPlacesId));
        } else {
            //travelPlacesId = savedInstanceState.getLong(STATE_PARAM_TRAVEL_ID);
        }

        initializeInjector();
    }

    @Override
    public TravelPlacesComponent getComponent() {
        return component;
    }

    /**
     * Method necessary for navigation of activity.
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, TravelDetailsActivity.class);
    }

    /**
     * Generate intent to open this activity.
     *
     * @param context  Source context.
     * @param travelPlacesId Travel identifier to view in detail.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context, final long travelPlacesId) {
        final Intent callingIntent = new Intent(context, TravelPlacesDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TRAVEL_PLACES_ID, travelPlacesId);
        return callingIntent;
    }

    /**
     * Initialize DI components for this activity.
     */
    private void initializeInjector() {
        this.component = DaggerTravelPlacesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
