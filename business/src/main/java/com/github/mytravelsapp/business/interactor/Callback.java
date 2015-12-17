package com.github.mytravelsapp.business.interactor;

/**
 * Background task callback.
 */
public interface Callback<R> {
    void onSuccess(R result);

    void onError(Throwable cause);
}
