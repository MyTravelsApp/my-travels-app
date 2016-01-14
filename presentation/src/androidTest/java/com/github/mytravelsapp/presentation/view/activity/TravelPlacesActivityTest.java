package com.github.mytravelsapp.presentation.view.activity;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;

import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author stefani
 */
public class TravelPlacesActivityTest extends ActivityInstrumentationTestCase2<TravelPlacesActivity> {

    private TravelPlacesActivity activity;

    @Mock
    private TravelModel travelModel;

    private static final long FAKE_ID = 10;

    public TravelPlacesActivityTest() {
        super(TravelPlacesActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        travelModel = new TravelModel(FAKE_ID);
        this.setActivityIntent(TravelPlacesActivity.getCallingIntent(getInstrumentation().getContext(),travelModel));
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstainsListFragment () {
        final Fragment travelListFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragmentTravelPlaces);
        assertThat(travelListFragment, is(notNullValue()));
    }

    public void testContainsProperTitle () {
        final String actualTitle = activity.getTitle().toString().trim();
        final String expectedTitle = activity.getString(R.string.activity_travel_places_title).trim();
        assertThat(actualTitle, is(expectedTitle));
    }
}