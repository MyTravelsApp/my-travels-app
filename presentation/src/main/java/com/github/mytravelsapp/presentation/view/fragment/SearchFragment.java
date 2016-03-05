package com.github.mytravelsapp.presentation.view.fragment;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.presenter.Presenter;
import com.github.mytravelsapp.presentation.view.View;

/**
 * Created by stefani on 04/03/2016.
 */
public abstract class SearchFragment<V extends View, P extends Presenter<V>> extends AbstractFragment<V, P> {
    private SearchView searchView;
    private String currentFilter;

    protected void configureSearch(final MenuItem searchItem) {
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.text_search_box));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String filter) {
                currentFilter = filter;
                executeSearch(currentFilter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String filter) {
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(android.view.View view, boolean queryTextFocused) {
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
                    executeSearch(currentFilter);
                }
                return true;
            }
        });
    }

    public abstract void executeSearch(String filter);
}
