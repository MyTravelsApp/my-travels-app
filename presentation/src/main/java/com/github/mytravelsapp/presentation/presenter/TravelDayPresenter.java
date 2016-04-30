package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.dto.TravelDayPlanningDto;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.FindTravelPlacesByIdsInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.model.TravelDayPlanningModel;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelDayView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelDayPresenter extends AbstractPresenter<TravelDayView> {

    private final TravelPlacesModelConverter converter;

    private final TravelModelConverter travelModelConverter;

    private final FindTravelPlacesByIdsInteractor findTravelPlacesByIdsInteractor;

    private final SaveTravelInteractor saveTravelInteractor;

    @Inject
    public TravelDayPresenter(final Navigator pNavigator, final FindTravelPlacesByIdsInteractor pFindTravelPlacesByIdsInteractor, final TravelModelConverter pTravelModelConverter, final TravelPlacesModelConverter pConverter, final SaveTravelInteractor pSaveTravelInteractor) {
        super(pNavigator);
        this.findTravelPlacesByIdsInteractor = pFindTravelPlacesByIdsInteractor;
        this.travelModelConverter = pTravelModelConverter;
        this.converter = pConverter;
        this.saveTravelInteractor = pSaveTravelInteractor;
    }

    public void load(final Date selectedDay) {
        final TravelModel model = getView().getCurrentModel();

        if (!Utils.isEmpty(model.getDaysPlanningMap())) {
            final List<TravelDayPlanningModel> daysPlanning = model.getDaysPlanningMap().get(selectedDay);

            if (daysPlanning == null) {
                getView().renderList(new ArrayList<TravelPlacesModel>());
            } else {
                getView().showLoading();
                final List<Long> travelPlacesIds = new ArrayList<>(daysPlanning.size());
                final List<Integer> travelPlacesOrder = new ArrayList<>(daysPlanning.size());

                for (final TravelDayPlanningModel dayPlanning : daysPlanning) {
                    if (dayPlanning.getDay().equals(selectedDay)) {
                        travelPlacesIds.add(dayPlanning.getTravelPlaceId());
                        travelPlacesOrder.add(dayPlanning.getOrder());
                    }
                }
                findTravelPlacesByIdsInteractor.setTravelPlaceIds(travelPlacesIds);

                findTravelPlacesByIdsInteractor.execute(new DefaultCallback<List<TravelPlacesDto>>(getView()) {
                    @Override
                    public void onSuccess(List<TravelPlacesDto> result) {
                        getView().hideLoading();
                        // Sort result by order in current day
                        TravelPlacesDto[] resultOrderBy = new TravelPlacesDto[result.size()];
                        for (final TravelPlacesDto item : result) {
                            final int index = travelPlacesIds.indexOf(item.getId());
                            resultOrderBy[travelPlacesOrder.get(index) - 1] = item;
                        }

                        getView().renderList(converter.convert(Arrays.asList(resultOrderBy)));
                    }
                });
            }
        }

    }

    public void remove(final Date selectedDay, final TravelPlacesModel placeToRemove) {
        final TravelModel model = getView().getCurrentModel();

        if (!Utils.isEmpty(model.getDaysPlanningMap())) {
            TravelDayPlanningModel toRemove = null;

            for (final TravelDayPlanningModel dayPlanning : model.getDaysPlanningMap().get(selectedDay)) {
                if (dayPlanning.getDay().equals(selectedDay) && dayPlanning.getTravelPlaceId().equals(placeToRemove.getId())) {
                    toRemove = dayPlanning;
                }
            }

            if (toRemove != null) {
                model.getDaysPlanningMap().get(selectedDay).remove(toRemove);
            }

            saveTravelInteractor.setData(travelModelConverter.convertToDto(model));
            saveTravelInteractor.execute(new DefaultCallback<Boolean>(getView()));
        }
    }

    /**
     *
     * @param selectedDay
     * @param fromPosition
     * @param toPosition
     */
    public void move(final Date selectedDay, final int fromPosition, final int toPosition) {
        final TravelModel model = getView().getCurrentModel();

        if (!Utils.isEmpty(model.getDaysPlanningMap())) {
            final List<TravelDayPlanningModel> dayPlanning = model.getDaysPlanningMap().get(selectedDay);
            Utils.swap(dayPlanning, fromPosition, toPosition);
            reOrderPlanning(model, selectedDay);
            saveTravelInteractor.setData(travelModelConverter.convertToDto(model));
            saveTravelInteractor.execute(new DefaultCallback<Boolean>(getView()));
        }
    }

    /**
     * Update order field in planning of specific day.
     * @param model Model to update.
     * @param selectedDay Specific day.
     */
    private void reOrderPlanning(final TravelModel model, final Date selectedDay) {

        if (!Utils.isEmpty(model.getDaysPlanningMap()) && !Utils.isEmpty(model.getDaysPlanningMap().get(selectedDay))) {
            int order = 1;
            for (final TravelDayPlanningModel dayPlanning : model.getDaysPlanningMap().get(selectedDay)) {
                dayPlanning.setOrder(order++);
            }

        }
    }
}
