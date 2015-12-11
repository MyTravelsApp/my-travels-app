package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

/**
 * Definition of the travel detail view methods.
 *
 * @author stefani
 */
public interface TravelPlacesDetailsView extends View {

    /**
     * Render specific model in the view.
     *
     * @param model Model to render.
     */
    void renderModel(TravelPlacesModel model);
}
