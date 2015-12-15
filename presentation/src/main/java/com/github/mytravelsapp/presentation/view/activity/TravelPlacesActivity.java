package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelPlacesComponent;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.fragment.TravelDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesFragment;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesActivity extends AbstractActivity implements HasComponent<TravelPlacesComponent>, TravelPlacesFragment.TravelPlacesListener{

    private TravelPlacesComponent component;
    private long travelId;

    private static final String INTENT_EXTRA_PARAM_TRAVEL_ID = "INTENT_PARAM_TRAVEL_ID";
    private static final String STATE_PARAM_TRAVEL_ID = "STATE_PARAM_TRAVEL_ID";

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
            travelId = getIntent().getLongExtra(INTENT_EXTRA_PARAM_TRAVEL_ID, TravelModel.DEFAULT_ID);
            addFragment(R.id.fragmentTravelPlaces, TravelPlacesFragment.newInstance(this.travelId));
        } else {
            travelId = savedInstanceState.getLong(STATE_PARAM_TRAVEL_ID);
        }

        //Para activar el dagger, de momento no lo tenemos activo.
        initializeInjector();
    }

    /**
     * Method necessary for navigation of activity.
     */
    public static Intent getCallingIntent(final Context context, final long travelId) {
        final Intent callingIntent = new Intent(context, TravelPlacesActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TRAVEL_ID, travelId);
        return callingIntent;
    }



    /**
     * Load activity menu.
     *
     * @param menu Activity menu.
     * @return true if activity has menu otherwise false.
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_travel_list, menu);
        return true;
    }

    @Override
    public void onTravelPlacesClicked(TravelPlacesModel model) {

    }

    @Override
    public void onAddTravelPlacesClicked(final TravelModel model) {
        navigator.navigateToTravelPlacesDetail(this, new TravelPlacesModel(TravelPlacesModel.DEFAULT_ID, model.getId()));
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
}

