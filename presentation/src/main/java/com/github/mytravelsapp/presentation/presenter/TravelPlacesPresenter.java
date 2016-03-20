package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesListInteractor;
import com.github.mytravelsapp.business.interactor.RemoveTravelPlacesInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelDayPlanningModel;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by stefani on 10/12/2015.
 */
@PerActivity
public class TravelPlacesPresenter extends AbstractPresenter<TravelPlacesView> {

    private final TravelPlacesModelConverter converter;
    private final TravelModelConverter travelConverter;
    private final RemoveTravelPlacesInteractor removeTravelPlacesInteractor;
    private final GetTravelPlacesListInteractor getTravelPlacesListInteractor;
    private final SaveTravelInteractor saveTravelInteractor;

    @Inject
    public TravelPlacesPresenter(final Navigator pNavigator, final RemoveTravelPlacesInteractor pRemoveTravelPlacesInteractor, final GetTravelPlacesListInteractor pGetTravelPlacesListInteractor, final TravelPlacesModelConverter converter, final SaveTravelInteractor pSaveTravelInteractor, final TravelModelConverter pTravelConverter) {
        super(pNavigator);
        this.removeTravelPlacesInteractor = pRemoveTravelPlacesInteractor;
        this.getTravelPlacesListInteractor = pGetTravelPlacesListInteractor;
        this.converter = converter;
        this.saveTravelInteractor = pSaveTravelInteractor;
        this.travelConverter = pTravelConverter;
    }

    /**
     * Navigate to the selected travel detail view.
     *
     * @param selectedModel Selected travel.
     */
    public void viewDetail(final TravelPlacesModel selectedModel) {
        getNavigator().navigateToTravelPlacesDetail(getView().getViewContext(), selectedModel);
    }

    /**
     * Navigate to new travel view.
     */
    public void newTravelPlaces() {
        getNavigator().navigateToTravelPlacesDetail(getView().getViewContext(), new TravelPlacesModel(getView().getCurrentTravel()));
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
        getView().showLoading();
        // FIXME Esto deberia hacerlo un solo interactor.
        removeTravelPlacesInteractor.setTravelPlacesId(travelPlacesId);
        removeTravelPlacesInteractor.execute(new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                final TravelModel model = getView().getCurrentTravel();
                final List<TravelDayPlanningModel> toRemove = new ArrayList<>();
                for (final TravelDayPlanningModel item:model.getDaysPlanning()) {
                    if (item != null && item.getTravelPlaceId().equals(travelPlacesId)) {
                        toRemove.add(item);
                    }
                }

                model.getDaysPlanning().removeAll(toRemove);
                saveTravelInteractor.setData(travelConverter.convertToDto(model));
                saveTravelInteractor.execute(new Callback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable cause) {
                        // FIXME SHOW ERROR!!!!
                        getView().hideLoading();
                    }
                });
            }

            @Override
            public void onError(Throwable cause) {
                // FIXME SHOW ERROR!!!!
                getView().hideLoading();
            }
        });
    }

    public void planning() {
        getNavigator().navigateToTravelPlanning(getView().getViewContext(), getView().getCurrentTravel());
    }

}
