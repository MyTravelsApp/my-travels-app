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
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.CategoryComponent;
import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.presenter.CategoryPresenter;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

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
 * Created by stefani on 14/01/2016.
 */
public class CategoryFragment extends AbstractFragment<CategoryView, CategoryPresenter> implements CategoryView {
	
	@Inject
    CategoryPresenter presenter;

    @Bind(R.id.rv_categories)
    RecyclerView rv_category;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private CategoryAdapter adapter;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private SearchView searchView;

    private String currentFilter;

	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_category, container, true);
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
        loadCategories();
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
        getComponent(CategoryComponent.class).inject(this);
        this.presenter.setView(this);
    }

    @Override
    public void showRemoveError() {
        showToastMessage(getString(R.string.category_error_travel_places));
    }

    private void loadCategories() {
        getPresenter().loadCategories(currentFilter);
    }

    @Override
    public void showConfirmationRemove(final int position, final CategoryModel model) {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.category_delete_confirm)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        executeRemove(position,model);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }


    @Override
    public void executeRemove(final int position, final CategoryModel model){
        final Snackbar undo = Snackbar.make(coordinatorLayout, getString(R.string.category_delete), Snackbar.LENGTH_INDEFINITE);
        undo.setAction(R.string.text_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryFragment.this.adapter.undoRemove(position, model);
            }
        });

        undo.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);

                if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                    if (getPresenter() != null) {
                        getPresenter().removeCategory(model.getId());
                    }
                }
            }
        });

        undo.show();
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