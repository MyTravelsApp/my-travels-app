package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelComponent;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.fragment.TravelDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlanningFragment;

/**
 * Created by kisco on 24/01/2016.
 */
public class TravelPlanningActivity extends AbstractActivity implements HasComponent<TravelComponent> {

    private static final String INTENT_EXTRA_PARAM_TRAVEL_MODEL = "INTENT_PARAM_TRAVEL_MODEL";
    private static final String STATE_PARAM_TRAVEL_MODEL = "STATE_PARAM_TRAVEL_MODEL";

    private TravelComponent component;
    private TravelModel travelModel;

    /**
     * Generate intent to open this activity.
     *
     * @param context  Source context.
     * @param pTravelModel Travel identifier to view in organizer.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context, final TravelModel pTravelModel) {
        final Intent callingIntent = new Intent(context, TravelPlanningActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TRAVEL_MODEL, pTravelModel);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout definition for activity
        setContentView(R.layout.activity_travel_planning);

        // Set the support toolbar to show in activity
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Load travel identifier from parameters or saved state.
        if (savedInstanceState == null) {
            travelModel = getIntent().getParcelableExtra(INTENT_EXTRA_PARAM_TRAVEL_MODEL);
            addFragment(R.id.fragmentTravelOrganizer, TravelPlanningFragment.newInstance(travelModel));
        } else {
            travelModel = savedInstanceState.getParcelable(STATE_PARAM_TRAVEL_MODEL);
        }

        initializeInjector();
    }

    /**
     * Save activity state.
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

    /**
     * Control menu item selection.
     *
     * @param item Selected menu.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                navigator.navigateToTravelDetail(this, travelModel);
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
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
