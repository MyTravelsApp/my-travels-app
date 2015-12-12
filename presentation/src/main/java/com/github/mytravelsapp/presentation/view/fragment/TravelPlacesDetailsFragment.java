package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;
import com.github.mytravelsapp.presentation.presenter.TravelPlacesDetailPresenter;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.TravelPlacesDetailsView;
import com.github.mytravelsapp.presentation.view.components.DatePickerSelectionListener;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows details of certain travel places.
 *
 * @author stefani
 */
public class TravelPlacesDetailsFragment extends AbstractFragment<TravelPlacesDetailsView, TravelPlacesDetailPresenter> implements TravelPlacesDetailsView {

    private static final String ARGUMENT_TRAVEL_PLACES_ID = "ARGUMENT_TRAVEL_PLACES_ID";


    TravelPlacesDetailPresenter presenter;

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
        // Setup UI
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
    }

    private void initialize() {
        presenter = new TravelPlacesDetailPresenter();
        this.presenter.setView(this);
        this.travelPlacesId = getArguments().getLong(ARGUMENT_TRAVEL_PLACES_ID);
        getPresenter().loadModel(this.travelPlacesId);
    }

    @Override
    protected TravelPlacesDetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void renderModel(final TravelPlacesModel model) {

    }

}
