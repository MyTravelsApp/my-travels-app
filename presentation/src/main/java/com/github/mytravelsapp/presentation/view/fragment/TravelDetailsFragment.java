package com.github.mytravelsapp.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.adapter.PlacesAutoCompleteAdapter;
import com.github.mytravelsapp.presentation.view.components.DatePickerSelectionListener;
import com.github.mytravelsapp.presentation.view.components.PlacesSelectionListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows details of certain travel.
 *
 * @author fjtorres
 */
public class TravelDetailsFragment extends AbstractFormFragment<TravelDetailsView, TravelDetailPresenter> implements TravelDetailsView {

    private static final String ARGUMENT_TRAVEL_MODEL = "ARGUMENT_TRAVEL_MODEL";

    @Inject
    TravelDetailPresenter presenter;

    private TravelModel travelModel;

    @Bind(R.id.txt_name)
    EditText txt_name;

    @Bind(R.id.txt_destination)
    AutoCompleteTextView txt_destination;

    @Bind(R.id.txt_start_date)
    TextView txt_start_date;

    @Bind(R.id.btn_start_date)
    ImageButton btn_start_date;

    @Bind(R.id.txt_finish_date)
    TextView txt_finish_date;

    @Bind(R.id.btn_finish_date)
    ImageButton btn_finish_date;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

    private PlacesAutoCompleteAdapter placesAutoCompleteAdapter;

    private GoogleApiClient googleApiClient;

    public static TravelDetailsFragment newInstance(final TravelModel pTravelModel) {
        final TravelDetailsFragment fragment = new TravelDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TRAVEL_MODEL, pTravelModel);
        fragment.setArguments(arguments);
        return fragment;
    }

    public TravelDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_details, container, false);
        ButterKnife.bind(this, fragmentView);

        initGoogleApi();
        initAutocomplete();

        // Setup UI
        btn_start_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), txt_start_date, getString(R.string.conf_date_format)));
        btn_finish_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), txt_finish_date, getString(R.string.conf_date_format)));
        txt_destination.setAdapter(placesAutoCompleteAdapter);
        txt_destination.setOnItemClickListener(new PlacesSelectionListener(placesAutoCompleteAdapter, googleApiClient, placesSelectionCallback));

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
     * @return boolean result.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;
        switch (item.getItemId()) {
            case R.id.action_save_travel:
                getPresenter().save();
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    private void initialize() {
        getComponent(TravelComponent.class).inject(this);
        this.travelModel= getArguments().getParcelable(ARGUMENT_TRAVEL_MODEL);
        getPresenter().setView(this);
        getPresenter().loadModel(travelModel.getId());
    }

    @Override
    protected TravelDetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void renderModel(final TravelModel model) {
        if (model != null) {
            txt_name.setText(model.getName());
            txt_destination.setText(model.getDestination().getDestinationPlaceName());
            txt_start_date.setText(Utils.formatDate(model.getStartDate(), Utils.DATE_FORMAT));
            txt_finish_date.setText(Utils.formatDate(model.getFinishDate(), Utils.DATE_FORMAT));

            if (model.getId() == TravelModel.DEFAULT_ID) {
                getActivity().setTitle(R.string.activity_travel_new_title);
            }else{
                getActivity().setTitle(travelModel.getName());
            }
        }
    }

    @Override
    public TravelModel getCurrentModel() {
        travelModel.setFinishDate(Utils.parseDate(txt_finish_date.getText().toString(), Utils.DATE_FORMAT));
        travelModel.setStartDate(Utils.parseDate(txt_start_date.getText().toString(), Utils.DATE_FORMAT));
        travelModel.setName(txt_name.getText().toString());
        travelModel.getDestination().setDestinationPlaceName(txt_destination.getText().toString());
        return travelModel;
    }

    @Override
    public boolean validate() {
        boolean result = true;

        View firstError = null;

        if (!validateRequiredField(txt_name)) {
            result = false;
            firstError = txt_name;
        }

        if (!validateRequiredField(txt_destination)) {
            result = false;
            if (firstError == null) {
                firstError = txt_destination;
            }
        }

        if (!validateRequiredField(txt_start_date) && !validateDateFormat(txt_start_date)) {
            result = false;
            if (firstError == null) {
                firstError = txt_start_date;
            }
        }

        if (!validateRequiredField(txt_finish_date) && !validateDateFormat(txt_finish_date)) {
            result = false;
            if (firstError == null) {
                firstError = txt_finish_date;
            }
        }

        if (firstError != null) {
            firstError.requestFocus();
        }

        return result;
    }

    @Override
    public Context getViewContext() {
        return getActivity();
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

    private void initGoogleApi() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this.getActivity(), 0, null)
                .build();
    }

    private void initAutocomplete () {
        final AutocompleteFilter citiesFilter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build();
        placesAutoCompleteAdapter = new PlacesAutoCompleteAdapter(getActivity(), R.layout.places_search_item, googleApiClient, null, citiesFilter);
    }

    private final PlacesSelectionListener.PlacesSelectionCallback placesSelectionCallback = new PlacesSelectionListener.PlacesSelectionCallback() {
        @Override
        public void onSelect(final Place selectedPlace) {
            travelModel.getDestination().setDestinationPlaceId(selectedPlace.getId());
            travelModel.getDestination().setDestinationPlaceName(selectedPlace.getName().toString());
            travelModel.getDestination().setDestinationPlaceLatitude(selectedPlace.getLatLng().latitude);
            travelModel.getDestination().setDestinationPlaceLongitude(selectedPlace.getLatLng().longitude);
            txt_destination.setText(selectedPlace.getName());
            txt_start_date.requestFocus();
        }

        @Override
        public void onError() {

        }
    };

}
