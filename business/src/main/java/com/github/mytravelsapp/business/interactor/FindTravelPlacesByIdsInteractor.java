package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class FindTravelPlacesByIdsInteractor extends AbstractBackgroundInteractor<List<TravelPlacesDto>> {

    private final TravelPlacesRepository travelPlacesRepository;

    private List<Long> travelPlaceIds;

    @Inject
    public FindTravelPlacesByIdsInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public List<TravelPlacesDto> backgroundTask() throws Exception {
        return travelPlacesRepository.findByIds(travelPlaceIds);
    }


    public List<Long> getTravelPlaceIds() {
        return travelPlaceIds;
    }

    public void setTravelPlaceIds(List<Long> travelPlaceIds) {
        this.travelPlaceIds = travelPlaceIds;
    }
}
