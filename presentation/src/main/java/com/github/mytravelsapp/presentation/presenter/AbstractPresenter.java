package com.github.mytravelsapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.View;

/**
 * Abstract presenter with basic implementations of methods.
 *
 * @author fjtorres
 */
public abstract class AbstractPresenter<V extends View> implements Presenter<V> {

    private V view;

    private Navigator navigator;

    public AbstractPresenter (final Navigator pNavigator) {
        this.navigator = pNavigator;
    }

    @Override
    public void setView(@NonNull final V pView) {
        this.view = pView;
    }

    @Override
    public V getView() {
        return view;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
