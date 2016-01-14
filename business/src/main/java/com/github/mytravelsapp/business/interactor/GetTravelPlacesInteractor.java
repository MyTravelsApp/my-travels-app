package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class GetTravelPlacesInteractor extends AbstractBackgroundInteractor<TravelPlacesDto> {

    private final TravelPlacesRepository travelPlacesRepository;

    private long travelPlacesId;

    @Inject
    public GetTravelPlacesInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public TravelPlacesDto backgroundTask() throws Exception {
        return travelPlacesRepository.findById(getTravelPlacesId());
    }

    public long getTravelPlacesId() {
        return travelPlacesId;
    }

    public void setTravelPlacesId(long travelPlacesId) {
        this.travelPlacesId = travelPlacesId;
    }
}
