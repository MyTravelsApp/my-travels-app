package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelPlacesInteractor;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link TravelPlacesDetailPresenter}.
 *
 * @author stefani
 */
public class TravelPlacesDetailsPresenterTest {

    private TravelPlacesDetailPresenter presenter;

    @Mock
    private Navigator mockNavigator;

    @Mock
    private GetTravelPlacesInteractor mockGetTravelPlacesInteractor;

    @Mock
    private SaveTravelPlacesInteractor mockSaveTravelPlacesInteractor;

    @Mock
    private TravelPlacesModelConverter mockTravelPlacesModelConverter;

    @Mock
    private TravelPlacesDetailsView mockTravelPlacesDetailsView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new TravelPlacesDetailPresenter(mockNavigator, mockSaveTravelPlacesInteractor, mockGetTravelPlacesInteractor, mockTravelPlacesModelConverter);
        presenter.setView(mockTravelPlacesDetailsView);
    }

    @Test
    public void testLoadModel() {
        presenter.loadModel(0L);
        verify(mockTravelPlacesDetailsView).showLoading();
        verify(mockGetTravelPlacesInteractor).execute(any(Callback.class));
    }

    @Test
    public void testSave() {
        when(mockTravelPlacesDetailsView.validate()).thenReturn(true);
        presenter.save();
        verify(mockTravelPlacesDetailsView).validate();
        verify(mockTravelPlacesDetailsView).showLoading();
        verify(mockSaveTravelPlacesInteractor).execute(any(Callback.class));
    }
}