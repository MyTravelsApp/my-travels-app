package com.github.mytravelsapp.presentation.di.modules;

import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.business.service.TravelPlacesService;
import com.github.mytravelsapp.business.service.TravelService;
import com.github.mytravelsapp.business.service.impl.TravelPlacesServiceImpl;
import com.github.mytravelsapp.business.service.impl.TravelServiceImpl;
import com.github.mytravelsapp.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author stefani
 */
@Module
public class TravelPlacesModule {

    @Provides
    @PerActivity
    public TravelPlacesService provideTravelPlacesService(final TravelPlacesRepository repository) {
        return new TravelPlacesServiceImpl(repository);
    }
}
