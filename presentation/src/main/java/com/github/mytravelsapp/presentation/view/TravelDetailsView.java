package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;

/**
 * Definition of the travel detail view methods.
 *
 * @author fjtorres
 */
public interface TravelDetailsView extends LoadDataView {

    /**
     * Render specific travel in the view.
     *
     * @param model Travel to render.
     */
    void renderModel(TravelModel model);

    /**
     * Return current travel fields in model.
     *
     * @return Model with current values.
     */
    TravelModel getCurrentModel();

    /**
     * Validate current model values.
     *
     * @return true if all values are correct otherwise false.
     */
    boolean validate();

}
