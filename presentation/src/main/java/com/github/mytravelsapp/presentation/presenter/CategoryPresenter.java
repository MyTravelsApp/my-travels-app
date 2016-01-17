package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.interactor.GetCategoryListInteractor;
import com.github.mytravelsapp.presentation.di.PerActivity;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.CategoryView;

import javax.inject.Inject;

/**
 * Created by stefani on 14/01/2016.
 */
@PerActivity
public class CategoryPresenter extends AbstractPresenter<CategoryView> {

    private GetCategoryListInteractor getCategoryListInteractor;

    @Inject
    public CategoryPresenter(final Navigator pNavigator, final GetCategoryListInteractor pGetCategoryListIntector) {
        super(pNavigator);
        this.getCategoryListInteractor = pGetCategoryListIntector;
    }
}
