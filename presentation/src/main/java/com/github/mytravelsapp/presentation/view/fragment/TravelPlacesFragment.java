package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesView;
import com.github.mytravelsapp.presentation.view.adapter.TravelPlacesAdapter;
import com.github.mytravelsapp.presentation.view.components.SimpleDividerItemDecoration;
import com.github.mytravelsapp.presentation.view.components.TravelPlacesTouchHelperCallback;

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

    private SearchView searchView;

    private String currentFilter;


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
        this.rv_travels_places.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        this.adapter = new TravelPlacesAdapter(getActivity(), new ArrayList<TravelPlacesModel>());// FIXME List to load
        this.adapter.setOnItemClickListener(onItemClickListener);
        this.adapter.setOnRemoveListener(onRemoveListener);
        this.rv_travels_places.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        this.btn_add_travel_places.setOnClickListener(onAddClickListener);
        //Add event delete touch
        final ItemTouchHelper helper = new ItemTouchHelper(new TravelPlacesTouchHelperCallback(this.adapter));
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
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.text_search_box));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String filter) {
                currentFilter = filter;
                filterTravelsPlaces();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String filter) {
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean queryTextFocused) {
                if (!queryTextFocused) {
                    MenuItemCompat.collapseActionView(searchItem);
                    searchView.setQuery("", false);
                }
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                if (currentFilter != null) {
                    currentFilter = null;
                    filterTravelsPlaces();
                }
                return true;
            }
        });
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

    private void filterTravelsPlaces (){
        getPresenter().searchTravelsPlaces(currentFilter, travelModel.getId());
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

    private final TravelPlacesAdapter.OnRemoveListener onRemoveListener = new TravelPlacesAdapter.OnRemoveListener() {
        @Override
        public void onRemove(long identifier) {
            if (getPresenter() != null) {
                getPresenter().removeTravelPlaces(identifier);
            }
        }
    };

}
