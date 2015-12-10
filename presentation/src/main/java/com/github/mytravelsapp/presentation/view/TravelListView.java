package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.List;

/**
 * Definition of the travel list view methods.
 *
 * @author fjtorres
 */
public interface TravelListView extends View {

    /**
     * Render travels in the view.
     *
     * @param list Travels to render.
     */
    void renderList(List<TravelModel> list);

    /**
     * Navigate to the selected travel detail view.
     *
     * @param selectedModel Selected travel.
     */
    void viewDetail(TravelModel selectedModel);

    /**
     * Navigate to new travel view.
     */
    void newTravel();
}
