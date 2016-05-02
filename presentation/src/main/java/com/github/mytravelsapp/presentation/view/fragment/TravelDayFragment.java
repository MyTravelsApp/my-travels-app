package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelDayPresenter;
import com.github.mytravelsapp.presentation.view.TravelDayView;
import com.github.mytravelsapp.presentation.view.adapter.AbstractAdapter;
import com.github.mytravelsapp.presentation.view.adapter.TravelPlacesAdapter;
import com.github.mytravelsapp.presentation.view.components.RemoveItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author fjtorres
 */
public class TravelDayFragment extends AbstractFragment<TravelDayView, TravelDayPresenter> implements TravelDayView {

    private static final String ARGUMENT_TRAVEL_MODEL = "ARGUMENT_TRAVEL_MODEL";
    private static final String ARGUMENT_SELECTED_DATE = "ARGUMENT_SELECTED_DATE";

    private TravelModel travelModel;
    private Date selectedDate;

    @Inject
    TravelDayPresenter presenter;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.rv_travels_places)
    RecyclerView rv_travels_places;

    @Bind(R.id.btn_add_travel_places)
    FloatingActionButton btn_add_travel_places;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private TravelPlacesAdapter adapter;

    private Snackbar undoSnackbar;

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
    public void onPause() {
        super.onPause();

        if (undoSnackbar != null && undoSnackbar.isShownOrQueued()) {
            undoSnackbar.dismiss();
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_day, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels_places.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adapter = new TravelPlacesAdapter(getActivity(), new ArrayList<TravelPlacesModel>());
        this.adapter.setOnRemoveListener(onRemoveListener);
        this.adapter.setOnMoveListener(onMoveListener);
        this.rv_travels_places.setAdapter(this.adapter);
        this.btn_add_travel_places.setOnClickListener(onAddClickListener);
        //Add event delete touch
        final ItemTouchHelper helper = new ItemTouchHelper(new RemoveItemTouchHelperCallback<>(this.adapter, true));
        helper.attachToRecyclerView(rv_travels_places);

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

    @Override
    public TravelModel getCurrentModel() {
        return travelModel;
    }

    @Override
    public void setCurrentModel(final TravelModel model) {
        this.travelModel = model;
    }

    @Override
    public void renderList(List<TravelPlacesModel> list) {
        adapter.setList(list);
    }

    private void initialize() {
        getComponent(TravelPlacesComponent.class).inject(this);
        this.presenter.setView(this);

        this.travelModel = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        this.selectedDate = (Date) getArguments().getSerializable(ARGUMENT_SELECTED_DATE);

        getPresenter().load(selectedDate);

        getActivity().setTitle(Utils.formatLargeDate(selectedDate));
    }

    private final View.OnClickListener onAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            getAppActivity().replaceFragment(R.id.fragmentTravelDay, TravelPlacesSelectorFragment.newInstance(travelModel, true, selectedDate), true);
        }
    };

    private final TravelPlacesAdapter.OnRemoveListener onRemoveListener = new TravelPlacesAdapter.OnRemoveListener<TravelPlacesModel>() {
        @Override
        public void onRemove(final int position, final TravelPlacesModel model) {

            if (undoSnackbar == null) {
                undoSnackbar = Snackbar.make(coordinatorLayout, getString(R.string.travel_delete), Snackbar.LENGTH_INDEFINITE);
            } else {
                undoSnackbar.dismiss();
            }

            undoSnackbar.setAction(R.string.text_undo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TravelDayFragment.this.adapter.undoRemove(position, model);
                }
            });

            undoSnackbar.setCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    super.onDismissed(snackbar, event);

                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                        if (getPresenter() != null) {
                            getPresenter().remove(selectedDate, model);
                        }
                    }
                }
            });

            undoSnackbar.show();
        }
    };

    final TravelPlacesAdapter.OnMoveListener onMoveListener = new AbstractAdapter.OnMoveListener<TravelPlacesModel>() {
        @Override
        public void onMove(int fromPosition, int toPosition, TravelPlacesModel model) {
            if (getPresenter() != null) {
                getPresenter().move(selectedDate, fromPosition, toPosition);
            }
        }
    };
}
