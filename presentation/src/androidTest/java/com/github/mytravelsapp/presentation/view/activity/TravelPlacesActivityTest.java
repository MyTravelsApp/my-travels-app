package com.github.mytravelsapp.presentation.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.mytravelsapp.R;

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
public class TravelPlacesActivityTest{

    @Rule
    public ActivityTestRule<TravelPlacesActivity> mActivityRule =
            new ActivityTestRule<>(TravelPlacesActivity.class);

    @Test
    public void containsFragmentTest() {
        onView(withId(R.id.fragmentTravelList)).check(matches(isDisplayed()));
    }
}