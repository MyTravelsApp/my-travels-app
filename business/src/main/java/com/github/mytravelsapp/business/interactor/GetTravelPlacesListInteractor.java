package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class GetTravelPlacesListInteractor extends AbstractBackgroundInteractor<List<TravelPlacesDto>> {

    private final TravelPlacesRepository travelPlacesRepository;

    private String filter;
    private long travelId;

    @Inject
    public GetTravelPlacesListInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public List<TravelPlacesDto> backgroundTask() throws Exception {
        return travelPlacesRepository.find(getFilter(), travelId);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }
}
