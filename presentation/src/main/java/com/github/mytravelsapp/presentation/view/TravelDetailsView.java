package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;

/**
 * Definition of the travel detail view methods.
 *
 * @author fjtorres
 */
public interface TravelDetailsView extends View {

    /**
     * Render specific model in the view.
     *
     * @param model Model to render.
     */
    void renderModel(TravelModel model);
}
