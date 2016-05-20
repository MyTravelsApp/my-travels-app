package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesSelectorPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesSelectorView;
import com.github.mytravelsapp.presentation.view.adapter.TravelPlacesSelectorAdapter;
import com.github.mytravelsapp.presentation.view.components.SelectedOnGestureListener;
import com.github.mytravelsapp.presentation.view.components.SimpleOnItemTouchListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author fjtorres
 */
public class TravelPlacesSelectorFragment extends AbstractFragment<TravelPlacesSelectorView, TravelPlacesSelectorPresenter> implements TravelPlacesSelectorView {

    private static final String TAG = "TRAVEL_PLACES_SELECTOR_FRAGMENT";
    private static final String ARGUMENT_TRAVEL_MODEL = TAG + "_ARGUMENT_TRAVEL_MODEL";
    private static final String ARGUMENT_ENABLE_SELECTION = TAG + "_ARGUMENT_ENABLE_SELECTION";
    private static final String ARGUMENT_SELECTED_DAY = TAG + "_ARGUMENT_SELECTED_DAY";

    @Inject
    TravelPlacesSelectorPresenter presenter;

    @Bind(R.id.rv_travels_places)
    RecyclerView rv_travels_places;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private TravelPlacesSelectorAdapter adapter;

    private TravelModel model;

    private boolean enableSelection;

    private Date selectedDay;

    public static TravelPlacesSelectorFragment newInstance(final TravelModel pTravelModel, final boolean pEnableSelection, final Date pSelectedDay) {
        final TravelPlacesSelectorFragment fragment = new TravelPlacesSelectorFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        arguments.putBoolean(ARGUMENT_ENABLE_SELECTION, pEnableSelection);
        arguments.putLong(ARGUMENT_SELECTED_DAY, pSelectedDay.getTime());
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places_selector, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels_places.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adapter = new TravelPlacesSelectorAdapter(getActivity(), new ArrayList<TravelPlacesModel>());
        this.rv_travels_places.setAdapter(this.adapter);

        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.text_selection_default);

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
        loadTravelPlaces();
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
            case android.R.id.home:
                //use back when go from fragment to other
                result=this.back();
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    private void initialize() {
        getComponent(TravelPlacesComponent.class).inject(this);
        model = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        enableSelection = getArguments().getBoolean(ARGUMENT_ENABLE_SELECTION);
        selectedDay = new Date(getArguments().getLong(ARGUMENT_SELECTED_DAY));
        this.presenter.setView(this);

        if (enableSelection) {
            activateSelection();
        }
    }

    private void loadTravelPlaces() {
        getPresenter().loadPlaces(selectedDay);
    }

    @Override
    protected TravelPlacesSelectorPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void renderList(List<TravelPlacesModel> places) {
        adapter.setList(places);
    }

    @Override
    public List<TravelPlacesModel> getSelectedPlaces() {
        return null;
    }

    @Override
    public TravelModel getCurrentModel() {
        return model;
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
    public void showLoadError() {
        // FIXME ERROR AL CARGAR
    }

    public void finishSelection(){
        this.back();
    }

    private void activateSelection () {
        final SelectedOnGestureListener onGestureListener = new SelectedOnGestureListener(rv_travels_places, (AppCompatActivity) getActivity());
        onGestureListener.setOnSelectListener(onSelectListener);
        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(getActivity(), onGestureListener);
        this.rv_travels_places.addOnItemTouchListener(new SimpleOnItemTouchListener(gestureDetector));

    }

    private final SelectedOnGestureListener.OnSelectListener onSelectListener = new SelectedOnGestureListener.OnSelectListener() {
        @Override
        public void onSelect() {
            if (getPresenter() != null) {
                getPresenter().onSelect(selectedDay, adapter.getSelectedModels());
            }
        }
    };


}
