package com.github.mytravelsapp.presentation.view.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;

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
public class TravelDetailsActivityTest {

    @Rule
    public ActivityTestRule<TravelDetailsActivity> mActivityRule =
            new ActivityTestRule<>(TravelDetailsActivity.class);


    @Test
    public void containsFragmentTest() {
        final Intent intent = TravelDetailsActivity.getCallingIntent(mActivityRule.getActivity(), new TravelModel(1L));
        mActivityRule.launchActivity(intent);
        onView(withId(R.id.fragment_detail)).check(matches(isDisplayed()));
    }
}
