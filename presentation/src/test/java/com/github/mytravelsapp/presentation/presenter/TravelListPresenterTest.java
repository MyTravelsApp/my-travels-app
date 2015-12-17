package com.github.mytravelsapp.presentation.presenter;

import android.content.Context;

import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelListInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link TravelListPresenter}.
 *
 * @author fjtorres
 */
public class TravelListPresenterTest {

    private TravelListPresenter presenter;

    @Mock
    private Navigator mockNavigator;

    @Mock
    private GetTravelListInteractor mockGetTravelListInteractor;

    @Mock
    private TravelModelConverter mockTravelModelConverter;

    @Mock
    private TravelListView mockTravelListView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.presenter = new TravelListPresenter(mockNavigator, mockGetTravelListInteractor, mockTravelModelConverter);
        this.presenter.setView(mockTravelListView);
    }

    @Test
    public void testLoadTravels() {
        presenter.loadTravels("");

        verify(mockTravelListView).showLoading();
        verify(mockGetTravelListInteractor).execute(any(Callback.class));
    }

    @Test
    public void testNewTravel() {
        this.presenter.newTravel();
        verify(mockNavigator).navigateToTravelDetail(any(Context.class), any(TravelModel.class));
    }

    @Test
    public void testTravelPlaces() {
        this.presenter.viewTravelPlaces(null);
        verify(mockNavigator).navigateToTravelPlaces(any(Context.class), any(TravelModel.class));
    }
}
