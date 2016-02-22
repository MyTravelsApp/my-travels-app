package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class GetTravelPlacesByCategoryInteractor extends AbstractBackgroundInteractor<List<TravelPlacesDto>> {

    private final TravelPlacesRepository travelPlacesRepository;

    private long idCategory;

    @Inject
    public GetTravelPlacesByCategoryInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public List<TravelPlacesDto> backgroundTask() throws  PersistenceException {
        return travelPlacesRepository.findByIdCategory(idCategory);
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }
}
