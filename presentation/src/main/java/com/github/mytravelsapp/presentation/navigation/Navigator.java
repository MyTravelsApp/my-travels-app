package com.github.mytravelsapp.presentation.navigation;

import android.content.Context;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.view.activity.CategoryActivity;
import com.github.mytravelsapp.presentation.view.activity.TravelDayActivity;
import com.github.mytravelsapp.presentation.view.activity.TravelDetailsActivity;
import com.github.mytravelsapp.presentation.view.activity.TravelListActivity;
import com.github.mytravelsapp.presentation.view.activity.TravelPlacesActivity;
import com.github.mytravelsapp.presentation.view.activity.TravelPlacesDetailsActivity;
import com.github.mytravelsapp.presentation.view.activity.TravelPlanningActivity;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to provide navigation between activities.
 *
 * @author fjtorres
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {

    }

    /**
     * Navigate to {@link TravelListActivity}.
     *
     * @param context Source context.
     */
    public void navigateToTravelList(final Context context) {
        if (context != null) {
            context.startActivity(TravelListActivity.getCallingIntent(context));
        }
    }

    /**
     * Navigate to {@link TravelDetailsActivity}.
     *
     * @param context Source context.
     */
    public void navigateToTravelDetail(final Context context, final TravelModel model) {
        if (context != null) {
            context.startActivity(TravelDetailsActivity.getCallingIntent(context, model));
        }
    }

    /**
     * Navigate to {@link TravelPlanningActivity}.
     *
     * @param context Source context.
     */
    public void navigateToTravelPlanning(final Context context, final TravelModel model) {
        if (context != null) {
            context.startActivity(TravelPlanningActivity.getCallingIntent(context, model));
        }
    }

    /**
     * Navigate to {@link TravelPlanningActivity}.
     *
     * @param context Source context.
     */
    public void navigateToTravelDay (final Context context, final TravelModel model, final Date selectedDate) {
        if (context != null) {
            context.startActivity(TravelDayActivity.getCallingIntent(context, model, selectedDate));
        }
    }

    /**
     * Method to defined the navigation to the activity TravelPlaces
     */
    public void navigateToTravelPlaces(final Context context, TravelModel model){
        if (context != null) {
            context.startActivity(TravelPlacesActivity.getCallingIntent(context, model));
        }
    }

    /**
     * Navigate to {@link TravelPlacesDetailsActivity}.
     *
     * @param context Source context.
     */
    public void navigateToTravelPlacesDetail(final Context context, final TravelPlacesModel model) {
        if (context != null) {
            context.startActivity(TravelPlacesDetailsActivity.getCallingIntent(context, model));
        }
    }
    /**
     * Navigate to {@link TravelPlacesDetailsActivity}.
     *
     * @param context Source context.
     */
    public void navigateToCategory(final Context context) {
        if (context != null) {
            context.startActivity(CategoryActivity.getCallingIntent(context));
        }
    }

}
