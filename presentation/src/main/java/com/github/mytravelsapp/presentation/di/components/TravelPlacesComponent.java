package com.github.mytravelsapp.presentation.di.components;

import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.di.modules.TravelModule;
import com.github.mytravelsapp.presentation.di.modules.TravelPlacesModule;
import com.github.mytravelsapp.presentation.view.fragment.TravelDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelPlacesFragment;

import dagger.Component;

/**
 * @author stefani
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TravelPlacesModule.class})
public interface TravelPlacesComponent extends ActivityComponent {
    void inject(TravelPlacesFragment fragment);

    void inject(TravelPlacesDetailsFragment fragment);
}
