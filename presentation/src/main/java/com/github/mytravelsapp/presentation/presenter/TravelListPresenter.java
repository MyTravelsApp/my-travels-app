package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelListInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelModel;
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
    private final TravelModelConverter converter;

    @Inject
    public TravelListPresenter(final Navigator pNavigator, final GetTravelListInteractor pGetTravelListInteractor, final TravelModelConverter pConverter) {
        super(pNavigator);
        this.getTravelListInteractor = pGetTravelListInteractor;
        this.converter = pConverter;
    }

    /**
     * Load travels and render in view.
     */
    public void loadTravels() {
        getView().showLoading();
        getTravelListInteractor.setFilter(null);
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
     * Navigate to the selected travel detail view.
     *
     * @param selectedModel Selected travel.
     */
    public void viewDetail(final TravelModel selectedModel) {
        getNavigator().navigateToTravelDetail(getView().getViewContext(), selectedModel);
    }

    /**
     * Navigate to new travel view.
     */
    public void newTravel() {
        getNavigator().navigateToTravelDetail(getView().getViewContext(), new TravelModel(TravelModel.DEFAULT_ID));
    }
}
