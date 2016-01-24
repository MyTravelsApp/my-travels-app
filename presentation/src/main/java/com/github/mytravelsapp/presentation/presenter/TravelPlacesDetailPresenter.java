package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetCategoryListInteractor;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelPlacesInteractor;
import com.github.mytravelsapp.presentation.converter.CategoryModelConverter;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel places detail.
 *
 * @author stefani
 */
@PerActivity
public class TravelPlacesDetailPresenter extends AbstractPresenter<TravelPlacesDetailsView> {

    private final TravelPlacesModelConverter converterTravelPlacesModel;
    private final CategoryModelConverter converterCategoryModel;
    private final SaveTravelPlacesInteractor saveTravelPlacesInteractor;
    private final GetTravelPlacesInteractor getTravelPlacesInteractor;
    private final GetCategoryListInteractor getCategoryListInteractor;


    @Inject
    public TravelPlacesDetailPresenter(final Navigator pNavigator, final SaveTravelPlacesInteractor pSaveTravelPlacesInteractor, final GetTravelPlacesInteractor pGetTravelPlacesInteractor, final GetCategoryListInteractor pGetCategoryListInteractor, final TravelPlacesModelConverter pConverterTravelPlacesModel, final CategoryModelConverter pConverterCategoryModel) {
        super(pNavigator);
        this.saveTravelPlacesInteractor = pSaveTravelPlacesInteractor;
        this.getTravelPlacesInteractor = pGetTravelPlacesInteractor;
        this.getCategoryListInteractor = pGetCategoryListInteractor;
        this.converterTravelPlacesModel = pConverterTravelPlacesModel;
        this.converterCategoryModel = pConverterCategoryModel;
    }

    /**
     * Load model for specific identifier.
     *
     * @param travelPlacesId Travel identifier.
     */
    public void loadModel(final long travelPlacesId) {
        getView().showLoading();
        getTravelPlacesInteractor.setTravelPlacesId(travelPlacesId);
        getTravelPlacesInteractor.execute(new Callback<TravelPlacesDto>() {
            @Override
            public void onSuccess(TravelPlacesDto result) {
                getView().hideLoading();
                getView().renderModel(converterTravelPlacesModel.convert(result));
            }

            @Override
            public void onError(Throwable cause) {
                getView().hideLoading();
                // FIXME SHOW ERROR!!!!
            }
        });
    }

    /**
     * Return the list of categories.
     */
    public void loadCategories (){
        getCategoryListInteractor.execute(new Callback<List<CategoryDto>>() {
            @Override
            public void onSuccess(List<CategoryDto> result) {
                getView().hideLoading();
                getView().renderCategories(converterCategoryModel.convert(result));
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
            saveTravelPlacesInteractor.setData(converterTravelPlacesModel.convertToDto(currentModel));
            saveTravelPlacesInteractor.execute(new Callback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (Boolean.TRUE.equals(result)) {
                        getView().hideLoading();
                        getNavigator().navigateToTravelPlaces(getView().getViewContext(), currentModel); // FIXME Parameter with travel
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
