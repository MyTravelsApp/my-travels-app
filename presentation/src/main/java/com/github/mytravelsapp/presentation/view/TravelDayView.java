package com.github.mytravelsapp.presentation.view;

import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;

import java.util.List;

/**
 * Definition of the travel day planning view.
 * @author fjtorres
 */
public interface TravelDayView extends LoadDataView {

    TravelModel getCurrentModel();

    void setCurrentModel(final TravelModel model);

    void renderList(List<TravelPlacesModel> list);
}
