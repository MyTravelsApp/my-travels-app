package com.github.mytravelsapp.presentation.di.components;

import android.content.Context;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.presentation.di.modules.ApplicationModule;
import com.github.mytravelsapp.presentation.navigation.Navigator;
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

    Navigator navigator();
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    TravelRepository travelRepository();
    TravelPlacesRepository travelPlacesRepository();
}
