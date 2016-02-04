package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.RelativeLayout;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelListPresenter;
import com.github.mytravelsapp.presentation.view.TravelListView;
import com.github.mytravelsapp.presentation.view.adapter.TravelAdapter;
import com.github.mytravelsapp.presentation.view.components.RemoveItemTouchHelperCallback;
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

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.rv_travels)
    RecyclerView rv_travels;

    @Bind(R.id.btn_add_travel)
    FloatingActionButton btn_add_travel;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private TravelAdapter adapter;

    private SearchView searchView;

    private String currentFilter;

    public TravelListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_list, container, true);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_travels.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rv_travels.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        this.adapter = new TravelAdapter(getActivity(), new ArrayList<TravelModel>());
        this.adapter.setOnItemClickListener(onItemClickListener);
        this.adapter.setOnRemoveListener(onRemoveListener);
        this.rv_travels.setAdapter(this.adapter);
        this.btn_add_travel.setOnClickListener(onAddClickListener);

        final ItemTouchHelper helper = new ItemTouchHelper(new RemoveItemTouchHelperCallback<TravelModel>(this.adapter));
        helper.attachToRecyclerView(rv_travels);
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

    /**
     * Load fragment menu.
     *
     * @param menu     Fragment menu.
     * @param inflater Menu inflater.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_travel_list, menu);
        configureSearch(menu);
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
            case R.id.action_search_travel:

                result = true;
                break;
            case R.id.action_configure_travel:
                presenter.getNavigator().navigateToCategory(getContext());
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
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
    }

    private void loadTravels() {
        getPresenter().loadTravels(currentFilter);
    }

    private void configureSearch(final Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.action_search_travel);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.text_search_box));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String filter) {
                currentFilter = filter;
                loadTravels();
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
                    loadTravels();
                }
                return true;
            }
        });
    }

    @Override
    public void showLoadError() {
        showToastMessage(getString(R.string.travel_error_load));
    }

    @Override
    public void showRemoveError() {
        showToastMessage(getString(R.string.travel_error_delete));
    }

    private final TravelAdapter.OnItemClickListener onItemClickListener = new TravelAdapter.OnItemClickListener<TravelModel>() {
        @Override
        public void onTravelItemClicked(final TravelModel model) {

            if (getPresenter() != null && model != null) {
                getPresenter().viewTravelPlaces(model);
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

    private final TravelAdapter.OnRemoveListener onRemoveListener = new TravelAdapter.OnRemoveListener<TravelModel>() {
        @Override
        public void onRemove(final int position, final TravelModel model) {

            final Snackbar undo = Snackbar.make(coordinatorLayout, getString(R.string.travel_delete), Snackbar.LENGTH_INDEFINITE);
            undo.setAction(R.string.text_undo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TravelListFragment.this.adapter.undoRemove(position, model);
                }
            });

            undo.setCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    super.onDismissed(snackbar, event);

                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                        if (getPresenter() != null) {
                            getPresenter().removeTravel(model.getId());
                        }
                    }
                }
            });

            undo.show();
        }
    };
}
