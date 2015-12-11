package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel places detail.
 *
 * @author fjtorres
 */
public class TravelPlacesDetailPresenter extends AbstractPresenter<TravelPlacesDetailsView> {

    @Inject
    public TravelPlacesDetailPresenter() {

    }

    /**
     * Load model for specific identifier.
     *
     * @param travelId Travel identifier.
     */
    public void loadModel(final long travelId) {
        TravelPlacesModel model;
        if (travelId == TravelPlacesModel.DEFAULT_ID) {
            model = new TravelPlacesModel(TravelPlacesModel.DEFAULT_ID);
        } else {
            model = new TravelPlacesModel(1L);
            model.setPlace("Plaza españa");
        }
        getView().renderModel(model);
    }
}
