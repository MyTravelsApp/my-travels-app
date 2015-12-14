package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.business.service.TravelPlacesService;
import com.github.mytravelsapp.business.service.impl.TravelPlacesServiceImpl;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.di.components.TravelPlacesComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesDetailPresenter;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;
import com.github.mytravelsapp.presentation.view.components.DatePickerSelectionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private static final String ARGUMENT_TRAVEL_PLACES_ID = "ARGUMENT_TRAVEL_PLACES_ID";

    @Inject
    TravelPlacesDetailPresenter presenter;

    private TravelPlacesDetailsListener travelPlacesDetailsListener;

    private long travelPlacesId;

    @Bind(R.id.txt_name)
    EditText txt_name;

    @Bind(R.id.txt_observation)
    EditText txt_observation;

    @Bind(R.id.spinner_category)
    Spinner spinner_category;

    public static TravelPlacesDetailsFragment newInstance(final long travelPlacesId) {
        final TravelPlacesDetailsFragment fragment = new TravelPlacesDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putLong(ARGUMENT_TRAVEL_PLACES_ID, travelPlacesId);
        fragment.setArguments(arguments);
        return fragment;
    }

    public TravelPlacesDetailsFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_places_details, container, false);
        ButterKnife.bind(this, fragmentView);
        addItemsSpinnerCategories();
        // Setup UI
        return fragmentView;
    }

    /**
     * Add items to spinner categories
     *
     */
    public void addItemsSpinnerCategories() {
        List<String> list = new ArrayList<String>();
        list.add("Parques");
        list.add("Plazas");
        list.add("Monumentos");
        list.add("Museos");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(dataAdapter);
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

    private void initialize() {
        getComponent(TravelPlacesComponent.class).inject(this);
        this.presenter.setView(this);
        this.travelPlacesId = getArguments().getLong(ARGUMENT_TRAVEL_PLACES_ID);
        getPresenter().loadModel(this.travelPlacesId);
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

    }

    @Override
    public TravelPlacesModel getCurrentModel() {
        final TravelPlacesModel model = new TravelPlacesModel(travelPlacesId);
        model.setName(txt_name.getText().toString());
        return model;
    }

    public interface TravelPlacesDetailsListener {

    }

}
