package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;
import com.github.mytravelsapp.presentation.view.adapter.TravelAdapter;
import com.github.mytravelsapp.presentation.view.adapter.TravelPlacesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesFragment extends AbstractFragment<TravelPlacesView, TravelPlacesPresenter> implements TravelPlacesView {

    //Travel associated of places
    private static final String ARGUMENT_TRAVEL_MODEL = "ARGUMENT_TRAVEL_MODEL";


    private TravelModel travelModel;

    @Inject
    TravelPlacesPresenter presenter;

    private TravelPlacesListener travelPlacesListener;

    private TravelPlacesAdapter adapter;

    @Bind(R.id.rv_travels_places)
    RecyclerView rv_travels_places;

    @Bind(R.id.btn_add_travel_places)
    FloatingActionButton btn_add_travel_places;



    public static TravelPlacesFragment newInstance(final TravelModel pTravelModel) {
        final TravelPlacesFragment fragment = new TravelPlacesFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        fragment.setArguments(arguments);
        return fragment;
    }

    /**
     * Se ejecuta cada vez que se renderiza la vista
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels_places.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.adapter = new TravelPlacesAdapter(getActivity(), new ArrayList<TravelPlacesModel>());// FIXME List to load
        this.adapter.setOnItemClickListener(onItemClickListener);
        this.rv_travels_places.setAdapter(this.adapter);

        this.btn_add_travel_places.setOnClickListener(onAddClickListener);

        return fragmentView;
    }

    /**
     * Se ejecuta cuando se ha creado la actividad
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        loadTravelsPlaces();
    }

    private void initialize() {
        getComponent(TravelPlacesComponent.class).inject(this);

        travelModel = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        this.presenter.setView(this);
    }

    private void loadTravelsPlaces() {
        getPresenter().loadTravelsPlaces(travelModel.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    protected TravelPlacesPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void newTravelPlaces() {
        if (travelPlacesListener != null) {
            travelPlacesListener.onAddTravelPlacesClicked(travelModel);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TravelPlacesListener) {
            this.travelPlacesListener = (TravelPlacesListener) context;
        }
    }

    @Override
    public void renderList(final List<TravelPlacesModel> list) {
        this.adapter.setList(list);
    }

    @Override
    public void viewDetail(final TravelPlacesModel selectedModel) {
        if (travelPlacesListener != null) {
            travelPlacesListener.onTravelPlacesClicked(selectedModel);
        }
    }

    public interface TravelPlacesListener {
        void onTravelPlacesClicked(TravelPlacesModel model);

        void onAddTravelPlacesClicked(TravelModel model);
    }

    private final TravelPlacesAdapter.OnItemClickListener onItemClickListener = new TravelPlacesAdapter.OnItemClickListener() {
        @Override
        public void onTravelPlacesItemClicked(final TravelPlacesModel model) {

            if (getPresenter() != null && model != null) {
                getPresenter().viewDetail(model);
            }
        }
    };

    private final View.OnClickListener onAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (getPresenter() != null) {
                getPresenter().newTravelPlaces();
            }
        }
    };

}
