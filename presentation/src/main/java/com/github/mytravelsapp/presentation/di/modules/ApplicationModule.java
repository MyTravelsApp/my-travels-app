package com.github.mytravelsapp.presentation.di.modules;

import com.github.mytravelsapp.presentation.AndroidApplication;
import com.github.mytravelsapp.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author fjtorres
 */
@Module
public class ApplicationModule {
    private final AndroidApplication androidApplication;

    public ApplicationModule(final AndroidApplication pAndroidApplication) {
        this.androidApplication = pAndroidApplication;
    }

    @Provides @Singleton Navigator provideNavigator() {
        return new Navigator();
    }
}
