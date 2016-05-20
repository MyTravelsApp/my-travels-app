package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author fjtorres
 */
public abstract class MyTravelsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        final View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onPreparePresenter();

        initializeView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void onPreparePresenter () {

    }

    protected abstract int getLayoutId ();
    protected abstract void initializeView();

    private void injectDependencies () {

    }


}
