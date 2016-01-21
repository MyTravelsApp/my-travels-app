package com.github.mytravelsapp.presentation.presenter;

import android.content.Context;

import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesListInteractor;
import com.github.mytravelsapp.business.interactor.RemoveTravelPlacesInteractor;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link TravelPlacesPresenter}.
 *
 * @author stefani
 */
public class TravelPlacesPresenterTest {

    private TravelPlacesPresenter presenter;

    @Mock
    private Navigator mockNavigator;

    @Mock
    private GetTravelPlacesListInteractor mockGetTravelPlacesListInteractor;

    @Mock
    private RemoveTravelPlacesInteractor mockRemoveTravelPlacesInteractor;

    @Mock
    private TravelPlacesModelConverter mockTravelPlacesModelConverter;

    @Mock
    private TravelPlacesView mockTravelPlacesView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.presenter = new TravelPlacesPresenter(mockNavigator, mockRemoveTravelPlacesInteractor, mockGetTravelPlacesListInteractor , mockTravelPlacesModelConverter);
        this.presenter.setView(mockTravelPlacesView);
    }

    @Test
    public void testLoadTravelsPlaces() {
        presenter.searchTravelsPlaces(null,0L);
        verify(mockTravelPlacesView).showLoading();
        verify(mockGetTravelPlacesListInteractor).execute(any(Callback.class));
    }

    @Test
    public void testNewTravelPlaces() {
        this.presenter.newTravelPlaces();
        verify(mockNavigator).navigateToTravelDetail(any(Context.class), any(TravelModel.class));
    }


    @Test
    public void testRemoveTravelPlaces () {
        this.presenter.removeTravelPlaces(0L);
        verify(mockRemoveTravelPlacesInteractor).execute(any(Callback.class));
    }
}
