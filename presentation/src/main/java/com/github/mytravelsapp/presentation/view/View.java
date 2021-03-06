package com.github.mytravelsapp.presentation.view;

import android.content.Context;

/**
 * @author fjtorres
 */
public interface View {

    /**
     * Get a {@link android.content.Context}.
     */
    Context getViewContext();

    /**
     * Show a generic error message in the view.
     */
    void showGenericError ();
}
