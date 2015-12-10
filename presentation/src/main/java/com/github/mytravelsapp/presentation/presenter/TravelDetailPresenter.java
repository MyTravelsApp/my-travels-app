package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Presenter that controls communication between views and models associated with travel detail.
 *
 * @author fjtorres
 */
public class TravelDetailPresenter extends AbstractPresenter<TravelDetailsView> {

    @Inject
    public TravelDetailPresenter() {

    }

    /**
     * Load model for specific identifier.
     *
     * @param travelId Travel identifier.
     */
    public void loadModel(final long travelId) {
        TravelModel model;
        if (travelId == TravelModel.DEFAULT_ID) {
            model = new TravelModel(TravelModel.DEFAULT_ID);
        } else {
            model = new TravelModel(1L);
            model.setName("Tres días a Roma");
            model.setDestination("Roma");
            Calendar calendar = Calendar.getInstance();
            calendar.set(2015, 10, 27);
            model.setStartDate(calendar.getTime());
            calendar.set(2015, 11, 2);
            model.setFinishDate(calendar.getTime());
        }
        getView().renderModel(model);
    }
}
