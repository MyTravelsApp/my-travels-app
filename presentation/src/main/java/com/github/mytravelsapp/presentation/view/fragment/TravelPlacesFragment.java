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
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;
import com.github.mytravelsapp.presentation.view.activity.TravelNavigationListener;
import com.github.mytravelsapp.presentation.view.adapter.TravelPlacesAdapter;
import com.github.mytravelsapp.presentation.view.components.RemoveItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by stefani on 10/12/2015.
 */
public class TravelPlacesFragment extends SearchFragment<TravelPlacesView, TravelPlacesPresenter> implements TravelPlacesView {

    //Travel associated of places
    private static final String ARGUMENT_TRAVEL_MODEL = "ARGUMENT_TRAVEL_MODEL";


    private TravelModel travelModel;

    private Snackbar undoSnackbar;

    @Inject
    TravelPlacesPresenter presenter;

    private TravelPlacesAdapter adapter;

    @Bind(R.id.rv_travels_places)
    RecyclerView rv_travels_places;

    @Bind(R.id.btn_add_travel_places)
    FloatingActionButton btn_add_travel_places;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private long idTravelPlacesDelete = TravelPlacesModel.DEFAULT_ID;


    public static TravelPlacesFragment newInstance(final TravelModel pTravelModel) {
        final TravelPlacesFragment fragment = new TravelPlacesFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (undoSnackbar != null && undoSnackbar.isShownOrQueued()) {
            undoSnackbar.dismiss();
        }
    }

    /**
     * It is execute when rendericed the view.
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels_places.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.adapter = new TravelPlacesAdapter(getActivity(), new ArrayList<TravelPlacesModel>());// FIXME List to loadCategories
        this.adapter.setOnItemClickListener(onItemClickListener);
        this.adapter.setOnRemoveListener(onRemoveListener);
        this.rv_travels_places.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        this.btn_add_travel_places.setOnClickListener(onAddClickListener);
        //Add event delete touch
        final ItemTouchHelper helper = new ItemTouchHelper(new RemoveItemTouchHelperCallback<TravelPlacesModel>(this.adapter));
        helper.attachToRecyclerView(rv_travels_places);
        return fragmentView;
    }

    /**
     * Load fragment menu.
     *
     * @param menu     Fragment menu.
     * @param inflater Menu inflater.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_travel_places, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search_travel);
        configureSearch(searchItem);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (TravelModel.DEFAULT_ID == travelModel.getId()) {
            menu.findItem(R.id.action_planning_travel).setVisible(false);
        }
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
            case R.id.action_edit_travel:
                presenter.getNavigator().navigateToTravelDetail(getContext(),travelModel);
                result = true;
                break;
            case R.id.action_planning_travel:
                if(getActivity() instanceof TravelNavigationListener){
                    ((TravelNavigationListener) getActivity()).openFragmentTravelPlanning();
                }
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    /**
     * Se ejecuta cuando se ha creado la actividad
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        getPresenter().searchTravelsPlaces(null, travelModel.getId());    }

    private void initialize() {
        getComponent(TravelPlacesComponent.class).inject(this);

        travelModel = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        this.presenter.setView(this);
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
    public void renderList(final List<TravelPlacesModel> list) {
        this.adapter.setList(list);
    }


    @Override
    public TravelModel getCurrentTravel() {
        return this.travelModel;
    }


    private final TravelPlacesAdapter.OnItemClickListener onItemClickListener = new TravelPlacesAdapter.OnItemClickListener<TravelPlacesModel>() {
        @Override
        public void onItemClicked(final TravelPlacesModel model) {

            if (getPresenter() != null && model != null) {
                model.setTravelModel(travelModel);
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

    private final TravelPlacesAdapter.OnRemoveListener onRemoveListener = new TravelPlacesAdapter.OnRemoveListener<TravelPlacesModel>() {
        @Override
        public void onRemove(final int position, final TravelPlacesModel model) {
            idTravelPlacesDelete = model.getId();

            if (undoSnackbar == null) {
                undoSnackbar = Snackbar.make(coordinatorLayout, getString(R.string.travel_delete), Snackbar.LENGTH_INDEFINITE);
            } else {
                undoSnackbar.dismiss();
            }


            undoSnackbar.setAction(R.string.text_undo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idTravelPlacesDelete = TravelPlacesModel.DEFAULT_ID;
                    TravelPlacesFragment.this.adapter.undoRemove(position, model);
                }
            });

            undoSnackbar.setCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    super.onDismissed(snackbar, event);

                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                        if (getPresenter() != null) {
                            idTravelPlacesDelete = TravelPlacesModel.DEFAULT_ID;
                            getPresenter().removeTravelPlaces(model.getId());
                        }
                    }
                }
            });

            undoSnackbar.show();
        }
    };

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
    public void executeSearch(String filter) {
        getPresenter().searchTravelsPlaces(filter, travelModel.getId());
    }
}
