package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.view.TravelListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelListPresenter extends AbstractPresenter<TravelListView> {

    private static final List<TravelModel> STATIC_DATA = new ArrayList<>();

    static {
        STATIC_DATA.add(new TravelModel(1L, "Viaje a Roma", "Roma"));
        STATIC_DATA.add(new TravelModel(2L, "Viaje a Londres", "Londres"));
        STATIC_DATA.add(new TravelModel(3L, "Viaje a Paris", "Paris"));
        STATIC_DATA.add(new TravelModel(4L, "Viaje a Riviera maya", "Playa del carmen"));
        STATIC_DATA.add(new TravelModel(5L, "Viaje a Mallorca", "Mallorca"));
        STATIC_DATA.add(new TravelModel(6L, "Viaje a Lisboa", "Lisboa"));
        STATIC_DATA.add(new TravelModel(7L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(8L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(9L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(10L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(11L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(12L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(13L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(14L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(15L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(16L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(17L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(18L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(19L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(20L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(21L, "Puente de XX", "XX"));
        STATIC_DATA.add(new TravelModel(22L, "Puente de XX", "XX"));
    }

    @Inject
    public TravelListPresenter() {

    }

    public void loadTravels() {
        getView().setTravelList(STATIC_DATA);
    }

    public void viewDetail(final TravelModel selectedModel) {
        getView().viewDetail(selectedModel);
    }

    public void newTravel() {
        getView().newTravel();
    }
}
