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
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelListPresenter;
import com.github.mytravelsapp.presentation.view.TravelListView;
import com.github.mytravelsapp.presentation.view.adapter.TravelAdapter;
import com.github.mytravelsapp.presentation.view.components.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows a list of {@link TravelModel}.
 *
 * @author fjtorres
 */
public class TravelListFragment extends AbstractFragment<TravelListView, TravelListPresenter> implements TravelListView {

    @Inject
    TravelListPresenter presenter;

    @Bind(R.id.rv_travels)
    RecyclerView rv_travels;

    @Bind(R.id.btn_add_travel)
    FloatingActionButton btn_add_travel;

    private TravelAdapter adapter;

    private TravelListListener travelListListener;

    public TravelListFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TravelListListener) {
            this.travelListListener = (TravelListListener) context;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_list, container, true);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rv_travels.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        this.adapter = new TravelAdapter(getActivity(), new ArrayList<TravelModel>());// FIXME List to load
        this.adapter.setOnItemClickListener(onItemClickListener);
        this.rv_travels.setAdapter(this.adapter);
        this.btn_add_travel.setOnClickListener(onAddClickListener);

        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        loadTravels();
    }

    @Override
    public TravelListPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void renderList(final List<TravelModel> list) {
        this.adapter.setList(list);
    }

    @Override
    public void viewDetail(final TravelModel selectedModel) {
        if (travelListListener != null) {
            travelListListener.onTravelClicked(selectedModel);
        }
    }

    @Override
    public void newTravel() {
        if (travelListListener != null) {
            travelListListener.onAddTravelClicked();
        }
    }

    private void initialize() {
        getComponent(TravelComponent.class).inject(this);
        this.presenter.setView(this);
    }

    private void loadTravels() {
        getPresenter().loadTravels();
    }


    private final TravelAdapter.OnItemClickListener onItemClickListener = new TravelAdapter.OnItemClickListener() {
        @Override
        public void onTravelItemClicked(final TravelModel model) {

            if (getPresenter() != null && model != null) {
                getPresenter().viewDetail(model);
            }
        }
    };

    private final View.OnClickListener onAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (getPresenter() != null) {
                getPresenter().newTravel();
            }
        }
    };

    public interface TravelListListener {
        void onTravelClicked(TravelModel model);

        void onAddTravelClicked();
    }
}
