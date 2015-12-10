package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;
import com.github.mytravelsapp.presentation.view.adapter.TravelAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesFragment extends AbstractFragment<TravelPlacesView, TravelPlacesPresenter> implements TravelPlacesView {


    TravelPlacesPresenter presenter;

    @Bind(R.id.rv_travels_places)
    RecyclerView rv_travels_places;

    @Bind(R.id.btn_add_travel)
    FloatingActionButton btn_add_travel;

    private void initialize() {
        presenter = new TravelPlacesPresenter();
        this.presenter.setView(this);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places, container, true);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels_places.setLayoutManager(new LinearLayoutManager(getActivity()));

        //this.btn_add_travel.setOnClickListener(onAddClickListener);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected TravelPlacesPresenter getPresenter() {
        return presenter;
    }
}
