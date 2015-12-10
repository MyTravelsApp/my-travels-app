package com.github.mytravelsapp.presentation;

import android.app.Application;

import com.github.mytravelsapp.presentation.di.components.ApplicationComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerApplicationComponent;
import com.github.mytravelsapp.presentation.di.modules.ApplicationModule;

/**
 * Android application for Dependency Injection (DI).
 *
 * @author fjtorres
 */
public class AndroidApplication extends Application {

    /**
     * Main application component (DI).
     */
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    /**
     * Initialize DI components.
     */
    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
