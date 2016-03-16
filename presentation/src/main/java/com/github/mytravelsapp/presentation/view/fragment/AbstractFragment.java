package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.presenter.Presenter;
import com.github.mytravelsapp.presentation.view.View;
import com.github.mytravelsapp.presentation.view.activity.AbstractActivity;

/**
 * Base {@link android.support.v4.app.Fragment} class for all fragments in this application.
 *
 * @author fjtorres
 */
public abstract class AbstractFragment<V extends View, P extends Presenter<V>> extends Fragment implements View {


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

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    protected abstract P getPresenter();

    @Override
    public void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().resume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().destroy();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().pause();
        }
    }

    protected <C> C getComponent(Class<C> componentType) {
        C result = null;
        if (getActivity() instanceof HasComponent) {
            result = componentType.cast(((HasComponent<C>) getActivity()).getComponent());
        }
        return result;
    }

    protected AbstractActivity getAppActivity() {
        return (AbstractActivity)getActivity();
    }
}
