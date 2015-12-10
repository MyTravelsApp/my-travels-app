package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;

/**
 * Activity that shows a list of travels.
 *
 * @author fjtorres
 */
public class TravelListActivity extends AbstractActivity implements HasComponent<TravelComponent>, TravelListFragment.TravelListListener {

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
            case R.id.action_search_travel:
                // FIXME Search travel action
                result = false;
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
    public void onTravelClicked(final TravelModel model) {
        navigator.navigateToTravelPlaces(this);
    }

    @Override
    public void onAddTravelClicked() {
        navigator.navigateToTravelDetail(this, new TravelModel(TravelModel.DEFAULT_ID));
    }
}
