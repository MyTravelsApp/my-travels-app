package com.github.mytravelsapp.presentation.di.modules;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.interactor.GetTravelInteractor;
import com.github.mytravelsapp.business.interactor.GetTravelListInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stefani on 04/04/2016.
 */
@Module
public class MainModule {

}
