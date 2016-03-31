package com.github.mytravelsapp.presentation;

import com.github.mytravelsapp.business.interactor.GetTravelInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.components.ApplicationComponent;
import com.github.mytravelsapp.presentation.di.modules.ApplicationModule;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;
import com.github.mytravelsapp.presentation.view.activity.TravelDetailsActivityTest;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by kisco on 09/02/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, TestComponent.MockModule.class})
public interface TestComponent extends ApplicationComponent {
    void inject(TravelDetailsActivityTest activityTest);

    @Module
    public class MockModule {
        @Provides
        @PerActivity
        TravelDetailPresenter provideTravelDetailPresenter(Navigator navigator, GetTravelInteractor mockGetTravelInteractor, SaveTravelInteractor mockSaveTravelInteractor, TravelModelConverter travelModelConverter) {
            return new TravelDetailPresenter(navigator, mockSaveTravelInteractor, mockGetTravelInteractor, travelModelConverter);
        }

        @Provides
        @Singleton
        GetTravelInteractor provideGetTravelInteractor() {
            return Mockito.mock(GetTravelInteractor.class);
        }

        @Provides
        @Singleton
        SaveTravelInteractor provideSaveTravelInteractor() {
            return Mockito.mock(SaveTravelInteractor.class);
        }
    }
}
