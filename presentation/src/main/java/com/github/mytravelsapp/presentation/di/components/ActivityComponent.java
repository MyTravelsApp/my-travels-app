package com.github.mytravelsapp.presentation.di.components;

import android.app.Activity;

import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.di.modules.ActivityModule;
import com.github.mytravelsapp.presentation.di.modules.ApplicationModule;

import dagger.Component;

/**
 * @author fjtorres
 */
@PerActivity
@Component(dependencies = ApplicationModule.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
