package com.github.mytravelsapp.presentation;

import android.os.Handler;
import android.os.Looper;

import com.github.mytravelsapp.business.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by kisco on 15/12/2015.
 */
@Singleton
public class UiThread implements PostExecutionThread {

    @Inject
    public UiThread () {

    }

    @Override
    public  void executeOnUiThread(final Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
