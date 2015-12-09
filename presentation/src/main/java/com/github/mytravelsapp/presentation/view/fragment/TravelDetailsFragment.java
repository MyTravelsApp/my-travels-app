package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.components.DatePickerSelectionListener;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author fjtorres
 */
public class TravelDetailsFragment extends AbstractFragment<TravelDetailsView, TravelDetailPresenter> implements TravelDetailsView {

    private static final String ARGUMENT_TRAVEL_ID = "ARGUMENT_TRAVEL_ID";

    @Inject
    TravelDetailPresenter presenter;

    private long travelId;

    @Bind(R.id.txt_name)
    TextView txt_name;

    @Bind(R.id.txt_destination)
    TextView txt_destination;

    @Bind(R.id.btn_start_date)
    Button btn_start_date;

    @Bind(R.id.btn_finish_date)
    Button btn_finish_date;

    public static TravelDetailsFragment newInstance(final long travelId) {
        final TravelDetailsFragment fragment = new TravelDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putLong(ARGUMENT_TRAVEL_ID, travelId);
        fragment.setArguments(arguments);
        return fragment;
    }

    public TravelDetailsFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_details, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        btn_start_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), btn_start_date, "dd/MM/yyyy"));
        btn_finish_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), btn_finish_date, "dd/MM/yyyy"));

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
        getComponent(TravelComponent.class).inject(this);
        this.presenter.setView(this);
        this.travelId = getArguments().getLong(ARGUMENT_TRAVEL_ID);
        getPresenter().loadModel(this.travelId);
    }

    @Override
    protected TravelDetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void renderModel(final TravelModel model) {
        if (model != null) {
            txt_name.setText(model.getName());
            txt_destination.setText(model.getDestination());
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (model.getStartDate() != null) {
                btn_start_date.setText(sdf.format(model.getStartDate()));
            }
            if (model.getFinishDate() != null) {
                btn_finish_date.setText(sdf.format(model.getFinishDate()));
            }
        }
    }
}
