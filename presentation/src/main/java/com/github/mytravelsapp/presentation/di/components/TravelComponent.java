package com.github.mytravelsapp.presentation.di.components;

import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.di.modules.TravelModule;
import com.github.mytravelsapp.presentation.view.fragment.TravelDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;

import dagger.Component;

/**
 * @author fjtorres
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TravelModule.class})
public interface TravelComponent {
    void inject(TravelListFragment fragment);
    void inject(TravelDetailsFragment fragment);
}
