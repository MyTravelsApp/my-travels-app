package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.GetTravelPlacesListInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.model.TravelDayPlanningModel;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelPlacesSelectorView;
import com.github.mytravelsapp.presentation.view.activity.TravelNavigationListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelPlacesSelectorPresenter extends AbstractPresenter<TravelPlacesSelectorView> {

    private final TravelModelConverter travelModelConverter;
    private final TravelPlacesModelConverter travelPlacesModelConverter;
    private final GetTravelPlacesListInteractor getTravelPlacesListInteractor;
    private final SaveTravelInteractor saveTravelInteractor;

    @Inject
    public TravelPlacesSelectorPresenter(final Navigator pNavigator, final GetTravelPlacesListInteractor pGetTravelPlacesListInteractor,
                                         final TravelPlacesModelConverter pTravelPlacesModelConverter, final SaveTravelInteractor pSaveTravelInteractor,
                                         final TravelModelConverter pTravelModelConverter) {
        super(pNavigator);
        this.getTravelPlacesListInteractor = pGetTravelPlacesListInteractor;
        this.travelPlacesModelConverter = pTravelPlacesModelConverter;
        this.saveTravelInteractor = pSaveTravelInteractor;
        this.travelModelConverter = pTravelModelConverter;
    }

    public void loadPlaces(final Date selectedDate) {
        final TravelModel model = getView().getCurrentModel();
        getView().showLoading();
        getTravelPlacesListInteractor.setTravelId(model.getId());
        getTravelPlacesListInteractor.execute(new Callback<List<TravelPlacesDto>>() {
            @Override
            public void onSuccess(List<TravelPlacesDto> result) {

                if (!Utils.isEmpty(model.getDaysPlanningMap()) && !Utils.isEmpty(model.getDaysPlanningMap().get(selectedDate))) {
                    final List<TravelPlacesDto> toRemove = new ArrayList<>();

                    for (final TravelDayPlanningModel item : model.getDaysPlanningMap().get(selectedDate)) {
                        for (final TravelPlacesDto item2 : result) {
                            if (item != null && item2 != null && item.getDay().equals(selectedDate) && item.getTravelPlaceId().equals(item2.getId())) {
                                toRemove.add(item2);
                                break;
                            }
                        }
                    }

                    result.removeAll(toRemove);
                }

                getView().hideLoading();
                getView().renderList(travelPlacesModelConverter.convert(result));
            }

            @Override
            public void onError(final Throwable cause) {
                getView().hideLoading();
                getView().showLoadError();
            }
        });
    }

    public void onSelect(final Date selectedDate, final List<TravelPlacesModel> selection) {
        final TravelModel model = getView().getCurrentModel();
        if (!Utils.isEmpty(selection)) {

            getView().showLoading();

            if (Utils.isEmpty(model.getDaysPlanningMap())) {
                model.setDaysPlanningMap(new HashMap<Date, List<TravelDayPlanningModel>>());
            }

            if (!model.getDaysPlanningMap().containsKey(selectedDate)) {
                model.getDaysPlanningMap().put(selectedDate, new ArrayList<TravelDayPlanningModel>());
            }

            final List<TravelDayPlanningModel> dayPlanning = model.getDaysPlanningMap().get(selectedDate);

            int order = dayPlanning.size();
            for (final TravelPlacesModel travelPlacesModel : selection) {
                if (travelPlacesModel != null) {
                    final TravelDayPlanningModel dayModel = new TravelDayPlanningModel();
                    dayModel.setTravelPlaceId(travelPlacesModel.getId());
                    dayModel.setDay(selectedDate);
                    dayModel.setOrder(++order);
                    if (!dayPlanning.contains(dayModel)) {
                        dayPlanning.add(dayModel);
                    }
                }
            }

            saveTravelInteractor.setData(travelModelConverter.convertToDto(model));
            saveTravelInteractor.execute(new Callback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    getView().hideLoading();
                    getView().finishSelection();
                    /*if(getView().getViewContext() instanceof TravelNavigationListener){
                        ((TravelNavigationListener) getView().getViewContext()).openFragmentTravelDay(selectedDate);

                    }*/
                }

                @Override
                public void onError(Throwable cause) {
                    getView().hideLoading();
                    // FIXME show error
                }
            });

        } else if(getView().getViewContext() instanceof TravelNavigationListener){
                ((TravelNavigationListener) getView().getViewContext()).openFragmentTravelDay(selectedDate);
        }
    }
}
