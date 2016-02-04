package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelPlacesComponent;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesFragment;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesActivity extends AbstractActivity implements HasComponent<TravelPlacesComponent>{

    private TravelPlacesComponent component;
    private TravelModel travelModel;

    private static final String INTENT_EXTRA_PARAM_TRAVEL_MODEL = "INTENT_PARAM_TRAVEL_MODEL";
    private static final String STATE_PARAM_TRAVEL_MODEL = "STATE_PARAM_TRAVEL_MODEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout definition for activity
        setContentView(R.layout.activity_travel_places);

        // Set the support toolbar to show in activity
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // Active home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Load travel identifier from parameters or saved state.
        if (savedInstanceState == null) {
            travelModel = getIntent().getParcelableExtra(INTENT_EXTRA_PARAM_TRAVEL_MODEL);
            addFragment(R.id.fragmentTravelPlaces, TravelPlacesFragment.newInstance(this.travelModel));
        } else {
            travelModel = savedInstanceState.getParcelable(STATE_PARAM_TRAVEL_MODEL);
        }

        setTitle(travelModel.getName());

        //Para activar el dagger, de momento no lo tenemos activo.
        initializeInjector();
    }

    /**
     * Method necessary for navigation of activity.
     */
    public static Intent getCallingIntent(final Context context, final TravelModel travelModel) {
        final Intent callingIntent = new Intent(context, TravelPlacesActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TRAVEL_MODEL, travelModel);
        return callingIntent;
    }

    /**
     * Save activity state. Execute when you minimize this activity.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelable(STATE_PARAM_TRAVEL_MODEL, travelModel);
        }
        super.onSaveInstanceState(outState);
    }


    @Override
    public TravelPlacesComponent getComponent() {
        return component;
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

    /**
     * Control menu item selection.
     *
     * @param item Selected menu.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                navigator.navigateToTravelList(this);
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }
}

