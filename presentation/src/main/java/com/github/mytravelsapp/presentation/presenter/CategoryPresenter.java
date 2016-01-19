package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetCategoryListInteractor;
import com.github.mytravelsapp.business.interactor.RemoveCategoryInteractor;
import com.github.mytravelsapp.presentation.converter.CategoryModelConverter;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.CategoryView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by stefani on 14/01/2016.
 */
@PerActivity
public class CategoryPresenter extends AbstractPresenter<CategoryView> {

    private GetCategoryListInteractor getCategoryListInteractor;

    private RemoveCategoryInteractor removeCategoryInteractor;

    private final CategoryModelConverter converter;

    @Inject
    public CategoryPresenter(final Navigator pNavigator, final GetCategoryListInteractor pGetCategoryListIntector, final RemoveCategoryInteractor pRemoveCategoryInteractor, final CategoryModelConverter pConverter) {
        super(pNavigator);
        this.getCategoryListInteractor = pGetCategoryListIntector;
        this.removeCategoryInteractor = pRemoveCategoryInteractor;
        this.converter = pConverter;
    }

    /**
     * Remove category
     */
    public void removeCategory(final long categoryId) {
        removeCategoryInteractor.setCategoryId(categoryId);
        removeCategoryInteractor.execute(new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {

            }

            @Override
            public void onError(Throwable cause) {
                // FIXME SHOW ERROR!!!!
            }
        });
    }

    /**
     * Load categories and render in view.
     *
     * @param filter Text to filter.
     */
    public void loadCategories(final String filter) {
        getView().showLoading();
        getCategoryListInteractor.setFilter(filter);
        getCategoryListInteractor.execute(new Callback<List<CategoryDto>>() {
            @Override
            public void onSuccess(List<CategoryDto> result) {
                getView().hideLoading();
                getView().renderList(converter.convert(result));
            }

            @Override
            public void onError(final Throwable cause) {
                getView().hideLoading();
                // FIXME SHOW ERROR!!!!
            }
        });

    }

}
