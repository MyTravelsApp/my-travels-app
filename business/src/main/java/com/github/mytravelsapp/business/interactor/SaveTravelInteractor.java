package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelRepository;

import javax.inject.Inject;

/**
 * Created by kisco on 15/12/2015.
 */
public class SaveTravelInteractor extends AbstractBackgroundInteractor<Boolean> {

    private final TravelRepository travelRepository;

    private TravelDto data;

    @Inject
    public SaveTravelInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelRepository pTravelRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelRepository = pTravelRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
        travelRepository.save(getData());
        return Boolean.TRUE;
    }

    public TravelDto getData() {
        return data;
    }

    public void setData(TravelDto model) {
        this.data = model;
    }
}
