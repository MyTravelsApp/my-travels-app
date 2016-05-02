package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlanningView;
import com.github.mytravelsapp.presentation.view.activity.TravelNavigationListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by kisco on 24/01/2016.
 */
public class TravelPlanningPresenter extends AbstractPresenter<TravelPlanningView> {

    @Inject
    public TravelPlanningPresenter(final Navigator pNavigator) {
        super(pNavigator);
    }

    public void loadDaysOfTravel() {

        final TravelModel model = getView().getCurrentModel();

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(model.getStartDate());

        List<Date> travelDays = new ArrayList<>();

        while (calendar.getTime().before(model.getFinishDate()) || calendar.getTime().equals(model.getFinishDate())) {
            travelDays.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        getView().renderTravelDays(travelDays);
    }

    public void selectedDate(final TravelModel model, final Date selectedDate) {
        getNavigator().navigateToTravelDay(getView().getViewContext(), model, selectedDate);
    }
}
