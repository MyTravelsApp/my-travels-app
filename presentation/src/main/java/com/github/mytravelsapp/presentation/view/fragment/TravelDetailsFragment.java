package com.github.mytravelsapp.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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
 * Fragment that shows details of certain travel.
 *
 * @author fjtorres
 */
public class TravelDetailsFragment extends AbstractFragment<TravelDetailsView, TravelDetailPresenter> implements TravelDetailsView {

    private static final String ARGUMENT_TRAVEL_ID = "ARGUMENT_TRAVEL_ID";

    @Inject
    TravelDetailPresenter presenter;

    private long travelId;

    @Bind(R.id.txt_name)
    EditText txt_name;

    @Bind(R.id.txt_destination)
    EditText txt_destination;

    @Bind(R.id.txt_start_date)
    TextView txt_start_date;

    @Bind(R.id.btn_start_date)
    ImageButton btn_start_date;

    @Bind(R.id.txt_finish_date)
    TextView txt_finish_date;

    @Bind(R.id.btn_finish_date)
    ImageButton btn_finish_date;

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
        btn_start_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), txt_start_date, "dd/MM/yyyy"));
        btn_finish_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), txt_finish_date, "dd/MM/yyyy"));

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
                txt_start_date.setText(sdf.format(model.getStartDate()));
            }
            if (model.getFinishDate() != null) {
                txt_finish_date.setText(sdf.format(model.getFinishDate()));
            }

            if (travelId == TravelModel.DEFAULT_ID) {
                getActivity().setTitle(R.string.activity_travel_new_title);
            }
        }
    }
}
