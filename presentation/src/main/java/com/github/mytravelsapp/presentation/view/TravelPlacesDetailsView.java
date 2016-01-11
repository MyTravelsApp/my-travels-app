package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

/**
 * Definition of the travel detail view methods.
 *
 * @author stefani
 */
public interface TravelPlacesDetailsView extends LoadDataView {

    /**
     * Render specific model in the view.
     *
     * @param model Model to render.
     */
    void renderModel(TravelPlacesModel model);

    /**
     * Validate current model values.
     *
     * @return true if all values are correct otherwise false.
     */
    boolean validate();

    /**
     * Return current travel places fields in model.
     *
     * @return Model with current values.
     */
    TravelPlacesModel getCurrentModel();

}
