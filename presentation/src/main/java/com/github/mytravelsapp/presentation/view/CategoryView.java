package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.List;

/**
 * Definition of the travel list view methods.
 *
 * @author stefani
 */
public interface CategoryView extends LoadDataView {

    /**
     * Render travels in the view.
     *
     * @param list Travels to render.
     */
    void renderList(List<CategoryModel> list);
}
