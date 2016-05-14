package com.github.mytravelsapp.presentation.di.components;

import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.di.modules.TravelModule;
import com.github.mytravelsapp.presentation.di.modules.TravelPlacesModule;
import com.github.mytravelsapp.presentation.view.fragment.TravelDayFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesSelectorFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlanningFragment;

import dagger.Component;

/**
 * @author stefani
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TravelPlacesModule.class, TravelModule.class})
public interface TravelPlacesComponent extends ActivityComponent {
    void inject(TravelPlacesFragment fragment);

    void inject(TravelPlacesDetailsFragment fragment);

    void inject(TravelPlanningFragment fragment);

    void inject(TravelDayFragment fragment);

    void inject(TravelPlacesSelectorFragment fragment);
}
