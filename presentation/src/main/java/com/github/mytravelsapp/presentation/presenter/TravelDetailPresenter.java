package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel detail.
 *
 * @author fjtorres
 */
@PerActivity
public class TravelDetailPresenter extends AbstractPresenter<TravelDetailsView> {

    private final SaveTravelInteractor saveTravelInteractor;
    private final GetTravelInteractor getTravelInteractor;
    private final TravelModelConverter converter;

    @Inject
    public TravelDetailPresenter(final Navigator pNavigator, final SaveTravelInteractor pSaveTravelInteractor,
                                 final GetTravelInteractor pGetTravelInteractor, final TravelModelConverter pConverter) {
        super(pNavigator);
        this.saveTravelInteractor = pSaveTravelInteractor;
        this.getTravelInteractor = pGetTravelInteractor;
        this.converter = pConverter;
    }

    /**
     * Load model for specific identifier.
     *
     * @param travelId Travel identifier.
     */
    public void loadModel(final long travelId) {
        getView().showLoading();
        getTravelInteractor.setTravelId(travelId);
        getTravelInteractor.execute(new Callback<TravelDto>() {
            @Override
            public void onSuccess(TravelDto result) {
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

    public void save() {
        if (getView().validate()) {

            getView().showLoading();
            final TravelModel currentModel = getView().getCurrentModel();
            saveTravelInteractor.setData(converter.convertToDto(currentModel));
            saveTravelInteractor.execute(new Callback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (Boolean.TRUE.equals(result)) {
                        getView().hideLoading();
                        //FIXME saveTravelInteractor should be return the object
                        currentModel.setId(saveTravelInteractor.getData().getId());
                        getNavigator().navigateToTravelPlaces(getView().getViewContext(), currentModel);
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
