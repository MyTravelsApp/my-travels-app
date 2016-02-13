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
     * Render categories in the view.
     *
     * @param list Categories to render.
     */
    void renderList(List<CategoryModel> list);

    /**
     * Render travels in the view.
     *
     * @param categoryModel categorie to add to the list categories.
     */
    void addItemSaved(CategoryModel categoryModel);
}
