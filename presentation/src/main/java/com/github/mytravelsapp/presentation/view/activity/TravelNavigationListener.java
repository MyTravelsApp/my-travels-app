package com.github.mytravelsapp.presentation.view.activity;

import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.Date;

/**
 * Created by stefani on 30/04/2016.
 */
public interface TravelNavigationListener {

    void openFragmentTravelDay(Date selectedDate);
    void openFragmentTravelPlanning();
}
