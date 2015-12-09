package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.TravelListView;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelListPresenter extends AbstractPresenter<TravelListView> {

    @Inject
    public TravelListPresenter() {

    }

    public void loadTravels() {
        final TravelModel model = new TravelModel(1L);
        model.setName("Roma");

        final TravelModel model2 = new TravelModel(2L);
        model2.setName("Paris");

        getView().setTravelList(Arrays.asList(model, model2));
    }

    public void viewDetail(final TravelModel selectedModel) {
        getView().viewDetail(selectedModel);
    }
}
