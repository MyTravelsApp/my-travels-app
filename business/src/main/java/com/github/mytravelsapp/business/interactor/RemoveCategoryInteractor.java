package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.exception.ExistCategoryInTravelPlacesBusinessException;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.CategoryRepository;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;

import javax.inject.Inject;

/**
 * Background task to remove category.
 */
public class RemoveCategoryInteractor extends AbstractBackgroundInteractor<Boolean> {

    private final CategoryRepository categoryRepository;

    private final TravelPlacesRepository travelPlacesRepository;

    private long categoryId;

    @Inject
    public RemoveCategoryInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final CategoryRepository pCategoryRepository, final TravelPlacesRepository pTravelPlacesRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.categoryRepository = pCategoryRepository;
        this.travelPlacesRepository = pTravelPlacesRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
       if(!travelPlacesRepository.findByIdCategory(getCategoryId()).isEmpty()){
           throw new ExistCategoryInTravelPlacesBusinessException("Category with travel places associated!");
       }
        categoryRepository.remove(getCategoryId());
        return Boolean.TRUE;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
