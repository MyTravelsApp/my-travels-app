package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import com.github.mytravelsapp.presentation.di.components.MainComponent;
import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.presenter.CategoryPresenter;
import com.github.mytravelsapp.presentation.view.CategoryView;
import com.github.mytravelsapp.presentation.view.adapter.CategoryAdapter;
import com.github.mytravelsapp.presentation.view.components.CategoryTouchHelperCallback;
import com.github.mytravelsapp.presentation.view.components.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Class for the fragment of category.
 * Created by stefani on 14/01/2016.
 */
public class CategoryFragment extends SearchFragment<CategoryView, CategoryPresenter> implements CategoryView {
	
	@Inject
    CategoryPresenter presenter;

    private Snackbar undoSnackbar;

    @Bind(R.id.rv_categories)
    RecyclerView rv_category;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private CategoryAdapter adapter;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private long idCategoryDelete = CategoryModel.DEFAULT_ID;


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
        final View fragmentView = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        this.rv_category.setLayoutManager(new LinearLayoutManager(getActivity()));
		//Show the line beetwen two rows.
        this.rv_category.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        this.adapter = new CategoryAdapter(getActivity(), new ArrayList<CategoryModel>());
        this.adapter.setOnClickAddButtonListener(onClickAddButtonListener);
        this.adapter.setOnRemoveListener(onRemoveListener);
        this.rv_category.setAdapter(this.adapter);
        final ItemTouchHelper helper = new ItemTouchHelper(new CategoryTouchHelperCallback(this.adapter));
        helper.attachToRecyclerView(rv_category);
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
        getPresenter().loadCategories(null);
    }
	
    @Override
    protected CategoryPresenter getPresenter() {
        return presenter;
    }


    @Override
    public void renderList(List<CategoryModel> list) {
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

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void addItemSaved(CategoryModel model, int position){
        if(position == -1){
            adapter.addItemList(model);
        } else{
            adapter.notifyItemChanged(position);
        }

    }
	
	private void initialize() {
        getComponent(MainComponent.class).inject(this);
        this.presenter.setView(this);
    }

    @Override
    public void showRemoveError() {
        showToastMessage(getString(R.string.category_error_travel_places));
    }


    @Override
    public void showConfirmationRemove(final int position, final CategoryModel model) {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.category_delete_confirm)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        executeRemove(position, model);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        CategoryFragment.this.adapter.undoRemove(position, model);
                    }
                }).show();

    }


    @Override
    public void executeRemove(final int position, final CategoryModel model){
        idCategoryDelete =model.getId();
        undoSnackbar = Snackbar.make(coordinatorLayout, getString(R.string.category_delete), Snackbar.LENGTH_INDEFINITE);
        undoSnackbar.setAction(R.string.text_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCategoryDelete=CategoryModel.DEFAULT_ID;
                CategoryFragment.this.adapter.undoRemove(position, model);
            }
        });

        undoSnackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);

                if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                    if (getPresenter() != null) {
                        idCategoryDelete=CategoryModel.DEFAULT_ID;
                        getPresenter().removeCategory(model.getId());
                    }
                }
            }
        });

        undoSnackbar.show();
    }

    /**
     * Load fragment menu.
     *
     * @param menu     Fragment menu.
     * @param inflater Menu inflater.
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categories, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search_category);
        configureSearch(searchItem);
    }

    @Override
    public void executeSearch(String filter) {
        getPresenter().loadCategories(filter);
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
            default:
                if(idCategoryDelete != CategoryModel.DEFAULT_ID){
                    getPresenter().removeCategory(idCategoryDelete);
                    idCategoryDelete = CategoryModel.DEFAULT_ID;
                }
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }


    private final CategoryAdapter.OnRemoveListener onRemoveListener = new CategoryAdapter.OnRemoveListener<CategoryModel>() {
        @Override
        public void onRemove(final int position, final CategoryModel model) {
            getPresenter().checkTravelPlacesByCategory(position,model);
        }
    };

    private final CategoryAdapter.OnClickAddButtonListener onClickAddButtonListener = new CategoryAdapter.OnClickAddButtonListener() {
        @Override
        public void save(CategoryModel model, int position) {
            getPresenter().save(model, position);
        }
    };


}