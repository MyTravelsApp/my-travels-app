package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelDetailPresenter extends AbstractPresenter<TravelDetailsView> {

    @Inject
    public TravelDetailPresenter() {

    }

    public void loadModel(final long travelId) {
        TravelModel model = null;
        if (travelId == -1) {
            model = new TravelModel(-1);
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
