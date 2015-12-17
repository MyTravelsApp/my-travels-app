package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import javax.inject.Inject;

/**
 * Background task to remove travel.
 */
public class RemoveTravelInteractor extends AbstractBackgroundInteractor<Boolean> {

    private final TravelRepository travelRepository;

    private long travelId;

    @Inject
    public RemoveTravelInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelRepository = pTravelRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
        travelRepository.remove(getTravelId());
        return Boolean.TRUE;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }
}
