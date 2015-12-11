package com.github.mytravelsapp.presentation.di.components;

import android.content.Context;

import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.presentation.di.modules.ApplicationModule;
import com.github.mytravelsapp.presentation.view.activity.AbstractActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author fjtorres
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AbstractActivity activity);

    Context context();
    TravelRepository travelRepository();
}
