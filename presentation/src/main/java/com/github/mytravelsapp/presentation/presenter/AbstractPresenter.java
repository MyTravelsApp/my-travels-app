package com.github.mytravelsapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.github.mytravelsapp.presentation.view.View;

/**
 * Abstract presenter with basic implementations of methods.
 *
 * @author fjtorres
 */
public abstract class AbstractPresenter<V extends View> implements Presenter<V> {

    private V view;

    @Override
    public void setView(@NonNull final V pView) {
        this.view = pView;
    }

    @Override
    public V getView() {
        return view;
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
