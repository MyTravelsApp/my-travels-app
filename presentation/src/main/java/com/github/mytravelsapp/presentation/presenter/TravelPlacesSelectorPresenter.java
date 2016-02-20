package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesListInteractor;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesSelectorView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelPlacesSelectorPresenter extends  AbstractPresenter<TravelPlacesSelectorView> {

    private final TravelPlacesModelConverter converter;
    private final GetTravelPlacesListInteractor getTravelPlacesListInteractor;

    @Inject
    public TravelPlacesSelectorPresenter (final Navigator pNavigator, final GetTravelPlacesListInteractor pGetTravelPlacesListInteractor, final TravelPlacesModelConverter pConverter) {
        super(pNavigator);
        this.getTravelPlacesListInteractor = pGetTravelPlacesListInteractor;
        this.converter = pConverter;
    }

    public void loadPlaces () {
        final TravelModel model = getView().getCurrentModel();
        getView().showLoading();
        getTravelPlacesListInteractor.setTravelId(model.getId());
        getTravelPlacesListInteractor.execute(new Callback<List<TravelPlacesDto>>() {
            @Override
            public void onSuccess(List<TravelPlacesDto> result) {
                getView().hideLoading();
                getView().renderList(converter.convert(result));
            }

            @Override
            public void onError(final Throwable cause) {
                getView().hideLoading();
                getView().showLoadError();
            }
        });
    }
}
