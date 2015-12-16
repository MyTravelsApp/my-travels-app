package com.github.mytravelsapp.business.interactor;

/**
 * Created by kisco on 15/12/2015.
 */
public interface Callback<R> {
    void onSuccess(R result);

    void onError(Throwable cause);
}
