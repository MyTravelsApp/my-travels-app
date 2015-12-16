package com.github.mytravelsapp.business.executor;

/**
 * Created by kisco on 15/12/2015.
 */
public interface PostExecutionThread {
    void executeOnUiThread(Runnable runnable);
}
