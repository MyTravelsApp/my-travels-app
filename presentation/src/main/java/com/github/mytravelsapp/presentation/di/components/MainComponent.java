package com.github.mytravelsapp.presentation.di.components;

import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.di.modules.MainModule;
import com.github.mytravelsapp.presentation.di.modules.TravelModule;
import com.github.mytravelsapp.presentation.view.fragment.CategoryFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;

import dagger.Component;

/**
 * Created by stefani on 04/04/2016.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, MainModule.class, TravelModule.class})
public interface MainComponent extends ActivityComponent{
   void inject(TravelListFragment fragment);
   void inject(CategoryFragment fragment);
}
