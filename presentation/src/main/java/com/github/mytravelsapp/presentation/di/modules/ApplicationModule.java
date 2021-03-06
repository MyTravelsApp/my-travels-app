package com.github.mytravelsapp.presentation.di.modules;

import android.content.Context;

import com.github.mytravelsapp.business.executor.JobExecutor;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.CategoryRepository;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.repository.TravelRepository;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.github.mytravelsapp.persistence.repository.DatabaseCategoryRepository;
import com.github.mytravelsapp.persistence.repository.DatabaseTravelPlacesRepository;
import com.github.mytravelsapp.persistence.repository.DatabaseTravelRepository;
import com.github.mytravelsapp.presentation.AndroidApplication;
import com.github.mytravelsapp.presentation.UiThread;
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

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.androidApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UiThread uiThread){
        return uiThread;
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(final Context context) {
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    TravelRepository provideTravelRepository(final DatabaseTravelRepository travelRepository) {
        return travelRepository;
    }

    @Provides
    @Singleton
    TravelPlacesRepository provideTravelPlacesRepository(final DatabaseTravelPlacesRepository travelPlacesRepository) {
        return travelPlacesRepository;
    }

    @Provides
    @Singleton
    CategoryRepository provideCategoryRepository(final DatabaseCategoryRepository categoryRepository) {
        return categoryRepository;
    }
}
