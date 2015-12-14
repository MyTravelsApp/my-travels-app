package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.service.TravelService;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel detail.
 *
 * @author fjtorres
 */
@PerActivity
public class TravelDetailPresenter extends AbstractPresenter<TravelDetailsView> {

    private final TravelService travelService;
    private final TravelModelConverter converter;

    @Inject
    public TravelDetailPresenter(final TravelService pTravelService, final TravelModelConverter pConverter) {
        this.travelService = pTravelService;
        this.converter = pConverter;
    }

    /**
     * Load model for specific identifier.
     *
     * @param travelId Travel identifier.
     */
    public void loadModel(final long travelId) {
        TravelModel model = null;
        if (travelId == TravelModel.DEFAULT_ID) {
            model = new TravelModel(TravelModel.DEFAULT_ID);
        } else {
            try {
                model = converter.convert(travelService.findById(travelId));
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
        getView().renderModel(model);
    }

    public void save() {
        if (getView().validate()) {
            final TravelModel model = getView().getCurrentModel();
            try {
                travelService.save(converter.convertToDto(model));
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            getView().renderTravelPlaces(model);
        }
    }
}
