package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.github.mytravelsapp.presentation.presenter.TravelDayPresenter;
import com.github.mytravelsapp.presentation.view.TravelDayView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kisco on 11/02/2016.
 */
public class TravelDayFragment extends AbstractFragment<TravelDayView, TravelDayPresenter> implements TravelDayView {

    private static final String ARGUMENT_TRAVEL_MODEL = "ARGUMENT_TRAVEL_MODEL";
    private static final String ARGUMENT_SELECTED_DATE = "ARGUMENT_SELECTED_DATE";

    private TravelModel travelModel;
    private Date selectedDate;

    @Inject
    TravelDayPresenter presenter;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    public static TravelDayFragment newInstance(final TravelModel pTravelModel, final Date pSelectedDate) {
        final TravelDayFragment fragment = new TravelDayFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        arguments.putSerializable(ARGUMENT_SELECTED_DATE, pSelectedDate);
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
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_day, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI


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
    protected TravelDayPresenter getPresenter() {
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

    private void initialize() {
        getComponent(TravelComponent.class).inject(this);
        this.presenter.setView(this);

        this.travelModel = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        this.selectedDate = (Date) getArguments().getSerializable(ARGUMENT_SELECTED_DATE);
    }
}
