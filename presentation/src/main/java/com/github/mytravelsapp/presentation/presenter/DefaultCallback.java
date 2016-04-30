package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.presentation.view.View;

/**
 * Default task callback.
 *
 * @author fjtorres
 */
public class DefaultCallback<R> implements Callback<R> {

    private final View view;

    public DefaultCallback(final View pView) {
        this.view = pView;
    }

    @Override
    public void onSuccess(R result) {

    }

    @Override
    public void onError(Throwable cause) {
        view.showGenericError();
    }
}
