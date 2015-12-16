package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelListInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
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
    private GetTravelListInteractor mockGetTravelListInteractor;

    @Mock
    private TravelModelConverter mockTravelModelConverter;

    @Mock
    private TravelListView mockTravelListView;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        this.presenter = new TravelListPresenter(mockGetTravelListInteractor, mockTravelModelConverter);
        this.presenter.setView(mockTravelListView);
    }

    @Test
    public void testLoadTravels() {
        presenter.loadTravels();

        verify(mockTravelListView).showLoading();
        verify(mockGetTravelListInteractor).execute(any(Callback.class));
    }
}
