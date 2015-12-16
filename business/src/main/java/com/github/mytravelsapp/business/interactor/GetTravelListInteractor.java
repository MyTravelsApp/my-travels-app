package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import java.util.List;

/**
 * @author fjtorres
 */
public class GetTravelListInteractor extends AbstractBackgroundInteractor<List<TravelDto>> {

    private final TravelRepository travelRepository;

    private String filter;

    public GetTravelListInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelRepository = pTravelRepository;
    }

    @Override
    public List<TravelDto> backgroundTask() throws Exception {
        return travelRepository.find(getFilter());
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
