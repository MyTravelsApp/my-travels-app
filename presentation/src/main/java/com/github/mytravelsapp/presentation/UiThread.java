package com.github.mytravelsapp.presentation;

import android.os.Handler;
import android.os.Looper;

import com.github.mytravelsapp.business.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * MainThread (UI Thread) implementation based on {@link android.os.Handler}.
 */
@Singleton
public class UiThread implements PostExecutionThread {

    @Inject
    public UiThread() {

    }

    @Override
    public void executeOnUiThread(final Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
