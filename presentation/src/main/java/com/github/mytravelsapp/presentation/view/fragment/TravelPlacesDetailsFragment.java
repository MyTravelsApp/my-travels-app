package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.CategoryModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesDetailPresenter;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;
import com.github.mytravelsapp.presentation.view.adapter.SpinCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows details of certain travel places.
 *
 * @author stefani
 */
public class TravelPlacesDetailsFragment extends AbstractFormFragment<TravelPlacesDetailsView, TravelPlacesDetailPresenter> implements TravelPlacesDetailsView {

    private static final String ARGUMENT_TRAVEL_PLACES_MODEL = "ARGUMENT_TRAVEL_PLACES_MODEL";

    @Inject
    TravelPlacesDetailPresenter presenter;

    private TravelPlacesModel travelPlacesModel;

    private SpinCategoryAdapter dataAdapter;

    @Bind(R.id.txt_name)
    EditText txt_name;

    @Bind(R.id.txt_observation)
    EditText txt_observation;

    @Bind(R.id.spinner_category)
    Spinner spinner_category;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    public List<CategoryModel> categories = new ArrayList<CategoryModel>();

    public static TravelPlacesDetailsFragment newInstance(final TravelPlacesModel travelPlacesModel) {
        final TravelPlacesDetailsFragment fragment = new TravelPlacesDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_PLACES_MODEL,travelPlacesModel);
        fragment.setArguments(arguments);
        return fragment;
    }

    public TravelPlacesDetailsFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places_details, container, false);
        ButterKnife.bind(this, fragmentView);
        setHasOptionsMenu(true);
        dataAdapter = new SpinCategoryAdapter(getViewContext(), categories);
        // Setup UI
        spinner_category.setAdapter(dataAdapter);
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
        inflater.inflate(R.menu.menu_travel_details, menu);
    }

    /**
     * Control menu item selection.
     *
     * @param item Selected menu.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            case R.id.action_save_travel:
                getPresenter().save();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
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

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    private void initialize() {
        getComponent(TravelPlacesComponent.class).inject(this);
        this.presenter.setView(this);
        this.travelPlacesModel = getArguments().getParcelable(ARGUMENT_TRAVEL_PLACES_MODEL);
        getPresenter().loadCategories();
        getPresenter().loadModel(this.travelPlacesModel.getId());
    }

    @Override
    protected TravelPlacesDetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    public boolean validate() {
        boolean result = true;

        View firstError = null;

        if (!validateRequiredField(txt_name)) {
            result = false;
            firstError = txt_name;
        }
        if (firstError != null) {
            firstError.requestFocus();
        }

        return result;
    }

    @Override
    public void renderModel(final TravelPlacesModel model) {
        if (model != null) {
            txt_name.setText(model.getName());
            txt_observation.setText(model.getObservations());
            spinner_category.setSelection(dataAdapter.getPosition(model.getCategoryModel()));
            if (travelPlacesModel.getId() == TravelPlacesModel.DEFAULT_ID) {
                getActivity().setTitle(R.string.activity_travel_places_title);
            }else{
                getActivity().setTitle(travelPlacesModel.getName());
            }
        }
    }

    @Override
    public void renderCategories(List<CategoryModel> pCategories) {
        if (pCategories != null) {
           dataAdapter.setList(pCategories);
        }
    }

    @Override
    public TravelPlacesModel getCurrentModel() {
        final TravelPlacesModel model = new TravelPlacesModel();
        model.setTravelModel(travelPlacesModel.getTravelModel());
        model.setId(travelPlacesModel.getId());
        model.setName(txt_name.getText().toString());
        model.setObservations(txt_observation.getText().toString());
        model.setCategoryModel((CategoryModel) spinner_category.getSelectedItem());
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

    public interface TravelPlacesDetailsListener {

    }

}
