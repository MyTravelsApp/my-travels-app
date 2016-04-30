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
public class TravelDetailsActivity extends AbstractActivity implements HasComponent<TravelComponent> {

    public static final String INTENT_EXTRA_PARAM_TRAVEL_MODEL = "INTENT_PARAM_TRAVEL_MODEL";
    public static final String STATE_PARAM_TRAVEL_MODEL = "STATE_PARAM_TRAVEL_MODEL";

    private TravelComponent component;
    private TravelModel travelModel;

    /**
     * Generate intent to open this activity.
     *
     * @param context  Source context.
     * @param pTravelModel Travel identifier to view in detail.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context, final TravelModel pTravelModel) {
        final Intent callingIntent = new Intent(context, TravelDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_TRAVEL_MODEL, pTravelModel);
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
            travelModel = getIntent().getParcelableExtra(INTENT_EXTRA_PARAM_TRAVEL_MODEL);
            replaceFragment(R.id.fragment_detail, TravelDetailsFragment.newInstance(travelModel));
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
            //outState.putLong(STATE_PARAM_TRAVEL_ID, travelId);
            outState.putParcelable(STATE_PARAM_TRAVEL_MODEL, travelModel);
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
                if (travelModel.getId() == TravelModel.DEFAULT_ID) {
                    navigator.navigateToTravelList(this);
                } else {
                    navigator.navigateToTravelPlaces(this, travelModel);
                }
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

}
