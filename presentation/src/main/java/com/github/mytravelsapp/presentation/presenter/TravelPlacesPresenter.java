package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.service.TravelPlacesService;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by stefani on 10/12/2015.
 */
@PerActivity
public class TravelPlacesPresenter extends AbstractPresenter<TravelPlacesView> {

    private final TravelPlacesService travelPlacesService;
    private final TravelPlacesModelConverter converter;

    @Inject
    public TravelPlacesPresenter(TravelPlacesService travelPlacesService, TravelPlacesModelConverter converter) {
        this.travelPlacesService = travelPlacesService;
        this.converter = converter;
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

    /**
     * Load travels and render in view.
     */
    public void loadTravelsPlaces() {
        List<TravelPlacesDto> result = Collections.emptyList();
        try {
            result = travelPlacesService.find(null);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        getView().renderList(converter.convert(result));
    }

}
