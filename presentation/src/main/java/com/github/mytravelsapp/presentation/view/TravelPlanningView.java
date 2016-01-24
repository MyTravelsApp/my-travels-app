package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.Date;
import java.util.List;

/**
 * Definition of the travel organizer view.
 *
 * @author fjtorres
 */
public interface TravelPlanningView extends LoadDataView {

    /**
     * Show dates parameter to organize travel.

     * @param daysOfTravel Days of the travel.
     */
    void renderTravelDays(List<Date> daysOfTravel);

    TravelModel getCurrentModel();
}
