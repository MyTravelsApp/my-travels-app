package com.github.mytravelsapp.presentation.view.activity;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.github.mytravelsapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author fjtorres
 */
@RunWith(AndroidJUnit4.class)
public class TravelListActivityTest extends ActivityInstrumentationTestCase2<TravelListActivity> {

    private TravelListActivity activity;

    public TravelListActivityTest() {
        super(TravelListActivity.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        this.setActivityIntent(TravelListActivity.getCallingIntent(getInstrumentation().getContext()));
        activity = getActivity();
    }

    @Override
    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testConstainsListFragment () {
        final Fragment travelListFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragmentTravelList);
        assertThat(travelListFragment, is(notNullValue()));
    }

    @Test
    public void testContainsProperTitle () {
        final String actualTitle = activity.getTitle().toString().trim();
        final String expectedTitle = activity.getString(R.string.activity_travel_list_title).trim();
        assertThat(actualTitle, is(expectedTitle));
    }
}
