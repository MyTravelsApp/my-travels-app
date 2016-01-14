package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesListInteractor;
import com.github.mytravelsapp.business.interactor.RemoveTravelPlacesInteractor;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by stefani on 10/12/2015.
 */
@PerActivity
public class TravelPlacesPresenter extends AbstractPresenter<TravelPlacesView> {

    private final TravelPlacesModelConverter converter;
    private final RemoveTravelPlacesInteractor removeTravelPlacesInteractor;
    private final GetTravelPlacesListInteractor getTravelPlacesListInteractor;

    @Inject
    public TravelPlacesPresenter(final Navigator pNavigator, final RemoveTravelPlacesInteractor pRemoveTravelPlacesInteractor, final GetTravelPlacesListInteractor pGetTravelPlacesListInteractor, final TravelPlacesModelConverter converter) {
        super(pNavigator);
        this.removeTravelPlacesInteractor = pRemoveTravelPlacesInteractor;
        this.getTravelPlacesListInteractor = pGetTravelPlacesListInteractor;
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
     *
     * @param filter Text to filter.
     */
    public void searchTravelsPlaces(final String filter, final long travelId) {
        getView().showLoading();
        getTravelPlacesListInteractor.setFilter(filter);
        getTravelPlacesListInteractor.setTravelId(travelId);
        getTravelPlacesListInteractor.execute(new Callback<List<TravelPlacesDto>>() {
            @Override
            public void onSuccess(List<TravelPlacesDto> result) {
                getView().hideLoading();
                getView().renderList(converter.convert(result));
            }

            @Override
            public void onError(final Throwable cause) {
                getView().hideLoading();
                // FIXME SHOW ERROR!!!!
            }
        });
    }

    /**
     * Remove travel
     */
    public void removeTravelPlaces(final long travelPlacesId) {
        removeTravelPlacesInteractor.setTravelPlacesId(travelPlacesId);
        removeTravelPlacesInteractor.execute(new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {

            }

            @Override
            public void onError(Throwable cause) {
                // FIXME SHOW ERROR!!!!
            }
        });
    }

}
