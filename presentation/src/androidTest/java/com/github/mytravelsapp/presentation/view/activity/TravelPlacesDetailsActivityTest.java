package com.github.mytravelsapp.presentation.view.activity;

import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.TravelModel;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author stefani
 */
public class TravelPlacesDetailsActivityTest extends ActivityInstrumentationTestCase2<TravelPlacesDetailsActivity> {

    private static final long FAKE_ID = 10;

    private TravelPlacesDetailsActivity activity;

    @Mock
    private TravelModel travelModel;

    public TravelPlacesDetailsActivityTest() {
        super(TravelPlacesDetailsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        Mockito.when(travelModel.getId()).thenReturn(FAKE_ID);
        this.setActivityIntent(TravelDetailsActivity.getCallingIntent(getInstrumentation().getContext(), travelModel));
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsDetailsFragment () {
        final Fragment travelListFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragment_places_detail);
        assertThat(travelListFragment, is(notNullValue()));
    }

    public void testContainsProperTitle () {
        final String actualTitle = activity.getTitle().toString().trim();
        final String expectedTitle = activity.getString(R.string.activity_travel_places_details_title).trim();
        assertThat(actualTitle, is(expectedTitle));
    }

    public void testDisplayedViews () {

    }

    public void testDisplayedData () {

    }
}