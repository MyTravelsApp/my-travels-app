package com.github.mytravelsapp.business.executor;

/**
 * Thread abstraction to change the execution context for the callback of background task.
 */
public interface PostExecutionThread {
    void executeOnUiThread(Runnable runnable);
}
