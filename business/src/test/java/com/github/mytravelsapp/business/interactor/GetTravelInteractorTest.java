package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unit test for {@link GetTravelInteractor}.
 *
 * @author fjtorres
 */
public class GetTravelInteractorTest {

    private GetTravelInteractor getTravelInteractor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private TravelRepository mockTravelRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.getTravelInteractor = new GetTravelInteractor(mockThreadExecutor, mockPostExecutionThread, mockTravelRepository);
    }

    @Test
    public void backgroundTaskTest() throws Exception {
        getTravelInteractor.backgroundTask();

        verify(mockTravelRepository).findById(anyLong());
        verifyNoMoreInteractions(mockTravelRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
