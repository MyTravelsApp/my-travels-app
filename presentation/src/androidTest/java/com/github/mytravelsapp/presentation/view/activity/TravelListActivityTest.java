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
 * @author fjtorres
 */
@RunWith(AndroidJUnit4.class)
public class TravelListActivityTest {

    @Rule
    public ActivityTestRule<TravelListActivity> mActivityRule =
            new ActivityTestRule<>(TravelListActivity.class);

    @Test
    public void containsFragmentTest() {
        onView(withId(R.id.fragmentTravelList)).check(matches(isDisplayed()));
    }
}
