package com.github.mytravelsapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.github.mytravelsapp.presentation.view.View;

/**
 * Interface representing a Presenter in a Model View Presenter (MVP) pattern.
 *
 * @param <V> Associated view.
 * @author fjtorres
 */
public interface Presenter<V extends View> {

    void resume();

    void pause();

    void destroy();

    void setView(@NonNull V view);

    V getView();

}
