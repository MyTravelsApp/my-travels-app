package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelListInteractor;
import com.github.mytravelsapp.business.interactor.RemoveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travels list.
 *
 * @author fjtorres
 */
@PerActivity
public class TravelListPresenter extends AbstractPresenter<TravelListView> {

    private final GetTravelListInteractor getTravelListInteractor;
    private final RemoveTravelInteractor removeTravelInteractor;
    private final TravelModelConverter converter;

    @Inject
    public TravelListPresenter(final Navigator pNavigator, final GetTravelListInteractor pGetTravelListInteractor, final RemoveTravelInteractor pRemoveTravelInteractor, final TravelModelConverter pConverter) {
        super(pNavigator);
        this.getTravelListInteractor = pGetTravelListInteractor;
        this.removeTravelInteractor = pRemoveTravelInteractor;
        this.converter = pConverter;
    }

    /**
     * Load travels and render in view.
     *
     * @param filter Text to filter.
     */
    public void loadTravels(final String filter) {
        getView().showLoading();
        getTravelListInteractor.setFilter(filter);
        getTravelListInteractor.execute(new Callback<List<TravelDto>>() {
            @Override
            public void onSuccess(List<TravelDto> result) {
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
     * Navigate to the selected travel places detail view.
     *
     * @param selectedModel Selected travel.
     */
    public void viewTravelPlaces(final TravelModel selectedModel) {
        getNavigator().navigateToTravelPlaces(getView().getViewContext(),selectedModel);
    }

    /**
     * Navigate to new travel view.
     */
    public void newTravel() {
        getNavigator().navigateToTravelDetail(getView().getViewContext(), new TravelModel(TravelModel.DEFAULT_ID));
    }

    /**
     * Remove travel
     */
    public void removeTravel(final long travelId) {
        removeTravelInteractor.setTravelId(travelId);
        removeTravelInteractor.execute(new Callback<Boolean>() {
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
