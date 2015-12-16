package com.github.mytravelsapp.presentation.di.modules;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.interactor.GetTravelInteractor;
import com.github.mytravelsapp.business.interactor.GetTravelListInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.business.service.TravelService;
import com.github.mytravelsapp.business.service.impl.TravelServiceImpl;
import com.github.mytravelsapp.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author fjtorres
 */
@Module
public class TravelModule {

    @Provides
    @PerActivity
    TravelService provideTravelService(final TravelRepository repository) {
        return new TravelServiceImpl(repository);
    }

    @Provides
    @PerActivity
    GetTravelListInteractor provideGetTravelListInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        return new GetTravelListInteractor(pThreadExecutor, pPostExecutionThread, pTravelRepository);
    }

    @Provides
    @PerActivity
    GetTravelInteractor provideGetTravelInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        return new GetTravelInteractor(pThreadExecutor, pPostExecutionThread, pTravelRepository);
    }

    @Provides
    @PerActivity
    SaveTravelInteractor provideSaveTravelInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        return new SaveTravelInteractor(pThreadExecutor, pPostExecutionThread, pTravelRepository);
    }
}
