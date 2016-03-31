package com.github.mytravelsapp.presentation;

import com.github.mytravelsapp.presentation.AndroidApplication;
import com.github.mytravelsapp.presentation.DaggerTestComponent;
import com.github.mytravelsapp.presentation.di.components.ApplicationComponent;
import com.github.mytravelsapp.presentation.di.modules.ApplicationModule;

/**
 * Created by kisco on 09/02/2016.
 */
public class MockAndroidApplication extends AndroidApplication {

    @Override
    protected ApplicationComponent initializeInjector() {
        return DaggerTestComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
}
