package com.github.mytravelsapp.presentation.view.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.business.dto.TravelDestinationDto;
import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.interactor.GetTravelInteractor;
import com.github.mytravelsapp.presentation.AndroidApplication;
import com.github.mytravelsapp.presentation.TestComponent;
import com.github.mytravelsapp.presentation.model.TravelDestinationModel;
import com.github.mytravelsapp.presentation.model.TravelModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author fjtorres
 */
@RunWith(AndroidJUnit4.class)
public class TravelDetailsActivityTest {

    @Rule
    public ActivityTestRule<TravelDetailsActivity> mActivityRule =
            new ActivityTestRule<>(TravelDetailsActivity.class, false, false);

    @Inject
    GetTravelInteractor mockGetTravelInteractor;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AndroidApplication app
                = (AndroidApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) app.getApplicationComponent();
        component.inject(this);
    }

    @Test
    public void newTravelTest() {
        final Intent intent = new Intent();
        intent.putExtra(TravelDetailsActivity.INTENT_EXTRA_PARAM_TRAVEL_MODEL, new TravelModel(1L));
        mActivityRule.launchActivity(intent);
        checkFields();
    }

    @Test
    public void travelDetailTest() throws Exception {
        final Date mockDate = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        final TravelModel mockData = new TravelModel(1L);
        mockData.setName("TEST");
        mockData.setFinishDate(mockDate);
        mockData.setStartDate(mockDate);
        mockData.setDestination(new TravelDestinationModel("TEST", "", 0d, 0d));

        final TravelDto mockDto = new TravelDto();
        mockDto.setDestination(new TravelDestinationDto(mockData.getDestination().getDestinationPlaceId(), mockData.getDestination().getDestinationPlaceName(), mockData.getDestination().getDestinationPlaceLatitude(), mockData.getDestination().getDestinationPlaceLongitude()));
        mockDto.setId(mockData.getId());
        mockDto.setFinishDate(mockData.getFinishDate());
        mockDto.setStartDate(mockData.getStartDate());
        mockDto.setName(mockData.getName());

        Mockito.when(mockGetTravelInteractor.backgroundTask()).thenReturn(mockDto);

        final Intent intent = new Intent();
        intent.putExtra(TravelDetailsActivity.INTENT_EXTRA_PARAM_TRAVEL_MODEL, mockData);
        mActivityRule.launchActivity(intent);
        checkFields();

        onView(withId(R.id.txt_name)).check(matches(withText(mockData.getName())));
        onView(withId(R.id.txt_destination)).check(matches(withText(mockData.getDestination().getDestinationPlaceName())));
        onView(withId(R.id.txt_start_date)).check(matches(withText(format.format(mockDate))));
        onView(withId(R.id.txt_finish_date)).check(matches(withText(format.format(mockDate))));
    }

    private void checkFields() {
        onView(withId(R.id.fragment_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_name)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_destination)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_start_date)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_start_date)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_finish_date)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_finish_date)).check(matches(isDisplayed()));
    }
}
