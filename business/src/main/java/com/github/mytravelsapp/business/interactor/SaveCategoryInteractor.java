package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.CategoryRepository;

import javax.inject.Inject;

/**
 * Created by stefani on 19/01/2015.
 */
public class SaveCategoryInteractor extends AbstractBackgroundInteractor<Boolean> {

    private final CategoryRepository categoryRepository;

    private CategoryDto data;

    @Inject
    public SaveCategoryInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final CategoryRepository pCategoryRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.categoryRepository = pCategoryRepository;
    }

    @Override
    public Boolean backgroundTask() throws Exception {
        categoryRepository.save(getData());
        return Boolean.TRUE;
    }

    public CategoryDto getData() {
        return data;
    }

    public void setData(CategoryDto model) {
        this.data = model;
    }
}