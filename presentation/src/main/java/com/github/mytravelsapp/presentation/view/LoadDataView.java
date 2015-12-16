package com.github.mytravelsapp.presentation.view;

/**
 * Interface representing a View that will use to load data.
 *
 * @author fjtorres
 */
public interface LoadDataView extends View {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();
}
