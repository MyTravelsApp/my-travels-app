package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link TravelDetailPresenter}.
 *
 * @author fjtorres
 */
public class TravelDetailsPresenterTest {

    private TravelDetailPresenter presenter;

    @Mock
    private Navigator mockNavigator;

    @Mock
    private GetTravelInteractor mockGetTravelInteractor;

    @Mock
    private SaveTravelInteractor mockSaveTravelInteractor;

    @Mock
    private TravelModelConverter mockTravelModelConverter;

    @Mock
    private TravelDetailsView mockTravelDetailsView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new TravelDetailPresenter(mockNavigator, mockSaveTravelInteractor, mockGetTravelInteractor, mockTravelModelConverter);
        presenter.setView(mockTravelDetailsView);
    }

    @Test
    public void testLoadModel() {
        presenter.loadModel(0L);
        verify(mockTravelDetailsView).showLoading();
        verify(mockGetTravelInteractor).execute(any(Callback.class));
    }

    @Test
    public void testSave() {
        when(mockTravelDetailsView.validate()).thenReturn(true);
        presenter.save();
        verify(mockTravelDetailsView).validate();
        verify(mockTravelDetailsView).showLoading();
        verify(mockSaveTravelInteractor).execute(any(Callback.class));
    }
}
