package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import javax.inject.Inject;

/**
 * Created by stefani on 11/01/2016.
 */
public class SaveTravelPlacesInteractor extends AbstractBackgroundInteractor<Boolean> {

    private final TravelPlacesRepository travelPlacesRepository;

    private TravelPlacesDto data;

    @Inject
    public SaveTravelPlacesInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
        travelPlacesRepository.save(getData());
        return Boolean.TRUE;
    }

    public TravelPlacesDto getData() {
        return data;
    }

    public void setData(TravelPlacesDto model) {
        this.data = model;
    }
}
