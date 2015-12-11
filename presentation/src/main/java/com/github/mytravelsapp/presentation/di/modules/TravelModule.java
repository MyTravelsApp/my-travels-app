package com.github.mytravelsapp.presentation.di.modules;

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
    public TravelService provideTravelService(final TravelRepository repository) {
        return new TravelServiceImpl(repository);
    }
}
