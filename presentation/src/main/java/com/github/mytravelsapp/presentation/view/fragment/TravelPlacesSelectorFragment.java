package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesSelectorPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesSelectorView;
import com.github.mytravelsapp.presentation.view.adapter.AbstractSelectorAdapter;
import com.github.mytravelsapp.presentation.view.adapter.TravelPlacesSelectorAdapter;
import com.github.mytravelsapp.presentation.view.components.SimpleDividerItemDecoration;

import java.util.ArrayList;
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

    @Inject
    TravelPlacesSelectorPresenter presenter;

    @Bind(R.id.rv_travels_places)
    RecyclerView rv_travels_places;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private TravelPlacesSelectorAdapter adapter;

    private TravelModel model;

    private ActionMode actionMode;

    public static TravelPlacesSelectorFragment newInstance(final TravelModel pTravelModel) {
        final TravelPlacesSelectorFragment fragment = new TravelPlacesSelectorFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places_selector, container, false);
        ButterKnife.bind(this, fragmentView);

        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(getActivity(), new SelectedOnGestureListener(rv_travels_places, (AppCompatActivity)getActivity(), new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(final ActionMode mode, final Menu menu) {
                final MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_selection, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(final ActionMode mode, final Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, final MenuItem item) {
                boolean result = true;
                switch (item.getItemId()) {
                    case R.id.action_selection:
                        break;
                    default:
                        result = false;
                        break;
                }

                return result;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                adapter.clearSelection();
            }
        }));

        // Setup UI
        this.rv_travels_places.setLayoutManager(new LinearLayoutManager(getActivity()));
        //this.rv_travels_places.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        this.rv_travels_places.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                gestureDetector.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        this.adapter = new TravelPlacesSelectorAdapter(getActivity(), new ArrayList<TravelPlacesModel>());
        this.rv_travels_places.setAdapter(this.adapter);
        setHasOptionsMenu(true);

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

    private void initialize() {
        getComponent(TravelComponent.class).inject(this);
        model = getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        this.presenter.setView(this);
    }

    private void loadTravelPlaces() {
        getPresenter().loadPlaces();
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

    private class SelectedOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        private final RecyclerView recyclerView;

        private final ActionMode.Callback callback;

        private final AppCompatActivity activity;

        public SelectedOnGestureListener(final RecyclerView pRecyclerView, final AppCompatActivity pActivity, final ActionMode.Callback pCallback) {
            this.recyclerView = pRecyclerView;
            this.callback = pCallback;
            this.activity = pActivity;
        }


        public void onLongPress(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (actionMode == null) {
                // Start the CAB using the ActionMode.Callback defined above
                actionMode = activity.startSupportActionMode(callback);
            }

            int idx = recyclerView.getChildAdapterPosition(view);

            toggleSelection(idx);

            super.onLongPress(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            int idx = recyclerView.getChildAdapterPosition(view);
            if (actionMode != null) {
                toggleSelection(idx);
            }
            return super.onSingleTapConfirmed(e);
        }

        private void toggleSelection (final int idx) {
            if (recyclerView.getAdapter() instanceof AbstractSelectorAdapter) {
                final AbstractSelectorAdapter<?, ?> lAdapter = ((AbstractSelectorAdapter) recyclerView.getAdapter());
                lAdapter.toggleSelection(idx);
                if (lAdapter.getSelectedItemCount() == 0) {
                    actionMode.finish();
                } else {
                    actionMode.setTitle(getString(R.string.text_selection, lAdapter.getSelectedItemCount()));
                }
            }
        }
    }
}
