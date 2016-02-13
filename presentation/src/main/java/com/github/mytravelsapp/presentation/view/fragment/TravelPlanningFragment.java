package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlanningPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlanningView;
import com.github.mytravelsapp.presentation.view.adapter.AbstractAdapter;
import com.github.mytravelsapp.presentation.view.adapter.DateAdapter;
import com.github.mytravelsapp.presentation.view.components.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows organizer view for travel.
 *
 * @author fjtorres
 */
public class TravelPlanningFragment extends AbstractFragment<TravelPlanningView, TravelPlanningPresenter> implements TravelPlanningView {

    private static final String ARGUMENT_TRAVEL_MODEL = "ARGUMENT_TRAVEL_MODEL";

    @Inject
    TravelPlanningPresenter presenter;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    @Bind(R.id.rv_travel_days)
    RecyclerView rv_travel_days;

    private DateAdapter adapter;

    private TravelModel travelModel;

    public static TravelPlanningFragment newInstance(final TravelModel pTravelModel) {
        final TravelPlanningFragment fragment = new TravelPlanningFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_planning, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travel_days.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rv_travel_days.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        adapter = new DateAdapter(getActivity(), new ArrayList<Date>());
        rv_travel_days.setAdapter(adapter);
        adapter.setOnItemClickListener(dateOnItemClickListener);

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
        initialize(savedInstanceState);

    }

    /**
     * Load fragment menu.
     *
     * @param menu     Fragment menu.
     * @param inflater Menu inflater.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    /**
     * Control menu item selection.
     *
     * @param item Selected menu.
     * @return boolean result.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public TravelPlanningPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
        getActivity().setProgressBarIndeterminate(true);
    }

    @Override
    public void hideLoading() {
        rl_progress.setVisibility(View.GONE);
        getActivity().setProgressBarIndeterminate(false);
    }

    private void initialize(Bundle savedInstanceState) {
        getComponent(TravelComponent.class).inject(this);
        this.presenter.setView(this);

        if (savedInstanceState == null) {
            this.travelModel = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        } else {
            this.travelModel = savedInstanceState.getParcelable("STATE_PARAM_TRAVEL_MODEL");
        }

        this.presenter.loadDaysOfTravel();
    }

    @Override
    public void renderTravelDays(final List<Date> daysOfTravel) {
        adapter.setList(daysOfTravel);
    }

    @Override
    public TravelModel getCurrentModel() {
        return travelModel;
    }

    private final DateAdapter.OnItemClickListener<Date> dateOnItemClickListener = new AbstractAdapter.OnItemClickListener<Date>() {
        @Override
        public void onItemClicked(final Date selectedDate) {
            presenter.selectedDate(getCurrentModel(), selectedDate);
        }
    };
}
