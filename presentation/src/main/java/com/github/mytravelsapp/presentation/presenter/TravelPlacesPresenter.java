package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import javax.inject.Inject;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesPresenter extends AbstractPresenter<TravelPlacesView> {
    @Inject
    public TravelPlacesPresenter() {

    }
    /**
     * Navigate to the selected travel detail view.
     *
     * @param selectedModel Selected travel.
     */
    public void viewDetail(final TravelPlacesModel selectedModel) {
        getView().viewDetail(selectedModel);
    }

    /**
     * Navigate to new travel view.
     */
    public void newTravelPlaces() {
        getView().newTravelPlaces();
    }
}
