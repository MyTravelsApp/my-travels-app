package com.github.mytravelsapp.presentation.view;

import android.view.*;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.List;

/**
 * Definition of the travel list view methods.
 * Created by stefani on 10/12/2015.
 */
public interface TravelPlacesView extends LoadDataView {

    /**
     * Render travels in the view.
     *
     * @param list Travels to render.
     */
    void renderList(List<TravelPlacesModel> list);

    /**
     * Return the current travel.
     *
     */
    TravelModel getCurrentTravel();
}
