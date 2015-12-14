package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.service.TravelService;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.TravelListView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travels list.
 *
 * @author fjtorres
 */
@PerActivity
public class TravelListPresenter extends AbstractPresenter<TravelListView> {

    private final TravelService travelService;
    private final TravelModelConverter converter;

    @Inject
    public TravelListPresenter(final TravelService pTravelService, final TravelModelConverter pConverter) {
        this.travelService = pTravelService;
        this.converter = pConverter;
    }

    /**
     * Load travels and render in view.
     */
    public void loadTravels() {
        List<TravelDto> result = Collections.emptyList();
        try {
            result = travelService.find(null);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        getView().renderList(converter.convert(result));
    }

    /**
     * Navigate to the selected travel detail view.
     *
     * @param selectedModel Selected travel.
     */
    public void viewDetail(final TravelModel selectedModel) {
        getView().viewDetail(selectedModel);
    }

    /**
     * Navigate to new travel view.
     */
    public void newTravel() {
        getView().newTravel();
    }
}
