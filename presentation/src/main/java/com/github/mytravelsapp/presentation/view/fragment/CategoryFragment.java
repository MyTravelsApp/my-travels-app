package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;

import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.presenter.CategoryPresenter;
import com.github.mytravelsapp.presentation.view.CategoryView;

import java.util.List;

/**
 * Created by stefani on 14/01/2016.
 */
public class CategoryFragment extends AbstractFragment<CategoryView, CategoryPresenter> implements CategoryView {
    @Override
    protected CategoryPresenter getPresenter() {
        return null;
    }

    @Override
    public void renderList(List<CategoryModel> list) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getViewContext() {
        return null;
    }
}
