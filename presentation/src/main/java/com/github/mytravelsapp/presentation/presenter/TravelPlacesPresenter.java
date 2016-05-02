package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
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
import com.github.mytravelsapp.presentation.view.activity.TravelNavigationListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

                if (!Utils.isEmpty(model.getDaysPlanningMap())) {


                    for (final Map.Entry<Date, List<TravelDayPlanningModel>> entry : model.getDaysPlanningMap().entrySet()) {
                        final List<TravelDayPlanningModel> toRemove = new ArrayList<>();

                        for (final TravelDayPlanningModel dayPlanningModel : entry.getValue()) {
                            if (dayPlanningModel != null && dayPlanningModel.getTravelPlaceId().equals(travelPlacesId)) {
                                toRemove.add(dayPlanningModel);
                            }
                        }

                        entry.getValue().removeAll(toRemove);
                    }
                }

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

}
