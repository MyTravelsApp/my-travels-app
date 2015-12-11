package com.github.mytravelsapp.presentation.view;

import android.view.*;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

/**
 * Definition of the travel list view methods.
 * Created by stefani on 10/12/2015.
 */
public interface TravelPlacesView extends View {

    /**
     * Navigate to new travel places view.
     */
    void newTravelPlaces();

    /**
     * Navigate to the selected travel places detail view.
     *
     * @param selectedModel Selected travel places.
     */
    void viewDetail(TravelPlacesModel selectedModel);
}
