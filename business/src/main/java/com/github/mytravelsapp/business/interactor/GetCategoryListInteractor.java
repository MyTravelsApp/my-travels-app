package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;
import com.github.mytravelsapp.business.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class GetCategoryListInteractor extends AbstractBackgroundInteractor<List<CategoryDto>> {

    private final CategoryRepository categoryRepository;

    private String filter;

    @Inject
    public GetCategoryListInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread, final CategoryRepository pCategoryRepository) {
        super(pThreadExecutor, pPostExecutionThread);
        this.categoryRepository = pCategoryRepository;
    }

    @Override
    public List<CategoryDto> backgroundTask() throws Exception {
        return categoryRepository.find(getFilter());
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
