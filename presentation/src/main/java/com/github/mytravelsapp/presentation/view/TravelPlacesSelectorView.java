package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.List;

/**
 * @author fjtorres
 */
public interface TravelPlacesSelectorView extends LoadDataView {

    void renderList(List<TravelPlacesModel> places);

    List<TravelPlacesModel> getSelectedPlaces();

    TravelModel getCurrentModel();

    void showLoadError ();
}
