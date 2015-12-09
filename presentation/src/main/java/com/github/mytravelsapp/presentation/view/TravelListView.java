package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;

import java.util.List;

/**
 * @author fjtorres
 */
public interface TravelListView extends View {
    void setTravelList(List<TravelModel> list);
    void viewDetail(TravelModel selectedModel);
    void newTravel();
}
