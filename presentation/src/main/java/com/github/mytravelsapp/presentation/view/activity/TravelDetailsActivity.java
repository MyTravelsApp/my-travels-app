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

/**
 * Activity that shows details of certain travel.
 *
 * @author fjtorres
 */
public class TravelDetailsActivity extends AbstractActivity implements HasComponent<TravelComponent>, TravelDetailsFragment.TravelDetailsListener {

    private static final String INTENT_EXTRA_PARAM_TRAVEL_ID = "INTENT_PARAM_TRAVEL_ID";
    private static final String STATE_PARAM_TRAVEL_ID = "STATE_PARAM_TRAVEL_ID";

    private TravelComponent component;

    private long travelId;

    /**
     * Generate intent to open this activity.
     *
     * @param context  Source context.
     * @param travelId Travel identifier to view in detail.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context, final long travelId) {
        final Intent callingIntent = new Intent(context, TravelDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TRAVEL_ID, travelId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_details);

        // Set the support toolbar to show in activity
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        // Active home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Load travel identifier from parameters or saved state.
        if (savedInstanceState == null) {
            travelId = getIntent().getLongExtra(INTENT_EXTRA_PARAM_TRAVEL_ID, TravelModel.DEFAULT_ID);
            addFragment(R.id.fragment_detail, TravelDetailsFragment.newInstance(this.travelId));
        } else {
            travelId = savedInstanceState.getLong(STATE_PARAM_TRAVEL_ID);
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
            outState.putLong(STATE_PARAM_TRAVEL_ID, travelId);
        }
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void activityOpenTransition() {
        overridePendingTransition(R.anim.activity_open_to_left, R.anim.activity_close_to_right);
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

    @Override
    public void afterSaveTravel(final TravelModel model) {
        navigator.navigateToTravelPlaces(this,model); // FIXME Parameter with travel
    }
}
