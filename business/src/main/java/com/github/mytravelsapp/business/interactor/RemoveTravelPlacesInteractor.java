package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import javax.inject.Inject;

/**
 * Background task to remove travel.
 */
public class RemoveTravelPlacesInteractor extends AbstractBackgroundInteractor<Boolean> {

    private final TravelPlacesRepository travelPlacesRepository;

    private long travelPlacesId;

    @Inject
    public RemoveTravelPlacesInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
        travelPlacesRepository.remove(getTravelPlacesId());
        return Boolean.TRUE;
    }

    public long getTravelPlacesId() {
        return travelPlacesId;
    }

    public void setTravelPlacesId(long travelPlacesId) {
        this.travelPlacesId = travelPlacesId;
    }
}
