package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.List;

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
     * Render specific model in the view.
     *
     * @param categories List to render.
     */
    void renderCategories(List<CategoryModel> categories);

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
