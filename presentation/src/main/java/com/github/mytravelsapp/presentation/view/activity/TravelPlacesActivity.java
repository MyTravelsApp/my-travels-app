package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesFragment;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesActivity extends AbstractActivity implements TravelPlacesFragment.TravelPlacesListener{

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

        //Para activar el dagger, de momento no lo tenemos activo.
        //initializeInjector();
    }

    /**
     * Method necessary for navigation of activity.
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, TravelPlacesActivity.class);
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
    public void onAddTravelPlacesClicked() {
        navigator.navigateToTravelPlacesDetail(this, new TravelPlacesModel(TravelPlacesModel.DEFAULT_ID));
    }
}

