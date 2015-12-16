package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unit test for {@link GetTravelListInteractor}.
 *
 * @author fjtorres
 */
public class GetTravelListInteractorTest {

    private GetTravelListInteractor getTravelListInteractor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private TravelRepository mockTravelRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.getTravelListInteractor = new GetTravelListInteractor(mockThreadExecutor, mockPostExecutionThread, mockTravelRepository);
    }

    @Test
    public void backgroundTaskTest() throws Exception {
        getTravelListInteractor.backgroundTask();

        verify(mockTravelRepository).find(anyString());
        verifyNoMoreInteractions(mockTravelRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}