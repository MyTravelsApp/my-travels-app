package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class GetTravelInteractor extends AbstractBackgroundInteractor<TravelDto> {

    private final TravelRepository travelRepository;

    private long travelId;

    @Inject
    public GetTravelInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelRepository = pTravelRepository;
    }

    @Override
    public TravelDto backgroundTask() throws Exception {
        return travelRepository.findById(getTravelId());
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public long getTravelId() {
        return travelId;
    }
}
