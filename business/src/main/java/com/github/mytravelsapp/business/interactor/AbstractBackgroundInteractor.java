package com.github.mytravelsapp.business.interactor;

import com.github.mytravelsapp.business.executor.PostExecutionThread;
import com.github.mytravelsapp.business.executor.ThreadExecutor;

/**
 * Created by kisco on 15/12/2015.
 */
public abstract class AbstractBackgroundInteractor<R> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    protected AbstractBackgroundInteractor(final ThreadExecutor pThreadExecutor, final PostExecutionThread pPostExecutionThread) {
        this.threadExecutor = pThreadExecutor;
        this.postExecutionThread = pPostExecutionThread;
    }

    public void execute(final Callback<R> callback) {
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    final R result = backgroundTask();
                    postExecutionThread.executeOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } catch (final Exception e) {
                    postExecutionThread.executeOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(e);
                        }
                    });
                }
            }
        });
    }

    public abstract R backgroundTask() throws Exception;
}
