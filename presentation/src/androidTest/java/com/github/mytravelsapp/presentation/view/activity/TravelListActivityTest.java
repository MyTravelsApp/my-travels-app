package com.github.mytravelsapp.presentation.view.activity;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.github.mytravelsapp.R;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author fjtorres
 */
public class TravelListActivityTest extends ActivityInstrumentationTestCase2<TravelListActivity> {

    private TravelListActivity activity;

    public TravelListActivityTest() {
        super(TravelListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(TravelListActivity.getCallingIntent(getInstrumentation().getContext()));
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstainsListFragment () {
        final Fragment travelListFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragmentTravelList);
        assertThat(travelListFragment, is(notNullValue()));
    }

    public void testContainsProperTitle () {
        final String actualTitle = activity.getTitle().toString().trim();
        final String expectedTitle = activity.getString(R.string.activity_travel_list_title).toString().trim();
        assertThat(actualTitle, is(expectedTitle));
    }
}
