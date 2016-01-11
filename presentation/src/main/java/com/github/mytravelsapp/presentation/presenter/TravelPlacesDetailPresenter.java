package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelPlacesInteractor;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel places detail.
 *
 * @author stefani
 */
@PerActivity
public class TravelPlacesDetailPresenter extends AbstractPresenter<TravelPlacesDetailsView> {

    private final TravelPlacesModelConverter converter;
    private final SaveTravelPlacesInteractor saveTravelPlacesInteractor;
    private final GetTravelPlacesInteractor getTravelPlacesInteractor;

    @Inject
    public TravelPlacesDetailPresenter(final Navigator pNavigator, final SaveTravelPlacesInteractor pSaveTravelPlacesInteractor, final GetTravelPlacesInteractor pGetTravelPlacesInteractor, final TravelPlacesModelConverter converter) {
        super(pNavigator);
        this.saveTravelPlacesInteractor = pSaveTravelPlacesInteractor;
        this.getTravelPlacesInteractor = pGetTravelPlacesInteractor;
        this.converter = converter;
    }

    /**
     * Load model for specific identifier.
     *
     * @param travelId Travel identifier.
     */
    public void loadModel(final long travelId) {
        getView().showLoading();
        getTravelPlacesInteractor.setTravelPlacesId(travelId);
        getTravelPlacesInteractor.execute(new Callback<TravelPlacesDto>() {
            @Override
            public void onSuccess(TravelPlacesDto result) {
                getView().hideLoading();
                getView().renderModel(converter.convert(result));
            }

            @Override
            public void onError(Throwable cause) {
                getView().hideLoading();
                // FIXME SHOW ERROR!!!!
            }
        });
    }

    /**
     * Save model for specific identifier.
     */
    public void save() {
        if (getView().validate()) {
            getView().showLoading();
            final TravelPlacesModel currentModel = getView().getCurrentModel();
            saveTravelPlacesInteractor.setData(converter.convertToDto(currentModel));
            saveTravelPlacesInteractor.execute(new Callback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (Boolean.TRUE.equals(result)) {
                        getView().hideLoading();
                        getNavigator().navigateToTravelPlaces(getView().getViewContext(), currentModel.getTravelModel()); // FIXME Parameter with travel
                    }
                }

                @Override
                public void onError(Throwable cause) {
                    getView().hideLoading();
                    // FIXME SHOW ERROR!!!!
                }
            });
        }
    }
}
