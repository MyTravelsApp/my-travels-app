package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.NewTravelPointView;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class NewTravelPointPresenter extends AbstractPresenter<NewTravelPointView> {

    @Inject
    public NewTravelPointPresenter (final Navigator pNavigator) {
        super(pNavigator);
    }
}
