package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unit test for {@link SaveTravelInteractor}.
 *
 * @author fjtorres
 */
public class SaveTravelInteractorTest {

    private SaveTravelInteractor saveTravelInteractor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private TravelRepository mockTravelRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.saveTravelInteractor = new SaveTravelInteractor(mockThreadExecutor, mockPostExecutionThread, mockTravelRepository);
    }

    @Test
    public void backgroundTaskTest() throws Exception {
        saveTravelInteractor.backgroundTask();

        verify(mockTravelRepository).save(any(TravelDto.class));
        verifyNoMoreInteractions(mockTravelRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}