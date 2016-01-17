package com.github.mytravelsapp.presentation.di.components;

import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.di.modules.CategoryModule;
import com.github.mytravelsapp.presentation.di.modules.TravelModule;
import com.github.mytravelsapp.presentation.view.fragment.CategoryFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelDetailsFragment;
import com.github.mytravelsapp.presentation.view.fragment.TravelListFragment;

import dagger.Component;

/**
 * @author stefani
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CategoryModule.class})
public interface CategoryComponent extends ActivityComponent {
    void inject(CategoryFragment fragment);

}
