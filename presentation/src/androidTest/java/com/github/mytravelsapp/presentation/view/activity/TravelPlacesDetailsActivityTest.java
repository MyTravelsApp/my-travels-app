package com.github.mytravelsapp.presentation.view.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author stefani
 */
@RunWith(AndroidJUnit4.class)
public class TravelPlacesDetailsActivityTest{

    @Rule
    public ActivityTestRule<TravelPlacesDetailsActivity> mActivityRule =
            new ActivityTestRule<>(TravelPlacesDetailsActivity.class);


    @Test
    public void containsFragmentTest() {
        final Intent intent = TravelPlacesDetailsActivity.getCallingIntent(mActivityRule.getActivity(), new TravelPlacesModel(new TravelModel(TravelModel.DEFAULT_ID)));
        mActivityRule.launchActivity(intent);
        onView(withId(R.id.fragment_places_detail)).check(matches(isDisplayed()));
    }
}
