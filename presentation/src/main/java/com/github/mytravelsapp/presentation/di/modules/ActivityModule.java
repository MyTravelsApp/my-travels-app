package com.github.mytravelsapp.presentation.di.modules;

import android.app.Activity;

import com.github.mytravelsapp.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author fjtorres
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(final Activity pActivity) {
        this.activity = pActivity;
    }

    @Provides
    @PerActivity
    public Activity activity() {
        return this.activity;
    }
}
