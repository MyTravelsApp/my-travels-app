package com.github.mytravelsapp.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.presenter.Presenter;
import com.github.mytravelsapp.presentation.view.View;

/**
 * Base {@link android.app.Fragment} class for all activities in this application.
 *
 * @author fjtorres
 */
public abstract class AbstractFragment<V extends View, P extends Presenter<V>> extends Fragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected abstract P getPresenter();

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
