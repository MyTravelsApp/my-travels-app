package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.service.TravelPlacesService;
import com.github.mytravelsapp.business.service.TravelService;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel places detail.
 *
 * @author stefani
 */
public class TravelPlacesDetailPresenter extends AbstractPresenter<TravelPlacesDetailsView> {

    private final TravelPlacesService travelPlacesService;
    private final TravelPlacesModelConverter converter;

    @Inject
    public TravelPlacesDetailPresenter(final Navigator pNavigator, TravelPlacesService travelPlacesService, TravelPlacesModelConverter converter) {
        super(pNavigator);
        this.travelPlacesService = travelPlacesService;
        this.converter = converter;
    }

    /**
     * Load model for specific identifier.
     *
     * @param travelId Travel identifier.
     */
    public void loadModel(final long travelId) {
        TravelPlacesModel model;
        if (travelId == TravelPlacesModel.DEFAULT_ID) {
            model = new TravelPlacesModel();
        } else {
            model = new TravelPlacesModel();
            model.setTravelModel(new TravelModel(travelId));
        }
        getView().renderModel(model);
    }

    /**
     * Save model for specific identifier.
     */
    public void save() {
        if (getView().validate()) {
            final TravelPlacesModel model = getView().getCurrentModel();
            try {
                travelPlacesService.save(converter.convertToDto(model));
                getNavigator().navigateToTravelPlaces(getView().getViewContext(),model.getTravelModel());
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
    }
}
