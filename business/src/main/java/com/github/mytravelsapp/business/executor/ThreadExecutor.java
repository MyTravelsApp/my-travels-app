package com.github.mytravelsapp.business.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link com.fernandocejas.android10.sample.domain.interactor.UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
