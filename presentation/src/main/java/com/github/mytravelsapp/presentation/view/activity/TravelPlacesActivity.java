package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesActivity extends AbstractActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout definition for activity
        setContentView(R.layout.activity_travel_places);

        // Set the support toolbar to show in activity
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        //Para activar el dagger, de momento no lo tenemos activo.
        //initializeInjector();
    }

    /**
     * Method necessary for navigation of activity.
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, TravelPlacesActivity.class);
    }
}
