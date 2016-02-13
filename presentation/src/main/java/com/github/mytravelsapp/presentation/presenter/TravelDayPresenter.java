package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelDayView;

import javax.inject.Inject;

/**
 * Created by kisco on 11/02/2016.
 */
public class TravelDayPresenter extends AbstractPresenter<TravelDayView> {

    @Inject
    public TravelDayPresenter (final Navigator pNavigator) {
        super(pNavigator);
    }
}
