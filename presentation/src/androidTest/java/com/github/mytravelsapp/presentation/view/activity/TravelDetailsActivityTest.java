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
public class TravelDetailsActivityTest extends ActivityInstrumentationTestCase2<TravelDetailsActivity> {

    private static final long FAKE_ID = 10;

    private TravelDetailsActivity activity;

    public TravelDetailsActivityTest() {
        super(TravelDetailsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(TravelDetailsActivity.getCallingIntent(getInstrumentation().getContext(), FAKE_ID));
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsDetailsFragment () {
        final Fragment travelListFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragment_detail);
        assertThat(travelListFragment, is(notNullValue()));
    }

    public void testContainsProperTitle () {
        final String actualTitle = activity.getTitle().toString().trim();
        final String expectedTitle = activity.getString(R.string.activity_travel_details_title).toString().trim();
        assertThat(actualTitle, is(expectedTitle));
    }

    public void testDisplayedViews () {

    }

    public void testDisplayedData () {

    }
}
