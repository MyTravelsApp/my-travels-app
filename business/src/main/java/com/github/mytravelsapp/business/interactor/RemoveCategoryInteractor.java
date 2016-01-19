package com.github.mytravelsapp.business.interactor;

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

    private long categoryId;

    @Inject
    public RemoveCategoryInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final CategoryRepository pCategoryRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.categoryRepository = pCategoryRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
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
