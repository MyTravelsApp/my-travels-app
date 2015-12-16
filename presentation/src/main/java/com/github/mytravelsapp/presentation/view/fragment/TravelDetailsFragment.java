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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.presenter.TravelDetailPresenter;
import com.github.mytravelsapp.presentation.view.TravelDetailsView;
import com.github.mytravelsapp.presentation.view.components.DatePickerSelectionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that shows details of certain travel.
 *
 * @author fjtorres
 */
public class TravelDetailsFragment extends AbstractFormFragment<TravelDetailsView, TravelDetailPresenter> implements TravelDetailsView {

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

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_travel_details, container, false);
        ButterKnife.bind(this, fragmentView);

        // Setup UI
        btn_start_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), txt_start_date, getString(R.string.conf_date_format)));
        btn_finish_date.setOnClickListener(new DatePickerSelectionListener(getActivity(), txt_finish_date, getString(R.string.conf_date_format)));

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
        boolean result;
        switch (item.getItemId()) {
            case R.id.action_save_travel:
                saveAction();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    private void initialize() {
        getComponent(TravelComponent.class).inject(this);
        this.travelId = getArguments().getLong(ARGUMENT_TRAVEL_ID);
        getPresenter().setView(this);
        getPresenter().loadModel(this.travelId);
    }

    private void saveAction() {
        getPresenter().save();
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
            final SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.conf_date_format));
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

    @Override
    public TravelModel getCurrentModel() {
        final TravelModel model = new TravelModel(travelId);
        final SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.conf_date_format));
        try {
            model.setFinishDate(sdf.parse(txt_finish_date.getText().toString()));
            model.setStartDate(sdf.parse(txt_start_date.getText().toString()));
        } catch (final ParseException e) {
            e.printStackTrace();
            // FIXME Invalid date error???
        }
        model.setName(txt_name.getText().toString());
        model.setDestination(txt_destination.getText().toString());
        return model;
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

}
